import org.apache.shiro.crypto.hash.Md5Hash;

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
