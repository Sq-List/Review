## HashMap

### 一、存储结构
HashMap是数组+链表+红黑树（JDK1.8增加了红黑树部分）实现的
![](./pic/HashMap存储结构.jpg)

源码中具体实现：
```java
// Node<K,V> 类用来实现数组及链表的数据结构
static class Node<K,V> implements Map.Entry<K,V> {
    final int hash;     // 保存节点的 hash　值
    final K key;        // 保存节点的　key　值
    V value;            // 保存节点的　value 值
    Node<K,V> next;     // 指向链表结构下的当前节点的　next 节点，红黑树　TreeNode　节点中也有用到

    Node(int hash, K key, V value, Node<K,V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public final K getKey()        { return key; }
    public final V getValue()      { return value; }
    public final String toString() { return key + "=" + value; }

    public final int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    public final V setValue(V newValue) {}

    public final boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof Map.Entry) {
            Map.Entry<?,?> e = (Map.Entry<?,?>)o;
            if (Objects.equals(key, e.getKey()) &&
                Objects.equals(value, e.getValue()))
                return true;
        }
        return false;
    }
}
```
```java
static final class TreeNode<K,V> 
    extends LinkedHashMap.Entry<K,V> {
    TreeNode<K,V> parent;   // 存储当前节点的父节点
    TreeNode<K,V> left;     // 存储当前节点的左孩子
    TreeNode<K,V> right;    // 存储当前节点的右孩子
    TreeNode<K,V> prev;     // 存储当前节点的前一个节点
    boolean red;            // 存储当前节点的颜色（红、黑）
    TreeNode(int hash, K key, V val, Node<K,V> next) {
        super(hash, key, val, next);
    }

    // ......
}
```

#### hash计算：
![](./pic/HashMap-hash计算.png)

### 二、各常量、成员变量作用
#### 常量：
```java
// 创建HashMap时未指定初始容量情况下的默认容量 2的4次方
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

// HashMap的最大容量 2的30次方
static final int MAXIMUM_CAPACITY = 1 << 30;

// HashMap默认的装载因子，当HashMap中元素超过 容量*装载因子 时，进行resize()操作
static final float DEFAULT_LOAD_FACTOR = 0.75f;

// 用来确定何时将hash冲突的链表转换成红黑树
static final int TREEIFY_THRESHOLD = 8;

// 用来确定何时将hash冲突的红黑树装换成链表
static final int UNTREEIFY_THRESHOLD = 6;

/**
 * 当需要将解决 hash 冲突的链表转变为红黑树时，
 * 需要判断下此时数组容量，
 * 若是由于数组容量太小（小于MIN_TREEIFY_CAPACITY）导致的 hash 冲突太多，
 * 则不进行链表转变为红黑树操作，
 * 转为利用　resize() 函数对　hashMap 扩容
 */
static final int MIN_TREEIFY_CAPACITY = 64;
```
#### 变量：
```java
// 保存Node<K, V>节点的数组
transient Node<K,V>[] table;

// 由HashMap中Node<K, V>节点构成的Set
transient Set<Map.Entry<K,V>> entrySet;

// 记录HashMap当前存储的元素的数量
transient int size;

// 记录HashMap发生结构变化的次数（value覆盖不是结构变化）
transient int modCount;

// threshold的值应该等于table.length * loadFactor，size超过这个值就会进行resize()扩容
int threshold;

// 记录HashMap装载因子
final float loadFactor;
```

