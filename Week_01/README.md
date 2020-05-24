# Week01作业
## 1. 用 add first 或 add last 这套新的 API 改写 Deque 的代码
改写所用的类为Deque实现之LinkedList。内容详见[`DequeTest.java`](https://github.com/lzk1012/algorithm009-class01/blob/master/Week_01/DequeTest.java)
## 2. 分析 Queue 和 PriorityQueue 的源码
> JDK版本：1.8


PriorityQueue为优先队列，作用是能保证每次取出的元素都是队列中权值最小的
1. 内部全局变量

```
// 默认初始容量
private static final int DEFAULT_INITIAL_CAPACITY = 11;
// 存储数据的数组
private transient Object[] queue;
// 队列的大小
private int size = 0;
// 使用的比较器
private final Comparator<? super E> comparator;
```
2. 构造函数
    
    代码略，大致意思：如果不指定初始容量，则使用默认容量DEFAULT_INITIAL_CAPACITY构造queue数组；而默认比较器comparator为null
3. 添加元素

```
public boolean add(E e) {
    return offer(e);
}
public boolean offer(E e) {
    if (e == null)
        throw new NullPointerException();// 不能往队列中添加null元素
    modCount++;
    int i = size;
    if (i >= queue.length)// 如果队列已满，则扩容
        grow(i + 1);
    size = i + 1;
    if (i == 0)
        queue[0] = e;//队列里没数据，把添加的元素放在第一位
    else
        siftUp(i, e);// 队列中已有数据，则调整数组中各元素位置，以保证优先队列的特性
    return true;
}
```
4. 扩容
```
private void grow(int minCapacity) {
    int oldCapacity = queue.length;
    // Double size if small; else grow by 50%
    int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                                     (oldCapacity + 2) :
                                     (oldCapacity >> 1));
    // overflow-conscious code
    if (newCapacity - MAX_ARRAY_SIZE > 0)
        newCapacity = hugeCapacity(minCapacity);
    queue = Arrays.copyOf(queue, newCapacity);
}

private static int hugeCapacity(int minCapacity) {
    if (minCapacity < 0) // overflow
        throw new OutOfMemoryError();
    return (minCapacity > MAX_ARRAY_SIZE) ?
        Integer.MAX_VALUE :
        MAX_ARRAY_SIZE;
}
```
设原始容量为x
- 如果原始容量x小于64，则新容量为2x+2;
- 如果x超过64，则新容量为1.5x;
- 若扩容后容量超过MAX_ARRAY_SIZE（Integer.MAX_VALUE - 8），则根据入参minCapacity（原始容量+1）判断，取Integer.MAX_VALUE或者MAX_ARRAY_SIZE

5. 查询
```
// peek取出队首元素或者null
public E peek() {
    return (size == 0) ? null : (E) queue[0];
}

// index获取元素位置，for遍历获取，不存在返回-1
private int indexOf(Object o) {
    if (o != null) {
        for (int i = 0; i < size; i++)
            if (o.equals(queue[i]))
                return i;
    }
    return -1;
}

// contains通过调用index判断元素是否存在
public boolean contains(Object o) {
    return indexOf(o) != -1;
}
```
6. 删除元素
```
// poll取出并移除队首元素，之后调整数组中各元素位置，以保证优先队列的特性
public E poll() {
    if (size == 0)
        return null;
    int s = --size;
    modCount++;
    E result = (E) queue[0];
    E x = (E) queue[s];
    queue[s] = null;
    if (s != 0)
        siftDown(0, x); // 用队首下标、队尾元素值作为参数，整数组中各元素位置
    return result;
}

// 移除元素，调用removeAt实现
public boolean remove(Object o) {
    int i = indexOf(o);
    if (i == -1)
        return false;
    else {
        removeAt(i);
        return true;
    }
}

// 如果删除的是队尾元素，直接设置为null，否则还要调整queue数组中各元素的位置
private E removeAt(int i) {
    // assert i >= 0 && i < size;
    modCount++;
    int s = --size;
    if (s == i) // removed last element
        queue[i] = null;
    else {
        E moved = (E) queue[s];
        queue[s] = null;
        // 删除都是删除数组中最后一个元素，然后调整i到叶子节点的元素位置
        siftDown(i, moved);
        if (queue[i] == moved) {
            siftUp(i, moved);
            if (queue[i] != moved)
                return moved;
        }
    }
    return null;
}

// clear通过for循环逐个设置为null
public void clear() {
    modCount++;
    for (int i = 0; i < size; i++)
        queue[i] = null;
    size = 0;
}
```

7. 调整queue中各元素位置

  PriorityQueue中使用数组存储元素，但是代码中有一个父节点的逻辑：下标x的父节点为⌊(x-1)/2⌋，即形成一个如下的二叉树，图中各数值为下标
![层级关系图](https://github.com/lzk1012/algorithm009-class01/blob/master/Week_01/PriorityQueue.png)

注：此二叉树为平衡树，但不是排序树，只能保证根节点到任意一个叶子节点的路径上，元素值升序，但不能保证任意节点的左节点值小于右节点（因此在下面siftDownUsingComparator源码中，添加了判断左右节点谁更大的逻辑）
```
// 源码注解为：将元素x插入到位置k，通过向上提升x直到它大于或等于其父节点，以此来保持堆不变。
// k意为要插入的下标，x为要插入的元素值
private void siftUp(int k, E x) {
    if (comparator != null)
        siftUpUsingComparator(k, x);
    else
        siftUpComparable(k, x);
}

// 此方法的大致意思为：设置下标k的值为x，然后向上到根节点进行位置调整：
//   通过 (k-1)>>>1，获得父节点的索引，然后逐个父子节点进行比较，保证在这一条根节点到k之间的链路上，数值升序
private void siftUpUsingComparator(int k, E x) {
    while (k > 0) {
        int parent = (k - 1) >>> 1;
        Object e = queue[parent];
        if (comparator.compare(x, (E) e) >= 0)
            break;
        queue[k] = e;
        k = parent;
    }
    queue[k] = x;
}
```
  siftUpUsingComparator和siftUpComparable逻辑一致，只不过一个是使用内部的比较器，一个是使用元素自身的compareTo方法。


```
// 源码注解为：将元素x插入到位置k，通过反复将x降级到树下，直到它小于或等于其子元素或为叶子，从而保持堆不变。
private void siftDown(int k, E x) {
    if (comparator != null)
        siftDownUsingComparator(k, x);
    else
        siftDownComparable(k, x);
}

// 此函数可以这么理解，把x的值覆盖到下标k上，然后向下到叶子节点进行位置调整：
//   从下标k开始，获取k的较小子节点，以此方式直至叶子节点，逐层比对，形成一条递增的链路
@SuppressWarnings("unchecked")
private void siftDownUsingComparator(int k, E x) {
    int half = size >>> 1;
    while (k < half) {
        int child = (k << 1) + 1;// 获取左子节点下标
        Object c = queue[child];
        int right = child + 1;
        if (right < size &&
            comparator.compare((E) c, (E) queue[right]) > 0)
            c = queue[child = right];
        if (comparator.compare(x, (E) c) <= 0)
            break;
        queue[k] = c;
        k = child;
    }
    queue[k] = x;
}
```

## 3. 总结
绘制脑图，见[`week01_summary.png`](https://github.com/lzk1012/algorithm009-class01/blob/master/Week_01/week01_summary.png)
