package test;

/**
 * Created by Administrator
 * 下午1:49 16-5-10
 */
public class StringIndexTest {
    public static void main(String[] args) {
        String s = "hello";
        System.out.println(s.indexOf("t"));
        System.out.println(s.substring(s.indexOf("o"), s.indexOf("t"))); // -1 - 4 = -5
    }
}
