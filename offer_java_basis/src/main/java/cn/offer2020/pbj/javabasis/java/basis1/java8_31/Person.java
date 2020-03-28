package cn.offer2020.pbj.javabasis.java.basis1.java8_31;

public class Person {

    private String name;
    private int age;
    private int size;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", size=" + size + "]";
    }
    public Person(String name, int age, int size) {
        super();
        this.name = name;
        this.age = age;
        this.size = size;
    }
    public Person() {
        super();
        // TODO Auto-generated constructor stub
    }
    
}
