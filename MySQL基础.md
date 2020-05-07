#MySQL学习面试总结
>> [CyC2018大佬]()
   [MySQL必知必会1-20章读书笔记](https://www.cnblogs.com/hwahe/p/12822943.html)
   [写一手好 SQL 很有必要](https://mp.weixin.qq.com/s?__biz=MzUxOTc4NjEyMw==&mid=2247485420&idx=1&sn=2dad5815b5cf5d65ac386cd115403de0&chksm=f9f51c08ce82951e7cff92cc3888104cac2e24a16f6c5f1ee701a359a5b7e16cbaa5f458aaab&mpshare=1&scene=23&srcid=&sharer_sharetime=1576124148148&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)
   [1000行MySQL学习笔记](https://mp.weixin.qq.com/s?__biz=MzIxNTQ0MDQxNg==&mid=2247486626&idx=1&sn=360e0ff2e280e800a5e6defb2cc5aabf&chksm=979901eda0ee88fbaf17f81b6cbf1d870aa59bd78ea2fcf25a91e9ca157f2c77f9d6f6bb7f95&mpshare=1&scene=23&srcid=&sharer_sharetime=1575470170943&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)
   [MySQL高手系列](https://www.cnblogs.com/itsoku123/category/1539183.html)
   [MySQL面试总结](https://www.cnblogs.com/canchi/p/12040744.html)
   [索引-建立框架篇](https://www.cnblogs.com/michael9/p/12144435.html)
## MySQL基础
[MySQL基础知识和常用命令总结](https://www.cnblogs.com/justisme/p/12797955.html)

[分享自己整理的MySQL基础笔记](https://www.nowcoder.com/discuss/353707)

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
### 索引 
#### B+Tree原理
```markdown
B Tree指的是Balance Tree，也就是平衡树。平衡树是一颗查找树，并且所有叶子节点位于同一层。
B+ Tree是基于B Tree和叶子节点顺序访问指针进行实现，它具有 B Tree的平衡性，并且通过顺序访问指针来提高区间查询的性能。
在B+Tree中，一个节点中的key从左到右非递减排列，如果某个指针的左右相邻key分别是keyi和keyi+1，且不为null，则该指针指向节点的所有key大于等于keyi且小于等于keyi+1。
红黑树等平衡树也可以用来实现索引，但是文件系统及数据库系统普遍采用 B+ Tree 作为索引结构，主要有以下两个原因：
（一）更少的查找次数：平衡树查找操作的时间复杂度和树高 h 相关，O(h)=O(logdN)，其中 d 为每个节点的出度。
（二）利用磁盘预读特性：为了减少磁盘 I/O 操作，磁盘往往不是严格按需读取，而是每次都会预读。
```
#### B+Tree索引
```markdown
B+Tree索引是大多数 MySQL 存储引擎的默认索引类型。
因为不再需要进行全表扫描，只需要对树进行搜索即可，所以查找速度快很多。
因为B+Tree的有序性，所以除了用于查找，还可以用于排序和分组。
可以指定多个列作为索引列，多个索引列共同组成键。
适用于全键值、键值范围和键前缀查找，其中键前缀查找只适用于最左前缀查找。如果不是按照索引列的顺序进行查找，则无法使用索引。
InnoDB 的 B+Tree 索引分为主索引和辅助索引。主索引的叶子节点 data 域记录着完整的数据记录，这种索引方式被称为聚簇索引。
因为无法把数据行存放在两个不同的地方，所以一个表只能有一个聚簇索引。
辅助索引的叶子节点的 data 域记录着主键的值，因此在使用辅助索引进行查找时，需要先查找到主键值，然后再到主索引中进行查找。
```
#### 哈希索引
```markdown
哈希索引能以 O(1) 时间进行查找，但是失去了有序性：无法用于排序与分组；只支持精确查找，无法用于部分查找和范围查找。
InnoDB存储引擎有一个特殊的功能叫“自适应哈希索引”，当某个索引值被使用的非常频繁时，会在B+Tree索引之上再创建一个哈希索引，
这样就让B+Tree索引具有哈希索引的一些优点，比如快速的哈希查找。
```
#### 全文索引
```markdown
MyISAM存储引擎支持全文索引，用于查找文本中的关键词，而不是直接比较是否相等。
查找条件使用 MATCH AGAINST，而不是普通的WHERE。全文索引使用倒排索引实现，它记录着关键词到其所在文档的映射。
InnoDB存储引擎在MySQL5.6.4版本中也开始支持全文索引。
```
#### 空间数据索引
```markdown
MyISAM 存储引擎支持空间数据索引（R-Tree），可以用于地理数据存储。空间数据索引会从所有维度来索引数据，
可以有效地使用任意维度来进行组合查询。必须使用 GIS 相关的函数来维护数据。
```
### 索引优化
[MySQL如何创建一个好索引？创建索引的5条建议](https://www.cnblogs.com/chenkeyu/p/12799207.html)

#### 1.独立的列
```markdown
在进行查询时，索引列不能是表达式的一部分，也不能是函数的参数，否则无法使用索引。
```
#### 2. 多列索引
```markdown
在需要使用多个列作为条件进行查询时，使用多列索引比使用多个单列索引性能更好。例如下面的语句中，最好把actor_id和film_id设置为多列索引。
```
#### 3. 索引列的顺序
```markdown
让选择性最强的索引列放在前面。索引的选择性是指：不重复的索引值和记录总数的比值。最大值为 1，此时每个记录都有唯一的索引与其对应。
选择性越高，每个记录的区分度越高，查询效率也越高。
```
#### 4. 前缀索引
```markdown
对于BLOB、TEXT和VARCHAR类型的列，必须使用前缀索引，只索引开始的部分字符。前缀长度的选取需要根据索引选择性来确定。
```
#### 5. 覆盖索引
```markdown
索引包含所有需要查询的字段的值。
具有以下优点：
    索引通常远小于数据行的大小，只读取索引能大大减少数据访问量。
    一些存储引擎（例如 MyISAM）在内存中只缓存索引，而数据依赖于操作系统来缓存。因此，只访问索引可以不使用系统调用（通常比较费时）。
    对于 InnoDB 引擎，若辅助索引能够覆盖查询，则无需访问主索引。
```
### 索引的优点
```markdown
1.大大减少了服务器需要扫描的数据行数。
2.帮助服务器避免进行排序和分组，以及避免创建临时表（B+Tree 索引是有序的，可以用于 ORDER BY 和GROUP BY 操作。
    临时表主要是在排序和分组过程中创建，不需要排序和分组，也就不需要创建临时表）。
3.将随机 I/O 变为顺序 I/O（B+Tree 索引是有序的，会将相邻的数据都存储在一起）。
```
### 查询性能优化
#### 1.使用Explain进行分析
```markdown
Explain 用来分析 SELECT 查询语句，开发人员可以通过分析 Explain 结果来优化查询语句。
比较重要的字段有：select_type : 查询类型，有简单查询、联合查询、子查询等,key : 使用的索引,rows : 扫描的行数。
```
#### 2.优化数据访问
```markdown
1. 减少请求的数据量
    只返回必要的列：最好不要使用 SELECT * 语句。
    只返回必要的行：使用LIMIT语句来限制返回的数据。
    缓存重复查询的数据：使用缓存可以避免在数据库中进行查询，特别在要查询的数据经常被重复查询时，缓存
    带来的查询性能提升将会是非常明显的。
2. 减少服务器端扫描的行数
    最有效的方式是使用索引来覆盖查询。
```
#### 3.重构查询方式
```markdown
1. 切分大查询 
    一个大查询如果一次性执行的话，可能一次锁住很多数据、占满整个事务日志、耗尽系统资源、阻塞很多小的但重要的查询。
2. 分解大连接查询    
    将一个大连接查询分解成对每一个表进行一次单表查询，然后在应用程序中进行关联，这样做的好处有：
    让缓存更高效。对于连接查询，如果其中一个表发生变化，那么整个查询缓存就无法使用。而分解后的多个查询，即使其中一个表发生变化，对其它表的查询缓存依然可以使用。
    分解成多个单表查询，这些单表查询的缓存结果更可能被其它查询使用到，从而减少冗余记录的查询。减少锁竞争；
    在应用层进行连接，可以更容易对数据库进行拆分，从而更容易做到高性能和可伸缩。
    查询本身效率也可能会有所提升。例如下面的例子中，使用IN()代替连接查询，可以让MySQL按照ID顺序进行查询，这可能比随机的连接要更高效。
```
### 存储引擎
```markdown
**InnoDB**是MySQL默认的事务型存储引擎，只有在需要它不支持的特性时，才考虑使用其它存储引擎。
实现了四个标准的隔离级别，默认级别是可重复读（REPEATABLE READ）。在可重复读隔离级别下，通过多版本并发控制（MVCC）+间隙锁（Next-Key Locking）防止幻影读。
主索引是聚簇索引，在索引中保存了数据，从而避免直接读取磁盘，因此对查询性能有很大的提升。
内部做了很多优化，包括从磁盘读取数据时采用的可预测性读、能够加快读操作并且自动创建的自适应哈希索引、能够加速插入操作的插入缓冲区等。
支持真正的在线热备份。其它存储引擎不支持在线热备份，要获取一致性视图需要停止对所有表的写入，而在读写混合场景中，停止写入可能也意味着停止读取。
**MyISAM**:设计简单，数据以紧密格式存储。对于只读数据，或者表比较小、可以容忍修复操作，则依然可以使用它。
提供了大量的特性，包括压缩表、空间数据索引等。
不支持事务。
不支持行级锁，只能对整张表加锁，读取时会对需要读到的所有表加共享锁，写入时则对表加排它锁。但在表有读取操作的同时，也可以往表中插入新的记录，这被称为并发插入（CONCURRENT INSERT）。
可以手工或者自动执行检查和修复操作，但是和事务恢复以及崩溃恢复不同，可能导致一些数据丢失，而且修复操作是非常慢的。
如果指定了 DELAY_KEY_WRITE 选项，在每次修改执行完成时，不会立即将修改的索引数据写入磁盘，而是会写到内存中的键缓冲区，
只有在清理键缓冲区或者关闭表的时候才会将对应的索引块写入磁盘。这种方式可以极大的提升写入性能，但是在数据库或者主机崩溃时会造成索引损坏，需要执行修复操作。
**比较**
事务：InnoDB 是事务型的，可以使用 Commit 和 Rollback 语句。
并发：MyISAM 只支持表级锁，而 InnoDB 还支持行级锁。
外键：InnoDB 支持外键。
备份：InnoDB 支持在线热备份。
崩溃恢复：MyISAM 崩溃后发生损坏的概率比 InnoDB 高很多，而且恢复的速度也更慢。
其它特性：MyISAM 支持压缩表和空间数据索引。
```
### 主从复制
```markdown
主要涉及三个线程：binlog 线程、I/O 线程和 SQL 线程。
    binlog 线程 ：负责将主服务器上的数据更改写入二进制日志（Binary log）中。
    I/O 线程 ：负责从主服务器上读取二进制日志，并写入从服务器的中继日志（Relay log）。
    SQL 线程 ：负责读取中继日志，解析出主服务器已经执行的数据更改并在从服务器中重放（Replay）。
```
### 读写分离
```markdown
主服务器处理写操作以及实时性要求比较高的读操作，而从服务器处理读操作。读写分离能提高性能的原因在于：
    主从服务器负责各自的读和写，极大程度缓解了锁的争用；
    从服务器可以使用 MyISAM，提升查询性能以及节约系统开销；
    增加冗余，提高可用性。
读写分离常用代理方式来实现，代理服务器接收应用层传来的读写请求，然后决定转发到哪个服务器。
```

## 优秀的博客文章
### [1.手把手教你分析Mysql死锁问题](https://www.cnblogs.com/jay-huaxiao/p/12685287.html)
```mysql
 select @@tx_isolation;  # 查看数据库隔离级别
 set autocommit=0;  # 自动提交关闭
 select * from information_schema.innodb_locks; # 查看锁情况
 show engine innodb status; # 查看最近一次死锁日志
 -- MySQL 中提供了两种封锁粒度：行级锁以及表级锁。应该尽量只锁定需要修改的那部分数据，而不是所有的资源。
```
#### 1.1共享锁与排他锁
```markdown
InnoDB 实现了标准的行级锁，包括两种：共享锁（简称 s 锁）、排它锁（简称 x 锁）。
共享锁（S锁）：允许持锁事务读取一行。
排他锁（X锁）：允许持锁事务更新或者删除一行。
```
#### 1.2 意向锁
```markdown
意向共享锁( IS 锁)：事务想要获得一张表中某几行的共享锁
意向排他锁( IX 锁)： 事务想要获得一张表中某几行的排他锁
```
#### 1.3 记录锁（Record Locks）
```markdown
记录锁是最简单的行锁，仅仅锁住一行。如：SELECT c1 FROM t WHERE c1 = 10 FOR UPDATE
记录锁永远都是加在索引上的，即使一个表没有索引，InnoDB也会隐式的创建一个索引，并使用这个索引实施记录锁。
会阻塞其他事务对其插入、更新、删除
```
#### 1.4 间隙锁（Gap Locks）
```markdown
间隙锁是一种加在两个索引之间的锁，或者加在第一个索引之前，或最后一个索引之后的间隙。
使用间隙锁锁住的是一个区间，而不仅仅是这个区间中的每一条数据。
间隙锁只阻止其他事务插入到间隙中，他们不阻止其他事务在同一个间隙上获得间隙锁，所以 gap x lock 和 gap s lock 有相同的作用。
```
#### 1.5 Next-Key Locks
```markdown
Next-key锁是记录锁和间隙锁的组合，它指的是加在某条记录以及这条记录前面间隙上的锁。
```
#### 1.6插入意向锁（Insert Intention）
```markdown
插入意向锁是在插入一行记录操作之前设置的一种间隙锁，这个锁释放了一种插入方式的信号，
亦即多个事务在相同的索引间隙插入时如果不是插入间隙中相同的位置就不需要互相等待。
```
#### 1.7 三级封锁协议
```markdown
**一级封锁协议**:事务T要修改数据A时必须加X(写)锁，直到T结束才释放锁。可以解决丢失修改问题。
**二级封锁协议**:在一级的基础上，要求读取数据A时必须加S(读)锁，读取完马上释放S(读)锁。可以解决读脏数据问题。
**三级封锁协议**:在二级的基础上，要求读取数据A时必须加S(读)锁，直到事务结束了才能释放S(读)锁。可以解决不可重复读的问题
```
#### 1.8 两段锁协议
```markdown
加锁和解锁分为两个阶段进行。
可串行化调度是指，通过并发控制，使得并发执行的事务结果与某个串行执行的事务结果相同。
事务遵循两段锁协议是保证可串行化调度的充分条件。
MySQL的InnoDB 存储引擎采用两段锁协议，会根据隔离级别在需要的时候自动加锁，并且所有的锁都是在同一时刻被释放，这被称为隐式锁定。
```
### [2.一文彻底读懂MySQL事务的四大隔离级别](https://www.cnblogs.com/jay-huaxiao/p/12639435.html)
#### 隔离级别
```markdown
未提交读（READ UNCOMMITTED）:事务中的修改，即使没有提交，对其它事务也是可见的。
提交读（READ COMMITTED）:一个事务只能读取已经提交的事务所做的修改。换句话说，一个事务所做的修改在提交之前对其它事务是不可见的。
可重复读（REPEATABLE READ）:保证在同一个事务中多次读取同样数据的结果是一样的。
可串行化（SERIALIZABLE）:强制事务串行执行。
```
### [3.Mysql面试题及千万级数据查询优化](https://www.cnblogs.com/lyn20141231/p/11742042.html)
### [4.数据库优化 - SQL优化](https://www.cnblogs.com/lyn20141231/p/11742042.html)
### [5.不就是SELECT COUNT语句吗，竟然能被面试官虐的体无完肤](https://www.cnblogs.com/hollischuang/p/11711778.html)
### 一个 SQL 执行的很慢，我们要分两种情况讨论：
```markdown
    https://www.cnblogs.com/kubidemanong/p/10734045.html
    
    1、大多数情况下很正常，偶尔很慢，则有如下原因
    
    (1)、数据库在刷新脏页，例如 redo log 写满了需要同步到磁盘。
    
    (2)、执行的时候，遇到锁，如表锁、行锁。
    
    2、这条 SQL 语句一直执行的很慢，则有如下原因。
    
    (1)、没有用上索引：例如该字段没有索引；由于对字段进行运算、函数操作导致无法用索引。
    
    (2)、数据库选错了索引。
```
### 1.InnoDB 引擎中的索引类型
[参考文献：聊一聊 InnoDB 引擎中的索引类型](https://www.cnblogs.com/jamaler/p/12222176.html)
[B-Tree 和 B+Tree 结构及应用，InnoDB 引擎， MyISAM 引擎](https://www.cnblogs.com/ITnoteforlsy/p/12228149.html)  
#### 总结
```
在 InnoDB 引擎中有三,在 InnoDB 引擎中使用 B+树来实现 B-Tree 索引种索引：
    B-Tree 索引 
    哈希索引
    全文索引
B-Tree 索引是 InnoDB 引擎的默认索引,在 InnoDB 引擎中使用 B+树来实现 B-Tree 索引.
    B-Tree 索引中又有主键索引和普通索引
        主键索引也叫聚集索引，是按照主键构建得一棵B+树，只要建立了主键就会自动加上索引，
        主键索引得特点是：叶子节点上存放着整张表的行记录数据，所以叶子节点也叫数据页。
        普通索引也叫二级索引，跟主键索引的主要区别在于叶子结点没有存放行记录的全部数据，
        只包含了需要的键值，还有一个标签，用来告诉存储引擎在哪里可以找到这行数据。
        普通索引因为行记录里没有数据的全部信息，在使用普通索引查询时，需要现在普通索引树上搜索一遍，再回到主键索引树上查询到需要的信息，这个过程也叫回表。
    每一张表其实就是多个B+树，树结点的key值就是某一行的主键，value是该行的其他数据。新建索引就是新增一个B+树，查询不走索引就是遍历主B+树。
```
### 2.InnoDB 引擎中的这些索引策略
[参考文献：聊一聊 InnoDB 引擎中的这些索引策略](https://www.cnblogs.com/jamaler/p/12239558.html)
#### 总结
```
覆盖索引:是指在普通索引树中可以得到查询的结果，不需要在回到主键索引树中再次搜索。
    减少树的搜索次数，显著提升查询性能。
    索引是按照值的顺序存储，所以对于 I/O 密集型的范围查询比随机从磁盘中读取每一行的 I/O 要少很多。
    索引的条目远小于数据的条目，在索引树上读取会极大的减小数据库的访问量。
最左前缀原则:是建立在联合索引之上的，如果我们建立了联合索引，我们不需要使用索引的全部定义，
只要用到了索引中的最左边的那个字段就可以使用这个索引，这就是 B-tree 索引支持最左前缀原则。
索引下推优化:是 MySQL 5.6 引入的， 可以在索引遍历过程中，对索引中包含的字段先做判断，直接过滤掉不满足条件的记录，减少回表次数。
```
### 3.埋在MySQL数据库应用中的17个关键问题！
[埋在MySQL数据库应用中的17个关键问题！](https://mp.weixin.qq.com/s?__biz=MzI1NDQ3MjQxNA==&mid=2247487762&idx=1&sn=dc71279f36959b62b63b48108cf8dca3&chksm=e9c5e8a3deb261b5e755d3ace7f2e4c6d69ac20e2510a2f7bd7a32adaf1413b053e3ab411aa7&mpshare=1&scene=23&srcid=1204BeiYSpXyUpL3fQ6wUW0P#rd)
```
MySQL的一些问题：性能优化、高可用性、强一致性、安全、备份、集群、横向扩展、纵向扩展、负载均衡、读写分离等
一：单Master：
    数据备份：冷备，热备，温备
    数据还原
    备份监控
    数据文件远程存储
二：一主一从：初衷是系统性能和系统高可用性问题
    数据备份，性能优化、读写分离、负载均衡
三：一主n从：可用性、一致性、性能中一种或者多种的要求比较高
四：横向集群
    横向集群主要是从业务特性的角度对系统进行切分，最彻底就是切分成了各个子系统，子系统之间通过一些数据同步的方案来把一些核心数据进行共享，以避免跨库调用跨库join。
五、纵向集群
```
[数据库并发处理 - 上的一把好"锁"](https://www.cnblogs.com/michael9/p/12167434.html)

[索引很难么？带你从头到尾捋一遍MySQL索引结构，不信你学不会！](https://www.cnblogs.com/javazhiyin/p/12016500.html)

[后端程序员必备：书写高质量SQL的30条建议](https://www.cnblogs.com/jay-huaxiao/p/12546973.html)

[教你如何迅速秒杀掉：99%的海量数据处理面试题](https://blog.csdn.net/v_july_v/article/details/7382693)

[很用心的为你写了 9 道 MySQL 面试题](https://mp.weixin.qq.com/s?__biz=MzUyNjQxNjYyMg==&mid=2247488127&idx=4&sn=040019b9991f9d62dcb0ded7d6cb7d1a&chksm=fa0e7dfecd79f4e8e2ab5b0e84ce7efa25589ebbb9eecf1b51d5413bc7edfc501a903c859201&mpshare=1&scene=23&srcid=&sharer_sharetime=1587272608819&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)

## LeetCode MySQL题目
```mysql
-- 175,181,183,184,176,177,178,180,626,
```
### 182. 查找重复的电子邮箱
```mysql
-- 编写一个 SQL 查询，查找 Person 表中所有重复的电子邮箱。
-- 解法1
select email from person group by email having count(email)>1

--解法2
select email from (select count(1) as t,email from person group by email)r  where r.t>1;

-- 解法3
select distinct(p1.Email) from Person p1  
    join Person  p2 on p1.Email = p2.Email AND p1.Id!=p2.Id
```
### 196. 删除重复的电子邮箱
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
### 595. 大的国家
```mysql
-- 如果一个国家的面积超过300万平方公里，或者人口超过2500万，那么这个国家就是大国家。
select name,population,area from World where area >3000000 or population >25000000;
select name,population,area from World where area > 3000000 
    UNION 
select name,population,area from World where population > 25000000;
```
### 627.交换工资
```mysql
-- 给定一个salary表，如下所示，有m=男性和f=女性的值。交换所有的f和m值（例如，将所有f值更改为m，反之亦然）。
-- 要求只使用一个更新（Update）语句，并且没有中间的临时表。
update salary set sex = if(sex = 'm','f','m');
update salary
    set sex = (case sex
                when 'm' then 'f'
                else 'm'
            end
            );
update salary set sex = char ( ASCII(sex) ^ ASCII('m') ^ ASCII('f'));
```
### 620. 有趣的电影
```mysql
select * from cinema where id%2=1 and description!= 'boring' order by rating DESC;
select * from cinema where id%2=1 and description <> 'boring' order by rating DESC;
```
### 596. 超过5名学生的课
```mysql
#共三种写法
#最朴实的写法，共三层查询，先利用 DISTINCT 去掉重复记录得到表 A，再利用 GROUP BY 为 CLASS 分组，然
#后用 COUNT() 统计每组个数得到表 B，最后在最外层限定数量 >=5 查到结果
SELECT B.CLASS								#最外层
	FROM (SELECT A.CLASS,COUNT(A.CLASS) C          #第二层查询，得到具有 CLASS、COUNT(CLASS) 的表 B
		FROM (SELECT DISTINCT *				#第三层查询，去重得到表 A
			FROM COURSES) A
	GROUP BY A.CLASS) B						#分组
	WHERE B.C >= 5;							#条件

#稍微优化，两层查询，主要是因为用了 HAVING 省了一层查询
SELECT A.CLASS					#最外层
	FROM (SELECT DISTINCT *		#第二层查询，去重得到表 a
		FROM COURSES) A
	GROUP BY A.CLASS			        #分组
	HAVING COUNT(A.CLASS) >= 5;	#利用 COUNT() 计算每组个数并筛选

#极致优化，一层查询，利用 GROUP BY 为 CLASS 分组后，直接用 COUNT() 统计每组学生个数，在统计前先用
# DISTINCT 去掉重复学生
SELECT CLASS
	FROM COURSES
	GROUP BY CLASS							#分组
	HAVING COUNT(DISTINCT STUDENT) >= 5;          #利用 COUNT() 统计每门课 STUDENT 的个数，同时利
```
[借助leetcode题目来了解BFS和DFS](https://www.cnblogs.com/yhycoder/p/12786423.html#shwtop)
[一份热乎乎的腾讯后端面试真题](https://www.cnblogs.com/jay-huaxiao/p/12776714.html)
[Java面试系列第2篇-Object类中的方法](https://www.cnblogs.com/extjs4/p/12772027.html)
[Java岗结束春招，总结面经回馈牛客，祝大家都能得偿所愿~](https://www.nowcoder.com/discuss/416945?type=2)
[https://github.com/2020GetGoodOffer/test]
[各大公司面试题分类整理](https://www.cnblogs.com/lvmengtian/p/12796820.html)
