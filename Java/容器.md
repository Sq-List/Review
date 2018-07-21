## 容器
[参考文献1](https://blog.csdn.net/xzp_12345/article/details/79251174)
[参考文献2](https://www.cnblogs.com/xiaoxi/p/6089984.html)
### 框架
总体图：
![](./pic/容器框架（详细）.jpg)

简化图：
![](./pic/容器框架.png)
其中淡绿色的表示接口，红色的表示我们经常使用的类。

### Collection

#### List
List集合代表一个有序集合，集合中每个元素都有其对应的顺序索引。List集合允许使用重复元素，可以通过索引来访问指定位置的集合元素。
* ArrayList
    1. ArrayList是List接口的可变数组**非同步实现**，并允许包括null在内的所有元素。
    1. 底层使用**数组**实现，默认大小为10。
    1. 该集合是可变长度数组，数组扩容时，会将老数组中的元素重新拷贝一份到新的数组中，每次数组容量增长大约是**其容量的1.5倍**，这种操作的代价很高。
    1. 采用了Fail-Fast机制，面对并发的修改时，迭代器很快就会完全失败，而不是冒着在将来某个不确定时间发生任意不确定行为的风险。
    1. remove方法会让下标到数组末尾的元素向前移动一个单位，并把最后一位的值置空，方便GC。
<br>

* LinkedList
    1. LinkedList是List接口的双向链表**非同步实现**，并允许包括null在内的所有元素。
    1. 底层的数据结构是基于**双向链表**的，该数据结构我们称为节点。
    1. 双向链表节点对应的类Node的实例，Node中包含成员变量：prev，next，item。其中，prev是该节点的上一个节点，next是该节点的下一个节点，item是该节点所包含的值。
    1. 它的查找是分两半查找，先判断index是在链表的哪一半，然后再去对应区域查找，这样最多只要遍历链表的一半节点即可找到。
<br>

* Vector
    1. 与ArrayList相似，但是Vector是**同步**的。
    1. 底层使用synchronized实现同步。
    1. 扩容请求其**容量的2倍**空间。

#### Set
Set是一种不包括重复元素的Collection。它维持它自己的内部排序，所以随机访问没有任何意义。与List一样，它同样允许null的存在但是仅有一个。
* HashSet
    1. HashSet是非同步的。
    1. HashSet由哈希表(实际上是一个HashMap实例)支持，不保证set的迭代顺序，并允许使用null元素。
    1. 元素是存放在HashMap的Key中，而Value统一使用一个Object对象。
    1. HashSet中存储的元素的是无序的，但相应的元素的位置是固定的。
<br>

* LinkedHashSet
    1. 继承于HashSet、又基于LinkedHashMap来实现的，有序，非同步。
    1. 根据元素的hashCode值来决定元素的存储位置，但是它同时使用链表维护元素的次序，即当遍历该集合时候，LinkedHashSet将会以元素的添加顺序访问集合的元素。
<br>

* TreeSet
    1. 一个有序集合，其底层是基于TreeMap实现的，非线程安全。
    1. 支持两种排序方式，自然排序和定制排序，其中自然排序为默认的排序方式。
    1. 是通过hashcode和equals函数来比较元素的。它是通过compare或者comparaeTo函数来判断元素是否相等。

### Map
Map与List、Set接口不同，它是由一系列键值对组成的集合，提供了key到Value的映射。
* HashMap
    1. HashMap是基于哈希表的Map接口的**非同步实现**，允许使用null值和null键，但不保证映射的顺序。
    1. 1.8前是Entry数组+链表，1.8后为Node数组+链表+红黑树
    1. 底层使用数组实现，数组中每一项是个**单向链表**，即数组和链表的结合体；当链表长度**大于一定阈值**时，链表**转换为红黑树**，这样减少链表查询时间。
    1. HashMap在底层将key-value当成一个整体进行处理，这个整体就是一个Node对象。HashMap底层采用一个Node[]数组来保存所有的key-value对，当需要存储一个Node对象时，会根据key的hash算法来决定其在数组中的存储位置，在根据equals方法决定其在该数组位置上的链表中的存储位置；当需要取出一个Node时，也会根据key的hash算法找到其在数组中的存储位置，再根据equals方法从该位置上的链表中取出该Node。
    1. 每次扩容2倍，扩容使用resize()，再hash把旧数组中的值重新插入新的数组中。
    1. hash值计算：计算key的hash值，高16位与低16位异或，位运算(n-1)%hash
    1. 采用了Fail-Fast机制，通过一个modCount值记录修改次数，对HashMap内容的修改都将增加这个值。迭代器初始化过程中会将这个值赋给迭代器的expectedModCount，在迭代过程中，判断modCount跟expectedModCount是否相等，如果不相等就表示已经有其他线程修改了Map，马上抛出异常。
<br>

* HashTable
    1. Hashtable是基于哈希表的Map接口的同步实现，不允许使用null值和null键。
    1. 底层使用数组实现，数组中每一项是个单链表，即数组和链表的结合体。
    1. Hashtable在底层将key-value当成一个整体进行处理，这个整体就是一个Entry对象。Hashtable底层采用一个Entry[]数组来保存所有的key-value对，当需要存储一个Entry对象时，会根据key的hash算法来决定其在数组中的存储位置，在根据equals方法决定其在该数组位置上的链表中的存储位置；当需要取出一个Entry时，也会根据key的hash算法找到其在数组中的存储位置，再根据equals方法从该位置上的链表中取出该Entry。
    1. synchronized是针对整张Hash表的，即每次锁住整张表让线程独占。
<br>

* LinkedHashMap
    1. LinkedHashMap继承于HashMap，底层使用**哈希表和双向链表**来保存所有元素，并且它是**非同步**，允许使用null值和null键。
    1. 基本操作与父类HashMap相似，通过重写HashMap相关方法，重新定义了数组中保存的元素Entry，来实现自己的链接列表特性。该Entry除了保存当前对象的引用外，还保存了其上一个元素before和下一个元素after的引用，从而构成了双向链接列表。
<br>

* TreeMap
    1. 一个有序的key-value集合，非同步，基于红黑树（Red-Black tree）实现，每一个key-value节点作为红黑树的一个节点。
    1. 排序方式也是分为两种，自然排序、定制排序，取决于使用的构造方法。