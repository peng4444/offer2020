# Java

[TOC]


## 面向对象思想
[Java面向对象封装和继承](https://www.cnblogs.com/Young111/p/10016570.html)
### 什么是面向过程、面向对象？
```markdown
面向过程与面向对象都是我们编程中，编写程序的一种思维方式。
    面向过程的程序设计方式，是遇到一件事时，思考“我该怎么做”，然后一步步实现的过程。
        例如：公司打扫卫生（擦玻璃、扫地、拖地、倒垃圾等），按照面向过程的程序设计方式会思考“打扫卫生我该怎么做，
        然后一件件的完成”，最后把公司卫生打扫干净了。
    面向对象的程序设计方式，是遇到一件事时，思考“我该让谁来做”，然后那个“谁”就是对象，他要怎么做这件事是他自己的事，
        反正最后一群对象合力能把事就好就行了。例如，公司打扫卫生（擦玻璃、扫地、拖地、倒垃圾等），
        按照面向对象的程序设计方式会思考“我该让谁来做，如小明擦玻璃、让小丽扫地、让小郭拖地、让小强倒垃圾等”,
        这里的“小明、小丽、小郭、小强”就是对象，他们要打扫卫生，怎么打扫是他们自己的事，反正最后一群对象合力把公司卫生打扫干净了。
面向对象思维方式的好处：
      面向对象思维方式是一种更符合人们思考习惯的思想
      面向过程思维方式中更多的体现的是执行者（自己做事情），面向对象中更多的体现是指挥者（指挥对象做事情）。
      面向对象思维方式将复杂的问题简单化。
```
### Java有哪些特点
```markdown
- 并发性的： 你可以在其中执行许多语句，而不必一次执行它
- 面向对象的：基于类和面向对象的编程语言。
- 独立性的： 支持一次编写，到处运行的独立编程语言，即编译后的代码可以在支持 Java 的所有平台上运行。
```
### 三大特性封装、继承和多态
```markdown
封装:利用抽象数据类型将数据和基于数据的操作封装在一起，使其构成一个不可分割的独立实体。数据被保护在抽象数据类型的内部，尽可能地隐藏内部的细节，
    只保留一些对外的接口使其与外部发生联系。用户无需关心对象内部的细节，但可以通过对象对外提供的接口来访问该对象。
    优点：
        减少耦合：可以独立地开发、测试、优化、使用、理解和修改
        减轻维护的负担：可以更容易被程序员理解，并且在调试的时候可以不影响其他模块
        有效地调节性能：可以通过剖析来确定哪些模块影响了系统的性能
        提高软件的可重用性
        降低了构建大型系统的风险：即使整个系统不可用，但是这些独立的模块却有可能是可用的
继承:
    继承应该遵循里氏替换原则，子类对象必须能够替换掉所有父类对象。
    Cat 可以当做 Animal 来使用，也就是说可以使用 Animal 引用 Cat 对象。父类引用指向子类对象称为 向上转型 。
    继承的好处：
        1、继承的出现提高了代码的复用性，提高软件开发效率。
        2、继承的出现让类与类之间产生了关系，提供了多态的前提。
    继承的注意事项
         1、在Java中，类只支持单继承，不允许多继承，也就是说一个类只能有一个直接父类。
         2、多个类可以继承一个父类。
         3、在Java中，多层继承是可以的，即一个类的父类可以再去继承另外的父类。
           4、在Java中，子类和父类是一种相对概念，也就是说一个类是某个类父类的同时，也可以是另一个类的子类。
[java抽象类、多态、接口](https://www.cnblogs.com/Young111/p/10033484.html)
抽象类、抽象方法的定义:
    抽象类定义的格式：abstract class 类名 {}
    抽象方法定义的格式：public abstract 返回值类型 方法名(参数);
    抽象类的特点
        1、抽象类和抽象方法都需要被abstract修饰。抽象方法一定要定义在抽象类中。
        2、抽象类不可以直接创建对象，原因：调用抽象方法没有意义。
        3、只有覆盖了抽象类中所有的抽象方法后，其子类才可以创建对象。否则该子类还是一个抽象类。
多态:Java中多态的代码体现在一个子类对象(实现类对象)既可以给这个子类(实现类对象)引用变量赋值，又可以给这个子类(实现类对象)的父类(接口)变量赋值。
    最终多态体现为父类引用变量可以指向子类对象。
    多态的前提是必须有子父类关系或者类实现接口关系，否则无法完成多态。
    在使用多态后的父类引用变量调用方法时，会调用子类重写后的方法。
    多态分为编译时多态和运行时多态：
    编译时多态主要指方法的重载
    运行时多态指程序中定义的对象引用所指向的具体类型在运行期间才确定
    运行时多态有三个条件：
        继承
        覆盖（重写）
        向上转型
[为何说要多用组合少用继承？如何决定该用组合还是继承？](https://www.cnblogs.com/appsucc/p/13117618.html)
尽管我们鼓励多用组合少用继承，但组合也并不是完美的，继承也并非一无是处。在实际的项目开发中，我们还是要根据具体的情况，来选择该用继承还是组合。
如果类之间的继承结构稳定，层次比较浅，关系不复杂，我们就可以大胆地使用继承。反之，我们就尽量使用组合来替代继承。
除此之外，还有一些设计模式、特殊的应用场景，会固定使用继承或者组合。
```
### 值传递和引用传递
```markdown
值传递是指在调用函数时将实际参数复制一份到函数中，这样的话如果函数对其传递过来的形式参数进行修改，将不会影响到实际参数。
引用传递是指在调用函数时将对象的地址直接传递到函数中，如果在对形式参数进行修改，将影响到实际参数的值。
简单点说，Java中的传递，是值传递，而这个值，实际上是对象的引用。
    如果参数是基本类型，传递的是基本类型的字面量值的拷贝。
    如果参数是引用类型，传递的是该参量所引用的对象在堆中地址值的拷贝。
```
### 类图
```markdown
泛化关系 (Generalization):用来描述继承关系，在 Java 中使用 extends 关键字。
实现关系 (Realization):用来实现一个接口，在 Java 中使用 implements 关键字。
聚合关系 (Aggregation):表示整体由部分组成，但是整体和部分不是强依赖的，整体不存在了部分还是会存在。
组合关系 (Composition):和聚合不同，组合中整体和部分是强依赖的，整体不存在了部分也不存在了。比如公司和部门，公司没了部门就不存
                        在了。但是公司和员工就属于聚合关系了，因为公司没了员工还在。
关联关系 (Association):表示不同类对象之间有关联，这是一种静态关系，与运行过程的状态无关，在最开始就可以确定。
        因此也可以用 1 对1、多对1、多对多这种关联关系来表示。比如学生和学校就是一种关联关系，一个学校可以有很多学生，
        但是一个学生只属于一个学校，因此这是一种多对一的关系，在运行开始之前就可以确定。
依赖关系 (Dependency)：和关联关系不同的是，依赖关系是在运行过程中起作用的。A 类和 B 类是依赖关系主要有三种形式：
        A 类是 B 类方法的局部变量；
        A 类是 B 类方法当中的一个参数；
        A 类向 B 类发送消息，从而影响 B 类发生变化。
```
### 设计原则
```markdown
1. 单一责任原则:修改一个类的原因应该只有一个。
    换句话说就是让一个类只负责一件事，当这个类需要做过多事情的时候，就需要分解这个类。
    如果一个类承担的职责过多，就等于把这些职责耦合在了一起，一个职责的变化可能会削弱这个类完成其它职责的能
力。
2. 开放封闭原则:类应该对扩展开放，对修改关闭。
    扩展就是添加新功能的意思，因此该原则要求在添加新功能时不需要修改代码。
    符合开闭原则最典型的设计模式是装饰者模式，它可以动态地将责任附加到对象上，而不用去修改类的代码。
3. 里氏替换原则:子类对象必须能够替换掉所有父类对象。
    继承是一种 IS-A 关系，子类需要能够当成父类来使用，并且需要比父类更特殊。
    如果不满足这个原则，那么各个子类的行为上就会有很大差异，增加继承体系的复杂度。
4. 接口分离原则:不应该强迫客户依赖于它们不用的方法。
    因此使用多个专门的接口比使用单一的总接口要好。
5. 依赖倒置原则:高层模块不应该依赖于低层模块，二者都应该依赖于抽象；抽象不应该依赖于细节，细节应该依赖于抽象。
    高层模块包含一个应用程序中重要的策略选择和业务模块，如果高层模块依赖于低层模块，那么低层模块的改动就会
    直接影响到高层模块，从而迫使高层模块也需要改动。
1. 迪米特法则
迪米特法则又叫作最少知识原则（Least Knowledge Principle简写LKP），就是说一个对象应当对其他对象有尽可能少的了解，不和陌生人说话。
2. 合成复用原则
尽量使用对象组合，而不是通过继承来达到复用的目的。
3. 共同封闭原则
一起修改的类，应该组合在一起（同一个包里）。如果必须修改应用程序里的代码，我们希望所有的修改都发生在一个包里（修改关闭），而不是遍布在很多包里。
4. 稳定抽象原则
最稳定的包应该是最抽象的包，不稳定的包应该是具体的包，即包的抽象程度跟它的稳定性成正比。
5. 稳定依赖原则
包之间的依赖关系都应该是稳定方向依赖的，包要依赖的包要比自己更具有稳定性。
```

## Java基础
### 1.Java数据类型
```markdown
byte/8
char/16
short/16
int/32
float/32
long/64
double/64
boolean/~
boolean 只有两个值：true、false，可以使用1bit 来存储，但是具体大小没有明确规定。JVM会在编译时期将
boolean 类型的数据转换为int，使用1来表示true，0表示false。JVM支持boolean数组，但是是通过读写byte数组来实现的。
包装类型:基本类型都有对应的包装类型，基本类型与其对应的包装类型之间的赋值使用自动装箱与拆箱完成。
缓存池:new Integer(123) 与 Integer.valueOf(123) 的区别在于：    
    new Integer(123) 每次都会新建一个对象；
    Integer.valueOf(123) 会使用缓存池中的对象，多次调用会取得同一个对象的引用。
    valueOf() 方法的实现比较简单，就是先判断值是否在缓存池中，如果在的话就直接返回缓存池的内容。
    在 Java 8 中，Integer 缓存池的大小默认为 -128~127。
float与double：Java不能隐式执行向下转型，因为这会使得精度降低。
```
### 2.Java八种变量
```markdown
实例变量:又被称为Instance variables。不使用 static 关键字定义，并且在任何方法、构造方法、块之外的变量都是实例变量。
全局变量：在Java中，是不存在全局变量的。因为Java是一门面向对象的编程语言，所有的内容都是属于类的一部分。
    Java这么做的原因是为了防止数据和类成员被其他程序的其他部分有意或者无意的修改。所以在Java中，使用静态变量来起到全局访问的目的。
静态变量:是属于该类的变量，它是由static关键字来修饰的。static修饰的变量属于静态变量，它只能定义在类的内部、方法的外部。
类变量:在Java中，类变量就是静态变量，它们都用static关键字进行修饰，所以，如果你再听到说静态变量的时候，它也就是类变量。
局部变量:指的是在方法中、构造器中或者块代码中定义的变量。局部变量的生命周期随方法、构造器、代码块的执行完毕而销毁。
成员变量
常量
实例变量的定义规则：
    实例变量可以使用四种访问修饰符进行修饰：public、protected、default、private
    实例变量可以使用 transient、final 关键字进行修饰
    实例变量不可以使用 abstract、synchronized、strictfp、native、static关键字进行修饰
实例变量的特点：
    实例变量的只能在类中声明，但是在方法、构造函数或任何块之外。
    当在为堆中对象分配空间时，将为每个实例变量分配一块区域。
    实例变量只能通过创建对象来使用，当使用 new 关键字进行创建对象时，实例变量同时也被创建，当垃圾回收器回收对象时，实例变量也会被销毁。
    实例变量可以使用访问修饰符来修饰
    实例变量不用强制初始化，它有自己的默认值。
    每个对象都有自己的一个实例变量的副本，因此在一个对象中修改变量不会对其他对象中的实例变量造成影响
    实例变量只能通过创建对象引用来使用。
静态变量的特点    
    静态变量只能使用 static 关键字进行修饰，它不能在方法中进行声明，不论是静态方法还是非静态方法。
    静态变量会在程序运行前进行初始化，并且只初始化一次。静态变量会有一个初始化顺序，我们后面说。
    静态变量的所有实例共享同一个副本。也就是说，静态变量只有一个，它不会随着对象实例的创建而进行副本拷贝
    静态变量可以通过类名.变量名 进行访问，并且不需要创建任何对象就能访问。
局部变量的特点     
    根据定义可知，局部变量定义在方法、构造器或者代码块中；
    然后局部变量的生命周期随方法、构造器、代码块的执行完毕而销毁；
    局部变量不能使用访问修饰符，例如如下代码
    局部变量仅在方法的声明、构造函数或者块内可见，局部变量只能在调用这些方法、构造函数或者块的内部使用
    局部变量没有默认值，所以局部变量应该在第一次使用或者声明的时候就应该初始化完成
```
### 2.String被声明为final，因此它不可被继承。
```markdown
String被声明为final，因此它不可被继承。在 Java 8 中，String内部使用char数组存储数据。
在Java9之后，String类的实现改用byte数组存储字符串，同时使用coder来标识使用了哪种编码。
value数组被声明为final，这意味着value数组初始化之后就不能再引用其它数组。并且String内部没有改变value数组的方法，因此可以保证String不可变。
不可变的好处    
    1. 可以缓存 hash 值
    因为String的hash值经常被使用，例如String用做HashMap的key。不可变的特性可以使得hash值也不可变，因此只需要进行一次计算。
    2. String Pool(字符串常量池)的需要
    如果一个String对象已经被创建过了，那么就会从String Pool中取得引用。只有String是不可变的，才可能使用String Pool。
    当一个字符串调用 intern()方法时，如果StringPool中已经存在一个字符串和该字符串值相等（使用equals() 方法进行确定），
    那么就会返回 String Pool中字符串的引用；否则，就会在String Pool中添加一个新的字符串，并返回这个新字符串的引用。
    3. 安全性
    String经常作为参数，String不可变性可以保证参数不可变。例如在作为网络连接参数的情况下如果String是可变的，
    那么在网络连接过程中，String被改变，改变String对象的那一方以为现在连接的是其它主机，而实际情况却不一定是。
    4. 线程安全
    String不可变性天生具备线程安全，可以在多个线程中安全地使用。
String 不可变；StringBuffer 和 StringBuilder 可变
String不可变，因此是线程安全的；StringBuilder不是线程安全的；StringBuffer是线程安全的，内部使用synchronized进行同步。
```
### 3.Object的方法clone
```markdown
clone():是Object的protected方法，它不是public，一个类不显式去重写clone()，其它类就不能直接去调用该类实例的clone()方法。
浅拷贝:拷贝对象和原始对象的引用类型引用同一个对象。
深拷贝:拷贝对象和原始对象的引用类型引用不同对象。
clone()的替代方案:最好不要去使用clone()，可以使用拷贝构造函数或者拷贝工厂来拷贝一个对象。
Java中的对象拷贝(Object Copy)指的是将一个对象的所有属性（成员变量）拷贝到另一个有着相同类类型的对象中去。
在程序中拷贝对象是很常见的，主要是为了在新的上下文环境中复用现有对象的部分或全部数据。
Java中的对象拷贝主要分为：浅拷贝 (Shallow Copy)、深拷贝 (Deep Copy)。
深拷贝和浅拷贝区别是什么？
浅拷贝只能应用于成员变量全是基本类型的对象，而深拷贝可以应用成员变量是对象的对象。
一般步骤是（浅复制）：
1.被复制的类需要实现Clonenable接口（不实现的话在调用clone方法会抛出CloneNotSupportedException异常)该接口为标记接口(不含任何方法)
2.覆盖 clone () 方法。方法中调用super.clone()方法得到需要的复制对象，注意该方法需要处理异常。
深拷贝的方式有两种：
1.与通过重写clone方法实现浅拷贝的基本思路一样，只需要为对象图的每一层的每一个对象都实现Cloneable接口并重写clone方法，
最后在最顶层的类的重写的clone方法中调用所有的clone方法即可实现深拷贝。简单的说就是：每一层的每个对象都进行浅拷贝 = 深拷贝。
2.结合序列化来解决这个问题，先把对象序列化，然后再反序列化成对象，该对象保证每个引用都是崭新的。这个就形成了多个引用，原引用和反序列化之后的引用不在相同。
```
### 4.final和static
```markdown
final:声明数据为常量，可以是编译时常量，也可以是在运行时被初始化后不能被改变的常量。
    对于基本类型，final使数值不变；不能被重新赋值。
    对于引用类型，final使引用不变，也就不能引用其它对象，但是被引用的对象本身是可以修改的。
    final声明方法不能被子类重写。
    final声明类不允许被继承。
    包含final域的对象的引用和读这个final域，不能重排序；构造函数对final域的写入和这个对象的引用被赋值，不能重排序。
    使用场景：
     不可改变域
     多线程使用场景，使用final关键字或者：synchronized、volatile、锁
static:静态变量：又称为类变量，也就是说这个变量属于类的，类所有的实例都共享静态变量，可以直接通过类名来访问它。静态变量在内存中只存在一份。
    实例变量：每创建一个实例就会产生一个实例变量，它与该实例同生共死。
    静态方法在类加载的时候就存在了，它不依赖于任何实例。所以静态方法必须有实现，也就是说它不能是抽象方法。
    静态语句块在类初始化时运行一次。
    非静态内部类依赖于外部类的实例，而静态内部类不需要。静态内部类不能访问外部类的非静态的变量和方法。
    在使用静态变量和方法时不用再指明ClassName，从而简化代码，但可读性大大降低。
    静态变量和静态语句块优先于实例变量和普通语句块，静态变量和静态语句块的初始化顺序取决于它们在代码中的顺序。
```
### 5.值传递和引用传递的区别是什么？
```markdown
值传递：在方法的调用过程中，实参把它的实际值传递给形参，此传递过程就是将实参的值复制一份传递到函数中，这样如果在函数中对该值（形参的值）进行了操作将不会
影响实参的值。因为是直接复制，所以这种方式在传递大量数据时，运行效率会特别低下。
引用传递：弥补了值传递的不足，如果传递的数据量很大，直接复过去的话，会占用大量的内存空间，而引用传递就是将对象的地址值传递过去，
函数接收的是原始值的首地址值。在方法的执行过程中，形参和实参的内容相同，指向同一块内存地址，也就是说操作的其实都是源数据，所以方法的执行将会影响到实际对象。
```
### 6.什么是java序列化？什么情况下需要序列化？如何避免序列化对象中的属性序列化？
```markdown
1.在Java中，我们可以通过多种方式来创建对象，并且只要对象没有被回收我们都可以复用此对象。但是，创建出来的这些对象都存在于JVM中的堆（stack）内存中，
只有JVM处于运行状态的时候，这些对象才可能存在。一旦JVM停止，这些对象也就随之消失；但是在真实的应用场景中，我们需要将这些对象持久化下来，
并且在需要的时候将对象重新读取出来，Java的序列化可以帮助我们实现该功能。
2.对象序列化机制（object serialization）是java语言内建的一种对象持久化方式，通过对象序列化，可以将对象的状态信息保存为字节数组，并且可以在有需要的时候将这个
字节数组通过反序列化的方式转换成对象，对象的序列化可以很容易的在JVM中的活动对象和字节数组（流）之间进行转换。
3.Java类通过实现java.io.Serializable接口来启用序列化功能，未实现此接口的类将无法将其任何状态或者信息进行序列化或者反序列化。可序列化类的所有子类型都是可以序
列化的。序列化接口没有方法或者字段，仅用于标识可序列化的语义。在JAVA中，对象的序列化和反序列化被广泛的应用到RMI（远程方法调用）及网络传输中。
属性避免序列化：
1.静态数据不能被序列化，因为静态数据不在堆内存中，而是在静态方法区中
2.将不需要序列化的属性前添加关键字 transient，序列化对象的时候，这个属性就不会序列化到指定的目的地中。
```
### 7.什么是反射？反射的应用场景？
[反射那些基础-Class](https://www.cnblogs.com/homejim/p/10140928.html)
```markdown
JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法和属性；
这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。所有反射的入口都是 java.lang.Class。
因为Java中规定了java.lang.reflect包下的所有类的构造函数都不为public， 因此，需要获得这些类的对象都需要调用Class类中适当的的方法。
    获取类对象的三种方式：
       通过object类的getClass()函数，由于object是根类，每一个类都有这个函数。Object.getClass()
       每一个类（包括基本数据类型，注意这里基本数据类型不用转成包装类）都有一个class属性，静态属性，通过类名直接访问。类.class;
       通过Class类的静态方法forName(String className)。 Class.forName("java.lang.System");
       通过包装类的TYPE成员。对于基本数据类型，除了以上的.class方法之外，对应包装类的TYPE成员变量来获取Class对象。Double.TYPE;
    应用：
       反射是很多框架的基础
       通过反射运行配置文件
       通过反射越过泛型检查
Class里面存储了对应类的所有信息，因此，我们可以获得类相关的信息。突破私有字段的保护机制，访问并修改对象的私有字段。
反射中，Class.forName()和ClassLoader.loadClass()区别？
 Class.forName()执行的是类加载过程的链接和初始化。需要整个类完全加载到内存中，以获取该类的信息。
 ClassLoader.loadClass()执行的只是类加载过程中的第一步，加载过程。loadClass方法是在双亲委派中调用，此时判断类由哪一个类加载器加载，因此类还未加载到内存中。
```
### 8.抽象类和接口的区别？普通类和抽象类有哪些区别？
```markdown
抽象类和普通类的唯一区别就在于是否实例化。
抽象类必须要有抽象方法吗？抽象类能使用final修饰吗？
     抽象类中可以有普通方法，也可以有抽象方法
     但是有抽象方法的类一定是抽象类，但是抽象类中不一定有抽象方法。
     abstract和final不共存，final不能继承
     abstract和static不共存 ，因为abstract不能修饰属性
```
### 9.什么是多态？
```markdown
多态就是指程序中定义的引用变量所指向的具体类型和通过该引用变量发出的方法调用在编程时并不确定，而是在程序运行期间才确定，
即一个引用变量到底会指向哪个类的实例对象，该引用变量发出的方法调用到底是哪个类中实现的方法，必须在由程序运行期间才能决定。
因为在程序运行时才确定具体的类，这样，不用修改源程序代码，就可以让引用变量绑定到各种不同的类实现上，从而导致该引用调用的具体方法随之改变，
即不修改程序代码就可以改变程序运行时所绑定的具体代码，让程序可以选择多个运行状态，这就是多态性。
```
### Java泛型
[用了这么多年的 Java 泛型，你对它到底有多了解？](https://www.cnblogs.com/goodAndyxublog/p/12934938.html)
```markdown
Java 泛型实现方式：Java采用类型擦除（Type erasure generics）的方式实现泛型。
    用大白话讲就是这个泛型只存在源码中，编译器将源码编译成字节码之时，就会把泛型『擦除』，所以字节码中并不存在泛型。
    并不是每一个泛型参数被擦除类型后都会变成Object类，如果泛型类型为T extends String这种方式，最终泛型擦除之后将会变成String。
类型擦除带来的缺陷：
    1.不支持基本数据类型,但是Java是可以向上转型的。查看字节码，泛型参数被擦除之后，强制变成了Object类型。
    2.Java类型擦除式泛型实现方式无论使用效果与运行效率全面落后于C#的具现化式泛型。
    3.由于编译之后，泛型就被擦除，所以在代码运行期间，Java 虚拟机无法获取泛型的实际类型。
Java 泛型发展史：
    1.Java 泛型最早是在 JDK5 的时候才被引入，但是泛型思想最早来自来自C++模板（template）。
```
[如何在运行时(Runtime)获得泛型的真正类型](https://www.cnblogs.com/xiaoheike/p/9867060.html)
### 10.BIO、NIO、AIO 有什么区别？
```markdown
BIO(Blocking I/O):同步阻塞I/O模式，数据的读取写入必须阻塞在一个线程内等待其完成。可以让每一个连接专注于自己的I/O并且编程模型简单，也不用过多考虑系统的过载、限流等问题。
NIO(NewI/O):NIO是一种同步非阻塞的I/O模型，在Java1.4中引入了NIO框架，对应java.nio包，提供了Channel,Selector,Buffer等抽象。它支持面向缓冲的，基于
通道的I/O操作方法。NIO提供了与传统BIO模型中的Socket和ServerSocket相对应的SocketChannel和ServerSocketChannel两种不同的套接字通道实现，两种通道都
支持阻塞和非阻塞两种模式。阻塞模式使用就像传统中的支持一样，比较简单，但是性能和可靠性都不好；非阻塞模式正好与之相反。对于低负载、低并发的应用程序，可以
使用同步阻塞I/O来提升开发速率和更好的维护性；对于高负载、高并发的（网络）应用，应使用NIO的非阻塞模式来开发
AIO (Asynchronous I/O): AIO也就是NIO2。在Java7中引入了NIO的改进版NIO2, 它是异步非阻塞的IO模型。异步IO是基于事件和回调机制实现的，也就是应用操作
之后会直接返回，不会堵塞在那里，当后台处理完成，操作系统会通知相应的线程进行后续的操作。AIO是异步IO的缩写，虽然NIO在网络操作中，提供了非阻塞的方法，
但是NIO的IO行为还是同步的。对于NIO来说，我们的业务线程是在IO操作准备好时，得到通知，接着就由这个线程自行进行IO操作,IO操作本身是同步的。
```
### 11.类的实例化顺序
```markdown
类实例化顺序为：父类静态代码块/静态域->子类静态代码块/静态域 -> 父类非静态代码块 -> 父类构造器 -> 子类非静态代码块 -> 子类构造器
```
### 12.Java创建对象有几种方式
```markdown
Java创建对象有5种方式
    用new语句创建对象。
    使用反射，使用Class.newInstance()创建对象/调用类对象的构造方法——Constructor
    调用对象的clone()方法。
    运用反序列化手段，调用java.io.ObjectInputStream对象的readObject()方法.
    使用Unsafe
```
### 12.Java创建对象的过程
```markdown
User user = new User();
1.类加载检查：虚拟机遇到一条new指令时，首先去检查这个指令能否在常量池中定位到这个类的符号引用，并且检查这个符号引用代表的类
    是否被加载过、解析过和初始化过。如果没有，那必须先执行相应的类加载过程。
2.分配内存：在类加载检查通过后，接下来虚拟机将为新生对象分配内存。对象所需的内存大小在类加载完成后便确认了，为对象分配空间的任务
    等同于把一块确定大小的内存从Java堆中划分出来。分配方式有指针碰撞和空闲列表。
    虚拟机采用两种方式保证线程安全：CAS+失败重试，TLAB。
3.初始化零值：保证对象的实例字段在Java代码中可以不赋初始值就直接使用。
4.设置对象头：对象的元数据信息，对象的哈希码，对象的GC分代年龄等等信息放在对象头中。
5.执行init方法：执行new指令之后会接着执行<init>方式，把对象按照意愿进行初始化。
```
### 13.深拷贝和浅拷贝区别
```markdown
浅拷贝:复制了对象的引用地址，两个对象指向同一个内存地址，所以修改其中任意的值，另一个值都会随之变化。
    对基本数据类型进行值传递，对引用数据类型进行引用传递般的拷贝。
深拷贝:将对象及值复制过来，两个对象修改其中任意的值另一个值不会改变。
    对基本数据类型进行值传递，对引用数据类型，创建一个新的对象，并复制其内容。
```
### 14.如何实现对象克隆？
```markdown
实现Cloneable接口，重写clone()方法。
Object的clone()方法是浅拷贝，即如果类中属性有自定义引用类型，只拷贝引用，不拷贝引用指向的对象。
对象的属性的Class也实现Cloneable接口，在克隆对象时也手动克隆属性，完成深拷贝
结合序列化(JDK java.io.Serializable接口、JSON格式、XML格式等)，完成深拷贝
```
### 15.JDK8中日期类型该如何使用
[JDK8中日期类型该如何使用？](https://www.cnblogs.com/zwwhnly/p/13097475.html)
![](http://note.youdao.com/yws/res/4850/1B2263611B9C4821B98FFD8F77A7B6F7)
```markdown
在JDK8之前，处理日期时间，我们主要使用3个类，Date、SimpleDateFormat和Calendar。
    存在一些问题，比如SimpleDateFormat不是线程安全的，比如Date和Calendar获取到的月份是0到11，而不是现实生活中的1到12。
JDK8推出了全新的日期时间处理类解决了这些问题，比如Instant、LocalDate、LocalTime、LocalDateTime、DateTimeFormatter，
日期处理类Date
    有参构造：public Date(long date);接收long型数据 public long getTime();转换为long型
日期格式化 SimpleDateFormat
    构造方法：public SimpleDateFormat(String patten)
    将Date转换为String :public final String frmat(Date date)
    将String转换为Date:public Date parse(String source) throws ParseException
Calender主要是进行日期计算
JDK8：
    1.Instant获取当前时间,获取时间戳,将long转换为Instant, 将String转换为Instant
    2.LocalDate 获取当前日期, 获取年月日,指定日期,比较日期是否相等,获取日期是本周/本月/本年的第几天,判断是否为闰年
    3.LocalTime 获取时分秒,
    4.LocalDateTime 获取当前时间,获取年月日时分秒,增加天数/小时,减少天数/小时,获取时间是本周/本年的第几天
    5.DateTimeFormatter 格式化LocalDate,格式化LocalTime,格式化LocalDateTime
    6. 类型相互转换 Instant转Date,Date转Instant,Date转LocalDateTime,Date转LocalDate,Date转LocalTime
        LocalDateTime转Date,LocalDate转Date,LocalTime转Date.
《阿里巴巴Java开发规范》中也推荐使用Instant代替Date，LocalDateTime 代替 Calendar，DateTimeFormatter 代替 SimpleDateFormat。
```

## Java高级
### 3.Java容器集合
#### 1.Java容器集合简介
[java集合详解](https://www.cnblogs.com/yanzezhong/p/12808089.html)
```markdown
数组 - > 链表 ->类集  ：类集就是Java数据结构的实现，类集就是动态对象数组   源码
    Collection
        List（有序，可重复集合）
            ArrayList:基于动态数组实现，支持随机访问。
            LinkedList:基于双向链表(JDK1.6以前为循环链表，JDK1.7取消了)实现，只能顺序访问，但是可以快速地在链表中间插入和删除元素。
            Vector:和ArrayList类似，但它是线程安全的(synchronized锁)，效率低。
        Set（无序，不可重复集合）
            HashSet:HashSet 查找的时间复杂度为 O(1)，TreeSet 则为 O(logN)。基于哈希表实现，支持快速查找，但不支持有序性操作。
                    并且失去了元素的插入顺序信息，也就是说使用 Iterator 遍历 HashSet 得到的结果是不确定的。
            LinkedHashSet:具有HashSet的查找效率，且内部使用双向链表维护元素的插入顺序。
            SortedSet接口
                TreeSet:基于红黑树实现，支持有序性操作，例如根据一个范围查找元素的操作。但是查找效率不如。
        Queue
            LinkedList:可以用它来实现双向队列。
            PriorityQueue:基于堆结构实现，可以用它来实现优先队列。
    Map(双列集合)
        HashTable：和HashMap类似，但是线程安全的(synchronized锁)，意味着同一时刻多个线程可以同时写入HashTable并且不会导致数据不一致。
        可以使用ConcurrentHashMap来支持线程安全，并且ConcurrentHashMap的效率会更高，因为ConcurrentHashMap引入了分段锁。 
            Poperties
        HashMap：（数组+链表+红黑树）当链表长度超过8时，将链表转换为红黑树，链表长度低于6，就把红黑树转回链表，以减少搜索时间。
        LinkedHashMap：继承HashMap实现Map接口，使用双向链表来维护元素的顺序，顺序为插入顺序或者最近最少使用（LRU）顺序。
        SortedMap接口
            TreeMap：基于红黑树实现。          
集合输出 Iterator,ListIterator(双向集合输出),Enumeration,foreach,Enumeration只有在Vector接口中使用
Map遍历的两种方式
 keyset和entryset，前者是获得key的集合，后者是获得key-value的集合，返回的都是set视图，利用set有迭代器iterator，通过iterator.next来遍历。
 推荐使用entrySet()方法，效率较高。对于keySet其实是遍历了2次，一次是转为iterator，一次就是从HashMap中取出key所对于的value。
    而entryset只是遍历了第一次，它把key和value都放到了entry中，所以快了。    
```
#### Java容器集合源码
##### [ArrayList详解-源码分析](https://www.cnblogs.com/strive-for-life/p/12923608.html)
```markdown
* ArrayList是基于数组实现的集合列表
* 支持任意性的访问（可根据索引直接得到你想要的元素）
* 线程不安全
* 支持动态扩容
* 查询快，增删慢
默认默认容量大小为10，新数组的容量大小为旧数组容量的1.5倍，2^n。
详细讲解ArrayList的部分源码。
```
##### [LinkedList详解-源码分析](https://www.cnblogs.com/strive-for-life/p/12932845.html)
```markdown
* 基于双向链表实现
* 便于插入和删除，不便于遍历
* 非线程安全
* 有序（链表维护顺序）
transient关键字标识变量不会被序列化。节点都被transient修饰。
LinkedList是基于双向链表实现的，即每一个节点都保存了上一个节点和下一个节点的信息。
LinkedList根据索引获取元素效率低的原因是因为它需要一个节点一个节点的遍历，获取首节点和尾节点很快。
LinkedList实现了Deque接口，具有双向队列的性质，可以实现数据结构中的堆栈。
```
#### 2.Collection和Collections有什么区别？
```markdown
 Collection是容器接口，是List和Set的根接口; Collections是工具类，提供处理集合的各种方法
如何决定使用HashMap还是TreeMap？
     HashMap效率高，存取数据快，但是数据无序
     TreeMap效率低，但是存储数据是排序的，可以取到最大和最小值
ArrayList和LinkedList的区别是什么？
     ArrayList动态数组，索引方便，插入不方便
     LinkedList链表，索引不方便，插入方便
ArrayList和Vector的区别是什么？
     ArrayList 线程不安全，扩容*1.5
     Vector方法使用synchronized关键字。线程安全，扩容*2，效率比ArrayList低，适合存大数据并且要求线程安全
Iterator和ListIterator有什么区别？
     Iterator是List和Set的迭代器
     ListIterator是List的迭代器
     Iterator单向，ListIterator双向，ListIterator继承自Iterator，并且实现了更多功能，添加替换等。
在 Queue中poll()和remove()有什么区别？
     poll()和remove ()都将移除并且返回对头，但是在poll()在队列为空时返回null，而remove()会抛出NoSuchElementException异常。
     peek()和element ()都将在不移除的情况下返回队头，但是peek()方法在队列为空时返回null，调用element()方法会抛出NoSuchElementException 异常。
     add()和offer()都是向队列中添加一个元素。但是如果想在一个满的队列中加入一个新元素，调用add()方法就会抛出一个unchecked异常，而调用offer()方法会返回false。
```
#### 3.[Java集合排序策略接口Comparator](https://www.cnblogs.com/felordcn/p/12921857.html)
```markdown
List<People> peoples = new ArrayList<>();
  // 中间省略
  // 按照年龄从小到大排序
peoples.sort(Comparator.comparing(People::getAge));
Comparator是一个函数式接口。它经常用于没有天然排序的集合进行排序，如 Collections.sort 或 Arrays.sort。
或者对于某些有序数据结构的排序规则进行声明，如TreeSet 、TreeMap。也就是该接口主要用来进行集合排序。
```
#### 3.Java容器中的设计模式
[设计模式 - 迭代器模式详解及其在ArrayList中的应用](https://www.cnblogs.com/songjilong/p/12807345.html)
```markdown
迭代器模式：Collection继承了Iterable接口，iterator()方法能够产生一个Iterator对象，通过这个对象就可以迭代遍历Collection中的元素。
            从 JDK1.5之后可以使用foreach方法来遍历实现了Iterable接口的聚合对象。
适配器模式：java.util.Arrays#asList()可以把数组类型转换为List类型。
            应该注意的是asList()的参数为泛型的变长参数，不能使用基本类型数组作为参数，只能使用相应的包装类型数组。
```
#### [面试：在面试中关于List（ArrayList、LinkedList）集合会怎么问呢](https://www.cnblogs.com/Ccwwlx/p/13124514.html)
>> ArrayList(1.8):是由动态再分配的Object[]数组作为底层结构，可设置null值，是非线程安全的。
    默认容量为10,扩容操作为当前容量*1.5倍。
>> LinkedList是一个继承于AbstractSequentialList的双向链表。它也可以被当做堆栈、队列或双端队列进行使用，而且LinkedList也为非线程安全，
jdk1.6使用的是一个带有header节头结点的双向循环链表，头结点不存储实际数据，在1.6之后，就变更使用两个节点first、last指向首尾节点。   
```markdown
区别：
    ArrayList是实现了基于动态数组的数据结构，LinkedList是基于链表结构。
    对于随机访问的get和set方法查询元素，ArrayList要优于LinkedList，因为LinkedList循环链表寻找元素。
    对于新增和删除操作add和remove，LinkedList比较高效，因为ArrayList要移动数据。
优缺点：
    对ArrayList和LinkedList而言，在末尾增加一个元素所花的开销都是固定的。对ArrayList而言，主要是在内部数组中增加一项，指向所添加的元素，
        偶尔可能会导致对数组重新进行分配；而对LinkedList而言，这个开销是 统一的，分配一个内部Entry对象。
    在ArrayList集合中添加或者删除一个元素时，当前的列表移动元素后面所有的元素都会被移动。而LinkedList集合中添加或者删除一个元素的开销是固定的。
    LinkedList集合不支持 高效的随机随机访问（RandomAccess），因为可能产生二次项的行为。
    ArrayList的空间浪费主要体现在在list列表的结尾预留一定的容量空间，而LinkedList的空间花费则体现在它的每一个元素都需要消耗相当的空间
应用场景:
ArrayList使用在查询比较多，但是插入和删除比较少的情况，而LinkedList用在查询比较少而插入删除比较多的情况
```
#### [数据结构:用实例分析ArrayList与LinkedList的读写性能](https://www.cnblogs.com/zhuhuix/p/13042761.html)
```markdown
List使用首选ArrayList。对于个别插入删除非常多的可以使用LinkedList。
LinkedList，遍历建议使用Iterator迭代器，尤其是数据量较大时LinkedList避免使用get遍历。
```
#### modCount属性的作用？
```markdown
modCount属性代表为结构性修改（改变list的size大小、以其他方式改变他导致正在进行迭代时出现错误的结果）的次数，
该属性被Iterator以及ListIterator的实现类所使用，且很多非线程安全使用modCount属性。初始化迭代器时会给这个modCount赋值，如果在遍历的过程中，
一旦发现这个对象的modCount和迭代器存储的modCount不一样，Iterator或者ListIterator 将抛出ConcurrentModificationException异常，
这是jdk在面对迭代遍历的时候为了避免不确定性而采取的fail-fast（快速失败）原则：
在线程不安全的集合中，如果使用迭代器的过程中，发现集合被修改，会抛出ConcurrentModificationExceptions错误，这就是fail-fast机制。
对集合进行结构性修改时，modCount都会增加，在初始化迭代器时，modCount的值会赋给expectedModCount，在迭代的过程中，
只要modCount改变了，int expectedModCount=modCount等式就不成立了，迭代器检测到这一点，就会抛出错误：urrentModificationExceptions。
```
#### 4.ConcurrentHashMap如何实现线程同步
```markdown
hashmap的线程安全版，引入segment，每一个segment都是线程安全的，相当于一个hashtable，因此ConcurrentHashMap也不允许出现 null。
这样就把整个类锁变成了局部锁，用哪一个segment就给哪一个segment加锁。减少竞争，提高效率。
对于 jdk1.8 的改进：
     取消的 segment，转而采用数组元素作为锁。把锁的粒度从多个 node 变成一个 node，进一步减少锁竞争
     链表大于 8 的时候转化为红黑树
实现线程同步：元素 Node，字段修饰为 final 和 volatile，采用乐观锁CAS，和分而治之的思想
     put 操作和初始化操作：
         volatile字段，标识位，表示当前是否有线程在初始化，volatile 字段保证了所有线程的可见。
         CAS机制，保证只有一个线程能够初始化
     size()/判断大小
         首先通过 CAS 机制，如果没有线程竞争，直接递增 count，
         失败就初始化桶，每一个桶并发的记录（同样是CAS机制，最大程度利用并发），如果桶计数频繁失败就扩容桶。
```
#### 未阅读
[随笔分类 - Java提高篇](https://www.cnblogs.com/mfrank/category/1118474.html)

[Java集合框架——大量数据处理操作练习题](https://blog.csdn.net/ZQ_313/article/details/84797467)

[当前标签：集合源码](https://www.cnblogs.com/tong-yuan/tag/%E9%9B%86%E5%90%88/default.html?page=2)

[[面试必问之ArrayList](https://www.cnblogs.com/fsmly/p/11283921.html)]

[LinkedList集合解析及手写集合](https://www.cnblogs.com/hang-on/p/11469263.html)

[JAVA面试题 手写ArrayList的实现，在笔试中过关斩将?](https://www.cnblogs.com/marsitman/p/11204338.html)

[List集合根据存储对象的属性字段排序实现](https://blog.csdn.net/u013821825/article/details/61202287)

[HashMap常见面试题整理](https://www.cnblogs.com/zengcongcong/p/11295349.html)

[深入理解HashMap](https://www.cnblogs.com/fsmly/p/11235484.html)

[红黑树这个数据结构，让你又爱又恨？看了这篇，妥妥的征服它](https://www.cnblogs.com/wskwbog/p/11236136.html)

[[Java集合HashSet的原理及常用方法](https://www.cnblogs.com/LiaHon/p/11257805.html)]

[[TreeMap 还能排序？分析下源码就明白了](https://www.cnblogs.com/wskwbog/p/11245010.html)]

[[这 3 个 Set集合的实现有点简单，那来做个总结吧](https://www.cnblogs.com/wskwbog/p/11260056.html)]

[[HashMap、HashTable、ConcurrentHashMap](https://www.cnblogs.com/wudidamowang666/p/11286279.html)]

[[Java集合系列(四)：HashMap、Hashtable、LinkedHashMap、TreeMap的使用方法及区别](https://www.cnblogs.com/zwwhnly/p/11304627.html)]

[刨死你系列——HashMap(jdk1.8)](https://www.cnblogs.com/Young111/p/11471049.html)

[[HashMap 实现及原理](https://www.cnblogs.com/jay-wu/p/10773976.html)]

[[jdk1.8 HashMap底层数据结构：深入解析为什么jdk1.8 HashMap的容量一定要是2的n次幂](https://www.cnblogs.com/laipimei/p/11316140.html)]

[[Array List和Linked List实现分析](https://www.cnblogs.com/fenjyang/p/11480944.html)]

[[Java 迭代接口：Iterator、ListIterator 和 Spliterator](https://www.cnblogs.com/liululee/p/11416038.html)]

[Comparable接口的实现和使用](https://www.cnblogs.com/wl-centrinc/p/11872758.html)
[Java集合的Stack、Queue、Map的遍历](https://blog.csdn.net/m0_37204491/article/details/70208831)
### 4.Java集合容器博客
#### [1.口气带你踩完五个 List 的大坑，真的是处处坑啊！](https://www.cnblogs.com/goodAndyxublog/p/12758755.html)
```markdown
1.数组转List:Arrays.toList(arrays)：
        不支持增删元素，会抛出UnsupportedOperationException
        共享原始数组，修改原始数组，影响新集合；修改新集合，影响原始数组。
        返回结果为Arrays一个内部类，修复List<String> list = new ArrayList<>(Arrays.asList(arrays));
2.List.subList()：
        生成新集合也会与原始 List 互相影响。SubList持有原始List引用，导致原有List无法被释放
        ArrayList的subList结果不可强转成ArrayList。subList返回的是ArrayList的内部类SubList，并不是ArrayList而是
        ArrayList的一个视图，对于SubList子列表的所有操作最终会反映到原列表上。
3.foreach删除元素：
        foreach这种迭代方式实际就是Iterator迭代器实现方式 所以ArrayList可能报异常
        CopyOnWriteList写操作发生在快照上，不会发生异常。
        修复使用Iterator#remove删除元素，JDK1.8List#removeIf
4.不可变集合：
        不可变集合只能被读取，不能做任何修改，包括增加，删除，修改，从而保护不可变集合的安全。
        不可变集合仅仅是原集合的视图，原集合任何改动都会影响不可变集合。
        使用 JDK9 List#of 方法。使用 Guava immutable list两种方式防止上。
5.使用工具类Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方法，它的add/remove/clear方法会抛出
        UnsupportedOperationException异常。asList的返回对象是一个Arrays内部类，并没有实现集合的修改方法。
```
####[2.Map集合怎么也有这么多坑？一不小心又踩了好几个！](https://www.cnblogs.com/goodAndyxublog/p/12840460.html)
```markdown
1.不是所有的Map都能包含null
    HashMap只允许一个key为null,value允许为null;
    ConcurrentHashMap不允许key和value为null;
![](https://img2020.cnblogs.com/other/1419561/202005/1419561-20200507074454124-572637654.jpg) 
2.自定义对象为HashMap的key
    对象的相同属性值重复插入，不会被替代，会插入新的一对键值。
    所以如果需要使用自定义对象做为Map集合的key，那么一定记得重写hashCode与equals方法。
    使用lombok的EqualsAndHashCode自动重写hashCode与equals方法：
        当Map中置入自定义对象后，接着修改了商品金额。然后当我们想根据同一个对象取出Map中存的值时，却发现取不出来了。
        上面的问题主要是因为get方法是根据对象的hashcode计算产生的hash值取定位内部存储位置。
        当我们修改了对象属性后，导致对象hashcode产生的了变化，从而导致get方法无法获取到值。
3.错用ConcurrentHashMap导致线程不安全
    深入分析这个问题原因，实际上是因为第一步与第二步是一个组合逻辑，不是一个原子操作。
    ConcurrentHashMap只能保证这两步单的操作是个原子操作，线程安全。但是并不能保证两个组合逻辑线程安全，
    很有可能A线程刚通过get方法取到值，还未来得及加1，线程发生了切换，B线程也进来取到同样的值。
4.List集合这些坑，Map中也有
    Map 接口除了支持增删改查功能以外，还有三个特有的方法，能返回所有 key，返回所有的 value，返回所有 kv 键值对。
    // 返回 key 的 set 视图
    Set<K> keySet()；
    // 返回所有 value   Collection 视图
    Collection<V> values();
    // 返回 key-value 的 set 视图
    Set<Map.Entry<K, V>> entrySet();
    这三个方法创建返回新集合，底层其实都依赖的原有Map中数据，所以一旦Map中元素变动，就会同步影响返回的集合。
    另外这三个方法返回新集合，是不支持的新增以及修改操作的，但是却支持clear、remove 等操作。
    所以如果需要对外返回Map这三个方法产生的集合，建议再来个套娃。new ArrayList<>(map.values());
    最后再简单提一下，使用foreach方式遍历新增/删除Map中元素，也将会和List集合一样，抛出ConcurrentModificationException。
```
#### [3.还在用迭代器处理集合吗？试试Stream，真香](https://www.cnblogs.com/keatsCoder/p/12846233.html)
```markdown

```
#### [4.瞬间教你学会使用java中list的retainAll方法](https://www.cnblogs.com/jichi/p/12892150.html)
```markdown
当我们有两个list集合的时候，我们可以使用retainAll方法求得两个list集合的子集。
```

### 5.Java I/O
![](https://img2020.cnblogs.com/blog/1515111/202006/1515111-20200612142833827-43182011.png)
#### Java的I/O大概可以分成以下几类：
```markdown
Java 的 I/O 大概可以分成以下几类：
    磁盘操作：File
    字节操作：字节输入流:InputStream和字节输出流:OutputStream;字节缓冲区类：BufferedInputStream和BufferedOutputStream
    字符操作：字符输入流:Reader和字符输出流:Writer;字符缓冲区类BufferedReader和BufferedWriter。
    对象操作：Serializable
    网络操作：Socket
    新的输入/输出：NIO
按照流的流向分，可以分为输入流和输出流；
按照操作单元划分，可以划分为字节流和字符流；
InputStream/Reader: 所有的输入流的基类，前者是字节输入流，后者是字符输入流。
OutputStream/Writer: 所有输出流的基类，前者是字节输出流，后者是字符输出流。
Java提供了从字节流到字符流的转换流，分别是InputStreamReader和OutputStreamWriter，但没有从字符流到字节流的转换流。
 实际上：字符流=字节流+编码表
```
#### [面试必备：详解JavaI/O流，掌握这些就可以说精通了？](https://www.cnblogs.com/happyone/p/12663145.html)
#### Java I/O装饰者模式
```markdown
Java I/O 使用了装饰者模式来实现。以InputStream 为例，
InputStream是抽象组件；
FileInputStream是InputStream的子类，属于具体组件，提供了字节流的输入操作；
FilterInputStream属于抽象装饰者，装饰者用于装饰组件，为组件提供额外的功能。例如BufferedInputStream为FileInputStream提供缓存的功能。
```
#### Java的字节流，字符流和缓冲流对比探究
>> [Java的字节流，字符流和缓冲流对比探究](https://www.cnblogs.com/misterchaos/p/12985332.html)
```markdown
根据以上实验，可以总结得出，字节流和字符流具有以下区别：
在同样使用缓冲区的前提下，字节流比字符流的效率稍微高一点。对于频繁操作且每次输入输出的数据量较小时，使用缓冲区可以带来明显的效率提升。
操作对象上，字节流操作的基本单元为字节，字符流操作的基本单元为Unicode码元（字符）。
字节流通常用于处理二进制数据，实际上它可以处理任意类型的数据，但它不支持直接写入或读取Unicode码元。而字符流通常处理文本数据，它支持写入及读取Unicode码元。
从源码可以看出来，字节流默认不使用缓冲区，而字符流内部使用了缓冲区。
```
####
[系统学习 Java IO ---- 目录，概览](https://www.cnblogs.com/czwbig/p/10007201.html)
[Java NIO 学习笔记（七）----NIO/IO 的对比和总结](https://www.cnblogs.com/czwbig/p/10056804.html)
[JAVA I/O系统](https://www.cnblogs.com/fengyumeng/p/9952079.html)
[JAVA 探究NIO](https://www.cnblogs.com/fengyumeng/p/10041986.html#top)
###

## 其他