### 三、构造方法
```java
// 返回的值是最接近的大于 cap 的 2的幂
// 若cap为9，则返回值为16
static final int tableSizeFor(int cap) {
    int n = cap - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
}
```
```java
// 构造方法1，指定初始容量和装载因子
public HashMap(int initialCapacity, float loadFactor) {
    if (initialCapacity < 0)
        throw new IllegalArgumentException("Illegal initial capacity: " +
                                            initialCapacity);
    if (initialCapacity > MAXIMUM_CAPACITY)
        initialCapacity = MAXIMUM_CAPACITY;
    if (loadFactor <= 0 || Float.isNaN(loadFactor))
        throw new IllegalArgumentException("Illegal load factor: " +
                                            loadFactor);
    this.loadFactor = loadFactor;
    // 注意此种方法创建的 hashMap 初始容量的值存在　threshold 中
    this.threshold = tableSizeFor(initialCapacity);
}

// 构造方法2，指定初始容量
// 装载因子的值采用默认的　0.75
public HashMap(int initialCapacity) {
    this(initialCapacity, DEFAULT_LOAD_FACTOR);
}

// 构造方法3
public HashMap() {
    this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
}

// 构造方法4
public HashMap(Map<? extends K, ? extends V> m) {
    this.loadFactor = DEFAULT_LOAD_FACTOR;
    putMapEntries(m, false);
}
```

