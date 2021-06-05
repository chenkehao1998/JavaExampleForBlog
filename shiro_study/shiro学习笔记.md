## 自定义Realm

自定义Realm，一般是直接继承`AuthorizingRealm`，而不是去实现`Realm`接口

继承`AuthorizingRealm`之后，需要实现两个抽象函数，分别是用于认证和授权

可以去仿照`SimpleAccountRealm`去实现，下面是`SimpleAccount`的部分源码

```java
//认证的函数实现
protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    UsernamePasswordToken upToken = (UsernamePasswordToken) token;
    SimpleAccount account = getUser(upToken.getUsername());

    if (account != null) {

        if (account.isLocked()) {
            throw new LockedAccountException("Account [" + account + "] is locked.");
        }
        if (account.isCredentialsExpired()) {
            String msg = "The credentials for account [" + account + "] are expired";
            throw new ExpiredCredentialsException(msg);
        }

    }

    return account;
}
//授权的函数实现
protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    String username = getUsername(principals);
    USERS_LOCK.readLock().lock();
    try {
        return this.users.get(username);
    } finally {
        USERS_LOCK.readLock().unlock();
    }
}
```

自定义`Realm`的实现

```java
public class MyRealm extends AuthorizingRealm {
    /**
     * 用于授权的函数 先暂时不实现
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 认证的函数实现 根据用户传入的登录信息 获取到正确的信息 以供调用它的函数用于认证
     * 根据用户传入的token获取相应的正确的AuthenticationInfo返回
     * 如果返回空 则在login的时候会throw UnknownAccountException
     * 如果返回内容不为空则
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal();
        String username = "chenkehao";
        String password = "123456";
        if(principal.equals(username)){
            AuthenticationInfo authenticationInfo = new SimpleAccount(principal,password,getName());
            return authenticationInfo;
        }
        return null;
    }
}
```

## 使用MD5+Salt+Hash

### MD5算法的介绍

- 作用：一般用来加密或者签名（校验和）

- 特点：MD5算法不可逆如何内容相同无论执行多少次md5生成结果始终是一致

- 网络上提供的MD5在线解密一般是用穷举的方法

- 生成结果：始终是一个16进制32位长度字符串

### shiro中MD5的基本使用

```java
public class MD5Test {
    public static void main(String[] args) {
        //使用Md5
        Md5Hash md5 = new Md5Hash("123");
        System.out.println("使用Md5-->"+md5.toHex());

        //使用Md5+salt
        Md5Hash md5Salt = new Md5Hash("123","'fo'~");
        System.out.println("使用Md5+salt-->"+md5Salt.toHex());

        //使用Md5+salt+hash 第三个参数代表散列多少次
        Md5Hash md5HashSalt = new Md5Hash("123","'fo'~",1024);
        System.out.println("使用Md5+salt+hash-->"+md5HashSalt.toHex());

    }
}
```



运行结果

```
使用Md5-->202cb962ac59075b964b07152d234b70
使用Md5+salt-->2f6d2ae52ee928a8ce165624eb2910c7
使用Md5+salt+hash-->89e97b80ee9b308e07db9b369b3ef166
```

自定义MD5 realm

```java
public class MyMD5Realm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal();

        String username = "chenkehao";
        String password = "123456";
        String salt = "[];',.";
        //对数据进行md5加密 用于假装获取到的就是加密好的数据
        String passwordMD5 = new Md5Hash(password,salt,1024).toHex();

        if(principal.equals(username)){
            //参数1:正确的用户名
            //参数2:md5+salt加密之后的密码
            //参数3:注册时的随机盐
            //参数4:realm的名字
            return new SimpleAccount(principal,passwordMD5,ByteSource.Util.bytes(salt),getName());
        }
        return null;
    }
}
```

测试代码

```java
public class MD5RealmTest {
    public static void main(String[] args) {
        DefaultSecurityManager manager = new DefaultSecurityManager();
        MyMD5Realm realm = new MyMD5Realm();
        //新建一个匹配器
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //设置匹配器的算法为md5
        matcher.setHashAlgorithmName("md5");
        //设置散列次数
        matcher.setHashIterations(1024);

        realm.setCredentialsMatcher(matcher);

        manager.setRealm(realm);

        SecurityUtils.setSecurityManager(manager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("chenkehao","123456");
        System.out.println("被授权："+subject.isAuthenticated());
        try {
            subject.login(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("被授权："+subject.isAuthenticated());
    }
}
```



