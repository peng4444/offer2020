#MySQL基础总结
##参考资料
[分享自己整理的MySQL基础笔记](https://www.nowcoder.com/discuss/353707)
[写一手好 SQL 很有必要](https://mp.weixin.qq.com/s?__biz=MzUxOTc4NjEyMw==&mid=2247485420&idx=1&sn=2dad5815b5cf5d65ac386cd115403de0&chksm=f9f51c08ce82951e7cff92cc3888104cac2e24a16f6c5f1ee701a359a5b7e16cbaa5f458aaab&mpshare=1&scene=23&srcid=&sharer_sharetime=1576124148148&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)
[1000行MySQL学习笔记](https://mp.weixin.qq.com/s?__biz=MzIxNTQ0MDQxNg==&mid=2247486626&idx=1&sn=360e0ff2e280e800a5e6defb2cc5aabf&chksm=979901eda0ee88fbaf17f81b6cbf1d870aa59bd78ea2fcf25a91e9ca157f2c77f9d6f6bb7f95&mpshare=1&scene=23&srcid=&sharer_sharetime=1575470170943&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)
[MySQL高手系列](https://www.cnblogs.com/itsoku123/category/1539183.html)
[MySQL面试总结](https://www.cnblogs.com/canchi/p/12040744.html)
[索引-建立框架篇](https://www.cnblogs.com/michael9/p/12144435.html)
### 事务
```markdown
事务指的是满足 ACID 特性的一组操作，可以通过 Commit 提交一个事务，也可以使用 Rollback 进行回滚。
**原子性**:事务被视为不可分割的最小单元，事务的所有操作要么全部提交成功，要么全部失败回滚。
回滚可以用回滚日志来实现，回滚日志记录着事务所执行的修改操作，在回滚时反向执行这些修改操作即可。
**一致性**:数据库在事务执行前后都保持一致性状态。在一致性状态下，所有事务对一个数据的读取结果都是相同的。
**隔离性**:一个事务所做的修改在最终提交以前，对其它事务是不可见的。
**持久性**:一旦事务提交，则其所做的修改将会永远保存到数据库中。即使系统发生崩溃，事务执行的结果也不能丢失。
```
### 并发一致性问题
```markdown
在并发环境下，事务的隔离性很难保证，因此会出现很多并发一致性问题。丢失修改、读脏数据、不可重复读、幻影读
```

# MySQL学习总结
## [1.手把手教你分析Mysql死锁问题](https://www.cnblogs.com/jay-huaxiao/p/12685287.html)
```mysql
 select @@tx_isolation;  # 查看数据库隔离级别
 set autocommit=0;  # 自动提交关闭
 select * from information_schema.innodb_locks; # 查看锁情况
 show engine innodb status; # 查看最近一次死锁日志
 -- MySQL 中提供了两种封锁粒度：行级锁以及表级锁。应该尽量只锁定需要修改的那部分数据，而不是所有的资源。
```
### 1.1共享锁与排他锁
```markdown
InnoDB 实现了标准的行级锁，包括两种：共享锁（简称 s 锁）、排它锁（简称 x 锁）。
共享锁（S锁）：允许持锁事务读取一行。
排他锁（X锁）：允许持锁事务更新或者删除一行。
```
### 1.2 意向锁
```markdown
意向共享锁( IS 锁)：事务想要获得一张表中某几行的共享锁
意向排他锁( IX 锁)： 事务想要获得一张表中某几行的排他锁
```
### 1.3 记录锁（Record Locks）
```markdown
记录锁是最简单的行锁，仅仅锁住一行。如：SELECT c1 FROM t WHERE c1 = 10 FOR UPDATE
记录锁永远都是加在索引上的，即使一个表没有索引，InnoDB也会隐式的创建一个索引，并使用这个索引实施记录锁。
会阻塞其他事务对其插入、更新、删除
```
### 1.4 间隙锁（Gap Locks）
```markdown
间隙锁是一种加在两个索引之间的锁，或者加在第一个索引之前，或最后一个索引之后的间隙。
使用间隙锁锁住的是一个区间，而不仅仅是这个区间中的每一条数据。
间隙锁只阻止其他事务插入到间隙中，他们不阻止其他事务在同一个间隙上获得间隙锁，所以 gap x lock 和 gap s lock 有相同的作用。
```
### 1.5 Next-Key Locks
```markdown
Next-key锁是记录锁和间隙锁的组合，它指的是加在某条记录以及这条记录前面间隙上的锁。
```
### 1.6插入意向锁（Insert Intention）
```markdown
插入意向锁是在插入一行记录操作之前设置的一种间隙锁，这个锁释放了一种插入方式的信号，
亦即多个事务在相同的索引间隙插入时如果不是插入间隙中相同的位置就不需要互相等待。
```
### 1.7 三级封锁协议
```markdown
**一级封锁协议**:事务T要修改数据A时必须加X(写)锁，直到T结束才释放锁。可以解决丢失修改问题。
**二级封锁协议**:在一级的基础上，要求读取数据A时必须加S(读)锁，读取完马上释放S(读)锁。可以解决读脏数据问题。
**三级封锁协议**:在二级的基础上，要求读取数据A时必须加S(读)锁，直到事务结束了才能释放S(读)锁。可以解决不可重复读的问题
```
### 1.8 两段锁协议
```markdown
加锁和解锁分为两个阶段进行。
可串行化调度是指，通过并发控制，使得并发执行的事务结果与某个串行执行的事务结果相同。
事务遵循两段锁协议是保证可串行化调度的充分条件。
MySQL的InnoDB 存储引擎采用两段锁协议，会根据隔离级别在需要的时候自动加锁，并且所有的锁都是在同一时刻被释放，这被称为隐式锁定。
```
## [2.一文彻底读懂MySQL事务的四大隔离级别](https://www.cnblogs.com/jay-huaxiao/p/12639435.html)
### 隔离级别
```markdown
未提交读（READ UNCOMMITTED）:事务中的修改，即使没有提交，对其它事务也是可见的。
提交读（READ COMMITTED）:一个事务只能读取已经提交的事务所做的修改。换句话说，一个事务所做的修改在提交之前对其它事务是不可见的。
可重复读（REPEATABLE READ）:保证在同一个事务中多次读取同样数据的结果是一样的。
可串行化（SERIALIZABLE）:强制事务串行执行。
```
## [3.Mysql面试题及千万级数据查询优化](https://www.cnblogs.com/lyn20141231/p/11742042.html)
## [4.数据库优化 - SQL优化](https://www.cnblogs.com/lyn20141231/p/11742042.html)
## [5.不就是SELECT COUNT语句吗，竟然能被面试官虐的体无完肤](https://www.cnblogs.com/hollischuang/p/11711778.html)


[数据库并发处理 - 上的一把好"锁"](https://www.cnblogs.com/michael9/p/12167434.html)
[索引很难么？带你从头到尾捋一遍MySQL索引结构，不信你学不会！](https://www.cnblogs.com/javazhiyin/p/12016500.html)
## 优秀的博客文章
[后端程序员必备：书写高质量SQL的30条建议](https://www.cnblogs.com/jay-huaxiao/p/12546973.html)

[教你如何迅速秒杀掉：99%的海量数据处理面试题](https://blog.csdn.net/v_july_v/article/details/7382693)

[很用心的为你写了 9 道 MySQL 面试题](https://mp.weixin.qq.com/s?__biz=MzUyNjQxNjYyMg==&mid=2247488127&idx=4&sn=040019b9991f9d62dcb0ded7d6cb7d1a&chksm=fa0e7dfecd79f4e8e2ab5b0e84ce7efa25589ebbb9eecf1b51d5413bc7edfc501a903c859201&mpshare=1&scene=23&srcid=&sharer_sharetime=1587272608819&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)

# LeetCode MySQL题目
## 196. 删除重复的电子邮箱
```mysql
DELETE p1.* from Person p1, Person p2 where p1.Email = p2.Email AND p1.Id>p2.Id;
delete p1 from (Person as p1 left join Person as p2 on p1.Email = p2.Email) where p1.Id = p2.Id;
delete from Person where Id not in (
    select Id from(
        select MIN(Id) as Id
        from Person
        GROUP BY Email
    ) as temp
);
```