### 四、put及其相关方法
```java
static final int hash(Object key) {
    int h;
    /**
     * key 的 hash值的计算是通过hashCode()的高16位异或低16位实现的：
     * (h = k.hashCode()) ^ (h >>> 16)
     * 主要是从速度、功效、质量来考虑的,
     * 这么做可以在数组table的length比较小的时候，
     * 也能保证考虑到高低Bit都参与到Hash的计算中，同时不会有太大的开销
     */
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```
```java
// 向HashMap中插入 (key, value)
public V put(K key, V value) {
    // 注意待插入节点　hash 值的计算，调用了　hash(key) 函数
    // 实际调用 putVal（）进行节点的插入
    return putVal(hash(key), key, value, false, true);
}

public void putAll(Map<? extends K, ? extends V> m) {
    putMapEntries(m, true);
}

/**
 * 把Map<? extends K, ? extends V> m 中的元素插入到 HashMap 中,
 * 若 evict 为 false,代表是在创建 hashMap 时调用了这个函数，
 * 例如利用上述构造函数 3 创建 hashMap；
 * 若 evict 为true,代表是在创建 hashMap 后才调用这个函数，
 * 例如上述的 putAll 函数。
 */
final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict) {
    int s = m.size();
    if (s > 0) {
        // 如果是在创建 hashMap 时调用的这个函数则 table 一定为空
        if (table == null) { // pre-size
            // 根据待插入的map 的 size 计算要创建的 hashMap 的容量
            float ft = ((float)s / loadFactor) + 1.0F;
            int t = ((ft < (float)MAXIMUM_CAPACITY) ?
                        (int)ft : MAXIMUM_CAPACITY);
            // 把要创建的 hashMap 的容量存在 threshold 中
            if (t > threshold)
                threshold = tableSizeFor(t);
        }
        // 判断带插入的map的size，若size大于threshold，则先进行resize()
        else if (s > threshold)
            resize();
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            K key = e.getKey();
            V value = e.getValue();
            // 实际调用 putVal（）进行节点的插入
            putVal(hash(key), key, value, false, evict);
        }
    }
}

final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    /**
     * 根据 hash 值确定节点在数组中的插入位置，
     * 若此位置没有元素则进行插入，
     * 注意确定插入位置所用的计算方法为 (n - 1) & hash,
     * 由于 n 一定是2的幂次，这个操作相当于 hash % n
     */
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    else { // 说明带插入的位置存在元素
        Node<K,V> e; K k;
        // 比较原来元素与待插入元素的 hash 值和 key 值
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        // 若原来元素是红黑树节点，调用红黑树的插入方法:putTreeVal
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else { //证明原来的元素是链表的头结点，从此节点开始向后寻找合适插入位置
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    // 若链表上节点超过TREEIFY_THRESHOLD - 1，将链表变为红黑树
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        if (e != null) { // 对应key已经存在
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    if (++size > threshold)
        resize();
    afterNodeInsertion(evict);
    return null;
}
```
```java
static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
    // ...

    static int tieBreakOrder(Object a, Object b) {
        int d;
        // System.identityHashCode()实际是利用对象a, b的内存地址进行比较
        if (a == null || b == null ||
            (d = a.getClass().getName().
                compareTo(b.getClass().getName())) == 0)
            d = (System.identityHashCode(a) <= System.identityHashCode(b) ?
                    -1 : 1);
        return d;
    }

    /**
     * 读懂这个函数要注意理解 hash 冲突发生的几种情况
     * 1、两节点　key 值相同（hash值一定相同），导致冲突
     * 2、两节点　key 值不同，由于 hash 函数的局限性导致hash 值相同，冲突
     * 3、两节点　key 值不同，hash 值不同，但 hash 值对数组长度取模后相同，冲突
     */
    final TreeNode<K,V> putTreeVal(HashMap<K,V> map, Node<K,V>[] tab,
                                       int h, K k, V v) {
        Class<?> kc = null;
        boolean searched = false;
        TreeNode<K,V> root = (parent != null) ? root() : this;
        // 从根节点开始查找合适的插入位置（与二叉搜索树查找过程相同）
        for (TreeNode<K,V> p = root;;) {
            int dir, ph; K pk;
            if ((ph = p.hash) > h)
                // dir小于0，接下来查找当前节点左孩子
                dir = -1;
            else if (ph < h)
                // dir大于0，接下来查找当前节点右孩子
                dir = 1;
            else if ((pk = p.key) == k || (k != null && k.equals(pk)))
                // hash 值相同，key 相同，直接返回
                return p;
            /**
             * 代表有以下几个含义:
             * 1、当前节点与待插入节点 key 不同, hash 值相同
             * 2、k是不可比较的，即k并未实现comparable<K>接口
             * (若k实现了comparable<K>接口，
             * comparableClassFor(k)返回的是k的class,而不是null)
             * 或者compareComparables(kc, k, pk)返回值为 0
             * (pk为空或者按照 k.compareTo(pk) 返回值为0,
             * 返回值为0可能是由于k的compareTo 方法实现不当引起的，
             * compareTo 判定相等，而上个else if中equals判定不等)
             */
            else if ((kc == null &&
                        (kc = comparableClassFor(k)) == null) ||
                        (dir = compareComparables(kc, k, pk)) == 0) {
                if (!searched) {
                    // 在以当前节点为根的整个树上搜索是否存在待插入节点（只会搜索一次）
                    TreeNode<K,V> q, ch;
                    searched = true;
                    if (((ch = p.left) != null &&
                            (q = ch.find(h, k, kc)) != null) ||
                        ((ch = p.right) != null &&
                            (q = ch.find(h, k, kc)) != null))
                        // 若树中存在 key相同的结点，直接返回
                        return q;
                }
                // 既然k是不可比较的，那就指定一个比较方式
                dir = tieBreakOrder(k, pk);
            }

            TreeNode<K,V> xp = p;
            if ((p = (dir <= 0) ? p.left : p.right) == null) {
                // 找到了待插入的位置，xp 为待插入节点的父节点
                // TreeNode中既存在树状关系，也存在链式关系，并且是双端链表
                Node<K,V> xpn = xp.next;
                TreeNode<K,V> x = map.newTreeNode(h, k, v, xpn);
                if (dir <= 0)
                    xp.left = x;
                else
                    xp.right = x;
                xp.next = x;
                x.parent = x.prev = xp;
                if (xpn != null)
                    ((TreeNode<K,V>)xpn).prev = x;
                // 红黑树平衡操作
                moveRootToFront(tab, balanceInsertion(root, x));
                return null;
            }
        }
    }

    // ...
}
```

