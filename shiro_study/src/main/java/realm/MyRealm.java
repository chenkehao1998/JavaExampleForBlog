package realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm {
    /**
     * 用于授权的函数
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取到用户名
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        //根据用户名的得到相应的权限，应该是从数据库中获取，这里假装获取到了
        SimpleAccount simpleAccount = new SimpleAccount();
        //给当前用户添加角色
        simpleAccount.addRole("admin");
        simpleAccount.addRole("user");

        //给当前用户添加权限
        simpleAccount.addStringPermission("eat");
        simpleAccount.addStringPermission("drink");

        return simpleAccount;
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
