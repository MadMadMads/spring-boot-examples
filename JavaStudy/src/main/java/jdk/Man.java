package jdk;

/**
 * @author: Luo
 * @description:
 * @time: 2020/9/23 14:35
 * Modified By:
 */
public class Man extends Person {
    String t1;

    public Man(){}

    @Override
    public String toString() {
        return "Man{" +
                "t1='" + t1 + '\'' +
                '}';
    }

    public Man(String name, String t1) {
        super(name);
        this.t1 = t1;
    }

    public Man(String t1) {
        this.t1 = t1;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    @Override
    public void eat() {
        System.out.println("eating");
    }
    public void eat1(String s) {
        System.out.println(s);
    }
}
