package cn.offer2020.pbj.javabasis.java.basis1.java8_31;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @ClassName: OptionalDemo
 * @Author: pbj
 * @Date: 2020/5/31 08:37
 * @Description: TODO [【Java8新特性】不了解Optional类，简历上别说你懂Java8！！](https://www.cnblogs.com/binghe001/p/12995007.html)
 */
public class OptionalDemo {

    @Test
    public void demo() {
        //使用empty()方法创建一个空的Optional对象：
        Optional<String> empty = Optional.empty();
        //使用of()方法创建Optional对象：
        String name = "bac";
        Optional<String> opt = Optional.of(name);
        assertEquals("Optional[bac]", opt.toString());
        //传递给of()的值不可以为空，否则会抛出空指针异常。例如，下面的程序会抛出空指针异常。
        String name1 = null;
        Optional<String> opt1 = Optional.of(name1);
        //如果我们需要传递一些空值，那我们可以使用下面的示例所示。
        //使用ofNullable()方法，则当传递进去一个空值时，不会抛出异常，而只是返回一个空的Optional对象，如同我们用Optional.empty()方法一样。
        String name2 = null;
        Optional<String> opt2 = Optional.ofNullable(name);
        //我们可以使用这个isPresent()方法检查一个Optional对象中是否有值，只有值非空才返回true。
        Optional<String> opt3 = Optional.of("binghe");
        assertTrue(opt3.isPresent());

        Optional<String> opt4 = Optional.ofNullable(null);
        assertFalse(opt4.isPresent());
        //在Java8之前，我们一般使用如下方式来检查空值。
        if(name != null){
            System.out.println(name.length());
        }
        //在Java8中，我们就可以使用如下方式来检查空值了。
        Optional<String> opt5 = Optional.of("binghe");
        opt.ifPresent(name3 -> System.out.println(name.length()));
        //orElse()方法用来返回Optional对象中的默认值，它被传入一个“默认参数‘。如果对象中存在一个值，则返回它，否则返回传入的“默认参数”。
        String nullName = null;
        String name5 = Optional.ofNullable(nullName).orElse("binghe");
        assertEquals("binghe", name5);
        //orElseGet()与orElse()方法类似，但是这个函数不接收一个“默认参数”，而是一个函数接口。
        String nullName1 = null;
        String name6 = Optional.ofNullable(nullName1).orElseGet(() -> "binghe");
        assertEquals("binghe", name6);
        //二者有什么区别？要想理解二者的区别，首先让我们创建一个无参且返回定值的方法。
        String text = null;
        System.out.println("Using orElseGet:");
        String defaultText = Optional.ofNullable(text).orElseGet(this::getDefaultName);
        assertEquals("binghe", defaultText);

        System.out.println("Using orElse:");
        defaultText = Optional.ofNullable(text).orElse(getDefaultName());
        assertEquals("binghe", defaultText);

        //两个Optional对象中都不存在value，因此执行结果相同。

        //那么，当Optional对象中存在数据会发生什么呢？我们一起来验证下。
        String name7 = "binghe001";

        System.out.println("Using orElseGet:");
        String defaultName = Optional.ofNullable(name7).orElseGet(this::getDefaultName);
        assertEquals("binghe001", defaultName);

        System.out.println("Using orElse:");
        defaultName = Optional.ofNullable(name7).orElse(getDefaultName());
        assertEquals("binghe001", defaultName);
        //可以看到，当使用orElseGet()方法时，getDefaultName()方法并不执行，因为Optional中含有值，而使用orElse时则照常执行。所以可以看到，当值存在时，orElse相比于orElseGet，多创建了一个对象。

        //orElseThrow()方法当遇到一个不存在的值的时候，并不返回一个默认值，而是抛出异常。
        String nullName2 = null;
        String name8 = Optional.ofNullable(nullName2).orElseThrow( IllegalArgumentException::new);

        //get()方法表示是Optional对象中获取值。
        Optional<String> opt9 = Optional.of("binghe");
        String name9 = opt9.get();
        assertEquals("binghe", name9);
        //使用get()方法也可以返回被包裹着的值。但是值必须存在。当值不存在时，会抛出一个NoSuchElementException异常。
        Optional<String> opt10 = Optional.ofNullable(null);
        String name10 = opt10.get();
    }

    public String getDefaultName() {
        System.out.println("Getting Default Name");
        return "binghe";
    }
}