### 五、get及其相关方法
```java
public V get(Object key) {
    Node<K,V> e;
    // 实际上是根据输入节点的 hash 值和 key 值利用getNode 方法进行查找
    return (e = getNode(hash(key), key)) == null ? null : e.value;
}

final Node<K,V> getNode(int hash, Object key) {
    Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
    // n是2的倍数 (n - 1) & hash 相当于 hash % n
    if ((tab = table) != null && (n = tab.length) > 0 &&
        (first = tab[(n - 1) & hash]) != null) {
        if (first.hash == hash && // always check first node
            ((k = first.key) == key || (key != null && key.equals(k))))
            return first;
        if ((e = first.next) != null) {
            // 若定位到的节点是　TreeNode 节点，则在树中进行查找
            if (first instanceof TreeNode)
                return ((TreeNode<K,V>)first).getTreeNode(hash, key);
            do { // 否则在链表中进行查找
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            } while ((e = e.next) != null);
        }
    }
    return null;
}
```
```java
final TreeNode<K,V> getTreeNode(int h, Object k) {
    // 从根节点开始，调用 find 方法进行查找
    return ((parent != null) ? root() : this).find(h, k, null);
}

final TreeNode<K,V> find(int h, Object k, Class<?> kc) {
    TreeNode<K,V> p = this;
    do {
        int ph, dir; K pk;
        TreeNode<K,V> pl = p.left, pr = p.right, q;
        // 首先进行hash 值的比较，若不同令当前节点变为它的左孩子或者右孩子
        if ((ph = p.hash) > h)
            p = pl;
        else if (ph < h)
            p = pr;
        // hash 值相同，进行key值的比较 
        else if ((pk = p.key) == k || (k != null && k.equals(pk)))
            return p;
        else if (pl == null)
            p = pr;
        else if (pr == null)
            p = pl;
        // 执行到这儿，意味着hash 值相同，key 值不同
        // 若k是可比较的并且k.compareTo(pk) 返回结果不为0 可进入下面else if
        else if ((kc != null ||
                    (kc = comparableClassFor(k)) != null) &&
                    (dir = compareComparables(kc, k, pk)) != 0)
            p = (dir < 0) ? pl : pr;
        /**
         * 若 k 是不可比较的
         * 或者k.compareTo(pk) 返回结果为0
         * 则在整棵树中进行查找，先找右子树，右子树没有再找左子树
         */
        else if ((q = pr.find(h, k, kc)) != null)
            return q;
        else
            p = pl;
    } while (p != null);
    return null;
}
```

### 七、扩容方法 resize()
resize() 方法中比较重要的是链表和红黑树的rehash操作，rehash原理：
扩容时，一般是把长度扩为原来的2倍，所以，元素的新位置要不是在原位置，要不是在原位置再移动 原来容量大小 的位置
![](./pic/HashMap-rehash.png)
n 为table的长度，图a表示扩容前的key1和key2两种key确定索引位置的示例；图b表示扩容后key1和key2两种确定索引位置的示例。其中，hash1是key1对应的hash与高位运算结果。
元素在重新计算hash之后，因为n变为2倍，那么n-1的mask范围在高位多1bit（红色），因此新的index就会发生这样的变化：
![](./pic/HashMap-rehash-result.png)
因此，在扩充HashMap的时，只需要看看原来的hash值新增的那个bit是1还是0，若为0则索引没变，否则，索引变成“原索引+原来容量大小”。
16扩充成32，示例：
![](./pic/HashMap-16-32-example.png)
**这个算法很巧妙，既省去了重新计算hash值的时间，而且同时，由于新增的1bit是0还是1可以认为是随机的，因此resize的过程，均匀的把之前的冲突的节点分散到新的槽中了**

