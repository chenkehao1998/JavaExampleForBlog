import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import realm.MyRealm;

public class MyRealmTest {
    public static void main(String[] args) {
        DefaultSecurityManager manager = new DefaultSecurityManager();
        Realm realm = new MyRealm();
        manager.setRealm(realm);

        SecurityUtils.setSecurityManager(manager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("chenkehao","1234560");
        System.out.println("被授权："+subject.isAuthenticated());
        try {
            subject.login(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("被授权："+subject.isAuthenticated());

    }
}
