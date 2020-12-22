package jdk;

/**
 * @author: Luo
 * @description:
 * @time: 2020/9/23 14:34
 * Modified By:
 */
public abstract class Person {
    private String  name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    public Person() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public abstract void eat();

}
