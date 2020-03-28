package cn.offer2020.pbj.book_reading.effective_java3.chapter2.item1;

/**
 * 参考文章：https://www.cnblogs.com/ybbzbb/p/5489617.html
 */
public class AnimalTest {
    public static void main(String [] args){
        Animal.registerProvider("dog", new Provider(new Dog()));

        Animal.registerProvider("person", new Provider(new Person()));

        IAnimal dog = Animal.newInstance("dog");
        IAnimal person = Animal.newInstance("person");

        System.out.println(dog.toString());
        System.out.println(person.toString());
    }
}
