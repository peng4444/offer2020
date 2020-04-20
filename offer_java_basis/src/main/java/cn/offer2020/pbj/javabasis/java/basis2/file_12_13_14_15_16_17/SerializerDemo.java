package cn.offer2020.pbj.javabasis.java.basis2.file_12_13_14_15_16_17;

import java.io.*;

/**
 * @ClassName: SerializerDemo
 * @Author: pbj
 * @Date: 2020/4/20 22:08
 * @Description: TODO 序列化
 */
//声明一个实体类，实现Serializable接口
class Student implements Serializable {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

public class SerializerDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //* 使用ObjectOutputStream类的writeObject方法，实现序列化
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:\\text.out"));
        Student student = new Student();
        student.setAge(25);
        student.setName("jayWei");
        objectOutputStream.writeObject(student);

        objectOutputStream.flush();
        objectOutputStream.close();
        //* 使用ObjectInputStream类的readObject方法，实现反序列化
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:\\text.out"));
        Student student1 = (Student) objectInputStream.readObject();
        System.out.println("name="+student1.getName());

    }
}
