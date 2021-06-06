package realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyMD5Realm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取到用户名
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        //根据用户名的得到相应的权限，应该是从数据库中获取，这里假装获取到了
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //给当前用户添加角色
        simpleAuthorizationInfo.addRole("admin");
        simpleAuthorizationInfo.addRole("user");

        //给当前用户添加权限
        simpleAuthorizationInfo.addStringPermission("food:eat:*");
        simpleAuthorizationInfo.addStringPermission("water:drink:tea");

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal();

        String username = "chenkehao";
        String password = "123456";
        String salt = "[];',.";
        String passwordMD5 = new Md5Hash(password,salt,1024).toHex();

        if(principal.equals(username)){
            return new SimpleAccount(principal,passwordMD5,ByteSource.Util.bytes(salt),getName());
        }

        return null;
    }
}
