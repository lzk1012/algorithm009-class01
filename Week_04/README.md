# Week04作业
## 二分查找模板
1. 模板如下

```
int binarySearch(int[] nums, int target) {
    int left = 0, right = ...;

    while(...) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            ...
        } else if (nums[mid] < target) {
            left = ...
        } else if (nums[mid] > target) {
            right = ...
        }
    }
    return ...;
}
```
分析二分查找的一个技巧是：如果可以，尽量不要出现 else，而是把所有情况用 else if 写清楚，这样可以清楚地展现所有细节。

其中 ... 标记的部分，就是可能出现细节问题的地方具体问题具体分析

另外，计算 mid 时需要防止溢出，代码中 left + (right - left) / 2 就和 (left + right) / 2 的结果相同，但是有效防止了 left 和 right 太大直接相加导致溢出。

而Java代码中，可以这么写：(right+left)>>>1 无符号右移，即使是在整型溢出以后，仍然能够得到正确的结果。

2. while 循环的条件 <=和< 的区别

这二者可能出现在不同功能的二分查找中，区别是：前者相当于两端都闭区间 [left, right]，后者相当于左闭右开区间 [left, right)，因为索引大小为 nums.length 是越界的。也就是说这个区间其实就是每次进行搜索的区间。

while(left <= right) 的终止条件是 left == right + 1，写成区间的形式就是 [right + 1, right]，或者带个具体的下标数字进去 [3, 2]，可见这时候区间为空，因为没有数字既大于等于 3 又小于等于 2 的吧。所以这时候 while 循环终止是正确的，直接返回 -1 即可。

而while(left < right) 的终止条件是 left == right，写成区间的形式就是 [left, right]，或者带个具体的数字进去 [2, 2]，这时候区间非空，还有一个数 2，但此时 while 循环终止了。也就是说这区间 [2, 2] 被漏掉了，索引 2 没有被搜索，如果这时候直接返回 -1 就是错误的。

3. 区间缩进的条件

有时候缩进情况是left = mid + 1，right = mid - 1？也有的代码是 right = mid 或者 left = mid，怎么判断？

刚才明确了「搜索区间」这个概念，如果当前mid不是要查找的值，下次搜索区间自然是 [left, mid-1] 或者 [mid+1, right]。因为 mid 已经搜索过，应该从搜索区间中去除。