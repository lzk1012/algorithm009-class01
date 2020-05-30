# Week02作业
## 1. HashMap总结
HashMap线程不安全，效率高，允许null键和null值。底层实现是数组+链表。而JDK1.8中，HashMap采用数组+链表+红黑树实现（如果链表转红黑树的条件是：节点大于等于8个）。

通过对key的哈希值进行再散列，找到数组中的存放下标，如果不冲突，直接存放，冲突的情况下，形成一个链表，存放在下标处。因此在极端情况下，如果所有存放的元素都冲突，查找的时间复杂度为O(n)，不过基本山不会发生这种情况。另外，如果存储的元素个数超过【table数组长度*加载因子】，则会触发HashMap的扩容，扩容内部对各元素进行重哈希。

详细源码分析见：

[HashMap剖析](https://note.youdao.com/ynoteshare1/index.html?id=e6c0de101a79462c8c4e1245248a9c34&type=note)

[1.8和1.7中HashMap的差异](https://note.youdao.com/ynoteshare1/index.html?id=5735de310d6d6f7a23a15de8a4fb075e&type=note)

## 2. 算法习题
详见各java文件

## 3. 总结
绘制脑图，见[`week02_summary.png`](https://github.com/lzk1012/algorithm009-class01/blob/master/Week_02/week02_summary.png)
