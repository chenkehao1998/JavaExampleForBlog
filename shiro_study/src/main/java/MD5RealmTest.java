import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import realm.MyMD5Realm;
import realm.MyRealm;

public class MD5RealmTest {
    public static void main(String[] args) {
        DefaultSecurityManager manager = new DefaultSecurityManager();
        MyMD5Realm realm = new MyMD5Realm();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
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