源码：
```java
final Node<K,V>[] resize() {
    Node<K,V>[] oldTab = table;
    // 原表大小
    int oldCap = (oldTab == null) ? 0 : oldTab.length;
    // 原表临界值
    int oldThr = threshold;
    // 初始化新的容量和阈值
    int newCap, newThr = 0;
    /**
     * 1. resize() 在size > threshold时被调用
     */
    if (oldCap > 0) {
        // 如果当前容量已经到达上限
        if (oldCap >= MAXIMUM_CAPACITY) {
            // 设置阈值为 2的31次方-1
            threshold = Integer.MAX_VALUE;
            // 返回当前老的hash桶，不在扩容
            return oldTab;
        }
        // 否则新的容量为旧的容量的2倍
        else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                    oldCap >= DEFAULT_INITIAL_CAPACITY) //如果旧的容量大于等于默认初始容量16
            // 那么新的阈值也等于旧的阈值的2倍
            newThr = oldThr << 1; // double threshold
    }
    /**
     * 2. resize() 在table为空被调用
     * oldCap 小于等于 0 且 oldThr 大于0，代表用户创建了一个 HashMap，
     * 但是使用的构造函数为
     * HashMap(int initialCapacity, float loadFactor)
     * 或 HashMap(int initialCapacity)
     * 或 HashMap(Map<? extends K, ? extends V> m)，
     * 导致 oldTab 为 null，oldCap 为0，oldThr 为用户指定的 HashMap的初始容量。
     */
    else if (oldThr > 0) // initial capacity was placed in threshold
        // 那么新表的容量就等于旧的阈值
        newCap = oldThr;
    /**
     * 3、resize（）函数在table为空被调用。
     * oldCap 小于等于 0 且 oldThr 等于0，
     * 用户调用 HashMap()构造函数创建的 HashMap，
     * 所有值均采用默认值，oldTab（Table）表为空，
     * oldCap为0，oldThr等于0
     */
    else {               // zero initial threshold signifies using defaults
        // 此时新表的容量为默认的容量 16
        newCap = DEFAULT_INITIAL_CAPACITY;
        // 新的阈值为默认容量16 * 默认加载因子0.75f = 12
        newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
    }
    // 如果新的阈值是0，对应的是  当前表是空的，但是有阈值的情况，即第二种情况
    if (newThr == 0) {
        // 根据新表容量 和 加载因子 求出新的阈值
        float ft = (float)newCap * loadFactor;
        // 越界修复
        newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                    (int)ft : Integer.MAX_VALUE);
    }
    // 更新阈值
    threshold = newThr;
    @SuppressWarnings({"rawtypes","unchecked"})
    // 根据新的容量，构建新的hash桶
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
    table = newTab;
    // 如果旧的hash桶中有元素
    // 将就hash桶中的所有结点转移到新的hash桶中
    if (oldTab != null) {
        // 遍历旧的hash桶
        for (int j = 0; j < oldCap; ++j) {
            Node<K,V> e;
            // 如果当前桶中有元素，则将链表赋值给e
            if ((e = oldTab[j]) != null) {
                // 将原hash桶置空，以便gc
                oldTab[j] = null;
                // 如果当前链表中就一个元素
                if (e.next == null)
                    // 直接将这个元素放置在新的hash桶里
                    newTab[e.hash & (newCap - 1)] = e;
                // 如果原来元素是红黑树节点，调用红黑树的split方法
                else if (e instanceof TreeNode)
                    ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                // 原来的元素是链表的头结点
                else { // preserve order
                    /**
                     * 扩容是容量翻倍的，所以原链表上的每个节点
                     * 现在可能存放在原来的下标，即low位
                     * 或者是扩容后的下标，即high位。high位=low位+原hash桶容量
                     */
                    // low位链表的头结点，尾结点
                    Node<K,V> loHead = null, loTail = null;
                    // high位链表的头结点，尾结点
                    Node<K,V> hiHead = null, hiTail = null;
                    // 临时结点，存放e的下一个结点
                    Node<K,V> next;
                    do {
                        next = e.next;
                        /**
                         * 这里又是一个利用位运算 代替常规运算的高效点： 
                         * 利用哈希值 与 旧的容量，
                         * 可以得到哈希值去模后，是大于等于oldCap还是小于oldCap，
                         * 等于0代表小于oldCap，应该存放在低位，否则存放在高位
                         */
                        if ((e.hash & oldCap) == 0) {
                            // 保存第一个结点
                            if (loTail == null)
                                loHead = e;
                            else
                                loTail.next = e;
                            loTail = e;
                        }
                        else {
                            if (hiTail == null)
                                hiHead = e;
                            else
                                hiTail.next = e;
                            hiTail = e;
                        }
                    } while ((e = next) != null);
                    // 将低位链表存放在原index处
                    if (loTail != null) {
                        loTail.next = null;
                        newTab[j] = loHead;
                    }
                    // 将高位链表存放在新index处
                    if (hiTail != null) {
                        hiTail.next = null;
                        newTab[j + oldCap] = hiHead;
                    }
                }
            }
        }
    }
    return newTab;
}
```