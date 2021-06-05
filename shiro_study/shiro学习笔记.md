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





