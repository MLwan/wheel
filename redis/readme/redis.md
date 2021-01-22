# Redis

## 一、NoSQL

### 1.1概述

关系型数据库是基于关系表的数据库，最终会将数据持久化到磁盘上

而nosql数据库是基于特殊的结构，并将数据存储到内存的数据库

从性能上而言，nosql数据库	要优于关系型数据库

从安全性上而言关系型数据库要优于nosql数据库

所以在实际开发中一个项目中nosql和关系型数据库会一起使用，达到性能和安全性的双保证

### 1.2作用

互联网三高：高并发、高负载、高扩展

1、对数据库高并发带来硬盘读写的需求：实时生成动态页面和信息对，当达到每秒上万次请求，硬盘IO已经无法承受。

2、对海量数据的高效率存储和访问的需求：大数据带来表操作的效率低下。

3、对数据库的高可扩展性和高可用性的需求：当用户量和访问量剧增，需要停机维护和数据迁移对24小时提供服务的网站来说非常痛苦。

### 1.3主流NoSQL产品

![img](F:\wheel\redis\readme\img\1.png)

NoSQL数据库的四大分类：

键值（key-value）存储数据库

相关产品：Tokyo Cabinet/Tyrant、Memcached(纯内存的nosql数据库)、**Redis（持久化的nosql数据库，支持内存又支持磁盘）**、Voldemort、Berkeley DB

典型应用：内容缓存，主要用于处理大量数据的高访问负载

数据模型：一系列键值对

优势：快速查询

劣势：存储的数据缺少结构化

 

列式存储数据库

相关产品：Cassandra（360）、**Hbase（新浪，小公司不用，月薪1w5以下不用玩）**、Riak

典型应用：分布式的文件系统

数据模型：以列簇式存储，将同一列数据存在一起

优势：查找速度快，可扩展性强，更容易进行分布式扩展

劣势：功能相对局限

 

文档型数据库

相关产品：CouchDB、**MongoDB（有潜力，灵活）**

典型应用：web应用（与key-value类似，value是结构化的）

数据模型：一系列键值对

优势：数据结构要求不严格

劣势：查询性能不高，而且缺乏统一的查询语法

 

图形（Graph）数据库

相关产品：Neo4J、InfoGrid、Infinite Graph

典型应用：社交网络

数据模型：图结构

优势：利用图结构相关算法

劣势：需要对整个图作计算才能得出结果，不容易做分布式的集群方案

### 1.4NoSQL特点

在大数据存取上具备关系型数据库无法比拟的性能优势:

1、 易扩展

​	去掉关系型数据的关系特性，也就是数据间无关系

2、大数据量，高性能

​	数据无关系、数据库结构简单

​    在内存中运行

3、灵活的数据模型

​	支持多种数据结构的存储

4、高可用

​	事先高可用的架构对性能影响不大，如Cassandra、HBase模型，通过复制模型也能实现高可用

5、存储

​	支持数据持久化存储，可以将数据存储在磁盘中，机器重启数据将从磁盘重新加载数据；项目内引入



## 二、Redis概述

### 2.1由来

Redis是用C语言开发的一个开源的高性能键值对（key-value）数据库。它通过提供多种键值数据类型来适应不同场景下的存储需求，目前为止Redis支持的值数据类型如下：

1、字符串（String）

2、哈希(hash:Map)

3、字符串列表（list）

4、字符串集合(set)

5、有序字符串集合(sorted set)

官方提供测试数据：50个并发执行100000个请求，读的速度是110000次/s,写的速度是81000次/s。数据仅供参考，根据服务器配置会有不同结果。

### 2.2应用场景

![img](F:\wheel\redis\readme\img\图片1.png)

# 3 Redis安装和使用

## 3.1 Redis在Linux上安装

1、安装redis编译的c环境，`yum install gcc-c++`

2、将redis-3.0.0.tar.gz上传到Linux系统中

3、解压到/usr/local下  `tar -xvf redis-2.6.16.tar.gz -C /usr/local`

4、进入redis-2.6.16目录 使用make命令编译redis

5、在redis-2.6.16目录中 使用`make PREFIX=/usr/local/redis install`命令安装			redis到/usr/local/redis中

6、拷贝redis-2.6.16中的redis.conf到安装目录redis中

7、redis 在bin下执行命令`redis-server redis.conf`

