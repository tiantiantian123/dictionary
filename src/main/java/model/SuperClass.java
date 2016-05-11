package model;

/**
 * Created by Administrator
 * 下午3:28 16-5-10
 */
public class SuperClass {
    private int i;
    double d;


}

class SubClass {
    String s;

    public SubClass(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