(前台启动服务器可以在bin下执行` ./redis-server`)

8、在bin下执行 `./redis-cli`进入redis客户端 进行操作

 

![img](img\wps816.tmp.jpg) 

9、如需远程连接redis，需配置redis端口6379在linux防火墙中开发

`/sbin/iptables -I INPUT -p tcp --dport 6379 -j ACCEPT`

`/etc/rc.d/init.d/iptables save`

​	10、退出客户端：`exit`

​	11、关闭服务器：

方式一：查看进程然后将其关闭

![img](img\wps827.tmp.jpg) 

方式二：

![img](img\wps828.tmp.jpg) 

  ![img](img\wps829.tmp.jpg) 



启动后看到如上欢迎页面，但此窗口不能关闭，窗口关闭就认为redis也关闭了(类似Tomcat通过bin下的startup.bat的方式)

解决方案：可以通过修改配置文件 配置redis后台启动，即服务器启动了但不会	穿件控制台窗口

将redis.conf文件中的daemonize从false修改成true表示后台启动

使用命令查看6379端口是否启动ps -ef | grep redis

## 3.2 java使用jedis操作redis

### 3.2.1 导入jar包

![img](file:///C:\Users\zhanwang\AppData\Local\Temp\ksohtml\wps6D09.tmp.jpg) 

### 3.2.2 jedis入门案例及连接池简单使用

![img](file:///C:\Users\zhanwang\AppData\Local\Temp\ksohtml\wps6D0A.tmp.jpg) 

![img](file:///C:\Users\zhanwang\AppData\Local\Temp\ksohtml\wps6D0B.tmp.jpg) 

### 3.2.3 获得jedis连接池的工具类

![img](file:///C:\Users\zhanwang\AppData\Local\Temp\ksohtml\wps6D0C.tmp.jpg) 

# 4 Redis的数据结构

## 4.1 定义key注意事项

其中的key是字符串类型，尽可能满足如下几点：

1）key不要太长，最好不要操作1024个字节，这不仅会消耗内存还会降低查找		效率

2）key不要太短，如果太短会降低key的可读性

3）在项目中，key最好有一个统一的命名规范（根据企业的需求）

## 4.2 存储String

### 4.2.1 常用命令

**1）*****\*set key value\****：设定key持有指定的字符串value，如果该key存在则进行覆盖	操作。总是返回”OK”

**2）*****\*get key\****：获取key的value。如果与该key关联的value不是String类型，redis	将返回错误信息，因为get命令只能用于获取String value；如果该key不存在，返	回null。

```shell
127.0.0.1:6379> get hash:player
(error) WRONGTYPE Operation against a key holding the wrong kind of value
127.0.0.1:6379> set key1 value1
OK
127.0.0.1:6379> get key1
"value1"
```

**3）*****\*getset key value\****：先获取该key的值，然后在设置该key的值。

```shell
127.0.0.1:6379> getset key1 valuedemo
"value1"
127.0.0.1:6379> get key1
"valuedemo"
```

4）***\*incr key\****：将指定的key的value原子性的递增1.如果该key不存在，其初始值	为0，在incr之后其值为1。如果value的值不能转成整型，如hello，该操作将执	行失败并返回相应的错误信息。

5）***\*decr key\****：将指定的key的value原子性的递减1.如果该key不存在，其初始值	为0，在incr之后其值为-1。如果value的值不能转成整型，如hello，该操作将执	行失败并返回相应的错误信息。

```shell
127.0.0.1:6379> set num 0
OK
127.0.0.1:6379> get num
"0"
127.0.0.1:6379> incr num
(integer) 1
127.0.0.1:6379> decr num
(integer) 0
127.0.0.1:6379> get numdemo
(nil)
127.0.0.1:6379> incr numdemo
(integer) 1
```

6）***\*incrby key increment\****：将指定的key的value原子性增加increment，如果该	key不存在，器初始值为0，在incrby之后，该值为increment。如果该值不能转成	整型，如hello则失败并返回错误信息

7）***\*decrby key decrement\****：将指定的key的value原子性减少decrement，如果	该key不存在，器初始值为0，在decrby之后，该值为decrement。如果该值不能	转成整型，如hello则失败并返回错误信息

```shell
127.0.0.1:6379> incrby num 6
(integer) 6
127.0.0.1:6379> decrby num 4
(integer) 2
```

8）***\*append key value\****：如果该key存在，则在原有的value后追加该值；如果该	key	不存在，则重新创建一个key/value

```shell
127.0.0.1:6379> get numappend
(nil)
127.0.0.1:6379> append numappend 8
(integer) 1
127.0.0.1:6379> append keyappend abc
(integer) 3
127.0.0.1:6379> get numappend
"8"
127.0.0.1:6379> get keyappend
"abc"
```



## 4.3 存储hash

### 4.3.1 概述

Redis中的Hashes类型可以看成具有String Key和String Value的map容器。所以该类型非常适合于存储值对象的信息。如Username、Password和Age等。如果Hash中包含很少的字段，那么该类型的数据也将仅占用很少的磁盘空间。每一个Hash	可以存储4294967295个键值对。

![img](file:///C:\Users\zhanwang\AppData\Local\Temp\ksohtml\wpsE5DE.tmp.jpg) 

### 4.3.2 常用命令

1）***\*hset key field value\****：为指定的key设定field/value对（键值对）。

2）***\*hgetall key：\****获取key中的所有filed-vaule

```shell
127.0.0.1:6379> hset hsetdemo name zhanw
(integer) 1
127.0.0.1:6379> hset hsetdemo age 2021
(integer) 1
127.0.0.1:6379> hset hsetdemo have tecnolegy
(integer) 1
127.0.0.1:6379> hgetall hsetdemo
1) "name"
2) "zhanw"
3) "age"
4) "2021"
5) "have"
6) "tecnolegy"
```

 

3）***\*hget key field\****：返回指定的key中的field的值

```shell
127.0.0.1:6379> hget hsetdemo have
"tecnolegy"
```

 

4）***\*hmset key fields\****：设置key中的多个filed/value

```shell
127.0.0.1:6379> hmset hsetdemo2 name baron age 2021
OK
127.0.0.1:6379> hgetall hsetdemo2
1) "name"
2) "baron"
3) "age"
4) "2021"
```

​	

5）***\*hmget key fileds\****：获取key中的多个filed的值

​	![img](file:///C:\Users\zhanwang\AppData\Local\Temp\ksohtml\wpsE5E2.tmp.jpg)

6）hdel key field[field:…]:可以删除一个或多个字段，返回值是被删除字段个数

​	![img](file:///C:\Users\zhanwang\AppData\Local\Temp\ksohtml\wpsE5E3.tmp.jpg)

7）hdel key:删除整个list

​	![img](file:///C:\Users\zhanwang\AppData\Local\Temp\ksohtml\wpsE5F3.tmp.jpg)

8）***\*hincrby key field increment\****：设置key中filed的值增加increment，如：age		增加20

​	![img](file:///C:\Users\zhanwang\AppData\Local\Temp\ksohtml\wpsE5F4.tmp.jpg)

9）***\*hexists key field\****：判	断指定的key中的filed是否存在

![img](file:///C:\Users\zhanwang\AppData\Local\Temp\ksohtml\wpsE5F5.tmp.jpg) 

10）***\*hlen key\****：获取key所包含的field的数量

![img](file:///C:\Users\zhanwang\AppData\Local\Temp\ksohtml\wpsE5F6.tmp.jpg) 

11）hkeys key:获得所有的key

![img](file:///C:\Users\zhanwang\AppData\Local\Temp\ksohtml\wpsE5F7.tmp.jpg) 

12）havs key:获得所有的value

![img](file:///C:\Users\zhanwang\AppData\Local\Temp\ksohtml\wpsE5F8.tmp.jpg) 

## 4.4 存储list

### 4.4.1 概述

在Redis中，List类型是按照插入顺序排序的字符串链表。和数据结构中的普通链表一样，我们可以在其头部(left)和尾部(right)添加新的元素。在插入时，如果该键并不存在，Redis将为该键创建一个新的链表。与此相反，如果链表中所有的元素均被移	除，那么该键也将会被从数据库中删除。List中可以包含的最大元素数量是4294967295。

从元素插入和删除的效率视角来看，如果我们是在链表的两头插入或删除元素，这将会是非常高效的操作，即使链表中已经存储了百万条记录，该操作也可以在常量时间内完成。然而需要说明的是，如果元素插入或删除操作是作用于链表中间，那将会是非常低效的。

![img](file:///C:\Users\zhanwang\AppData\Local\Temp\ksohtml\wps7F6B.tmp.jpg) 

### 4.4.2 常用命令

**1）*****\*lpush key value1 value2...\****：在指定的key所关联的list的头部插入所有的values，如果该key不存在，该命令在插入的之前创建一个与该key关联的空链表，之后再向该链表的头部插入数据。插入成功，返回元素的个数。

```shell
127.0.0.1:6379> lpush listdemo list1 list2 list3
(integer) 3 
```



**2）*****\*rpush key value1、value2…\****：在该list的尾部添加元素

```shell
 127.0.0.1:6379> rpush listdemo rlist
(integer) 4
```



**3）*****\*lrange key start end\****：获取链表中从start到end的元素的值，start、end可	为负数，若为-1则表示链表尾部的元素，-2则表示倒数第二个，依次类推… 

```shell
127.0.0.1:6379> lrange listdemo 1 2
1) "list2"
2) "list1"
127.0.0.1:6379> lrange listdemo 2 3
1) "list1"
2) "rlist"
127.0.0.1:6379> lrange listdemo 0 1
1) "list3"
2) "list2"
```



4）**\*lpushx key value\***：仅当参数中指定的key存在时（如果与key管理的list中没	有值时，则该key是不存在的）在指定的key所关联的list的头部插入value。

5）***\*rpushx key value\****：在该list的尾部添加元素



|      |                                                              |
| ---- | ------------------------------------------------------------ |
|      | ![img](file:///C:\Users\zhanwang\AppData\Local\Temp\ksohtml\wps7F6F.tmp.jpg) |

6）***\*lpop key\****：返回并弹出指定的key关联的链表中的第一个元素，即头部元素。



7）***\*rpop key\****：从尾部弹出元素。



|      |                                                              |
| ---- | ------------------------------------------------------------ |
|      | ![img](file:///C:\Users\zhanwang\AppData\Local\Temp\ksohtml\wps7F7F.tmp.jpg) |

 





|      |                                                              |
| ---- | ------------------------------------------------------------ |
|      | ![img](file:///C:\Users\zhanwang\AppData\Local\Temp\ksohtml\wps7F80.tmp.jpg) |

8）***\*rpoplpush resource destination\****：将链表中的尾部元素弹出并添加到头部

???

9）***\*llen key\****：返回指定的key关联的链表中的元素的数量。

```shell
127.0.0.1:6379> llen listdemo
(integer) 4
127.0.0.1:6379> lrange listdemo 0 4
1) "list3"
2) "list2"
3) "list1"
4) "rlist"
```



10）***\*lset key index value\****：设置链表中的index的脚标的元素值，0代表链表的头元	素，-1代表链表的尾元素。

```shell
127.0.0.1:6379> lset listdemo 1 insert1
OK
127.0.0.1:6379> lrange listdemo 0 5
1) "list3"
2) "insert1"
3) "list1"
4) "rlist"
```

注：insert1替换原来list2。并非插入了一个



11）***\*lrem key count value\****：删除count个值为value的元素，如果count大于0，从头向尾遍历并删除count个值为value的元素，如果count小于0，则从尾向头遍历并删除。如果count等于0，则删除链表中所有等于value的元素。

```shell
127.0.0.1:6379> lrem listdemo 1 list1
(integer) 1
127.0.0.1:6379> lrange listdemo 0 4
1) "list3"
2) "insert1"
3) "rlist"
```

 

12）***\*linsert key before|after pivot value\****：在pivot元素前或者后插入value这个	元素。

```shell
127.0.0.1:6379> linsert listdemo before insert1 insert0
(integer) 4
127.0.0.1:6379> lrange listdemo 0 4
1) "list3"
2) "insert0"
3) "insert1"
4) "rlist"
```



## 4.5 存储set

## 4.6 存储sortedset

# 5 keys的通用命令

![img](file:///C:\Users\zhanwang\AppData\Local\Temp\ksohtml\wps1E14.tmp.jpg) 

![img](file:///C:\Users\zhanwang\AppData\Local\Temp\ksohtml\wps1E15.tmp.jpg) 

![img](file:///C:\Users\zhanwang\AppData\Local\Temp\ksohtml\wps1E16.tmp.jpg) 

![img](file:///C:\Users\zhanwang\AppData\Local\Temp\ksohtml\wps1E17.tmp.jpg) 

清空redis缓存

```shell
127.0.0.1:6379> flushall
OK
```

