# Week08作业
## 1、冒泡排序
```
// 依次两两比较，后者比前者大，交换
public void bubbleSort(int[] arr){
    // 是否有元素的交换，如果没有元素交换，直接返回。此时最优的时间复杂度为O(n)
    boolean hasSwap = false;
    for(int i= 0,len = arr.length;i<len;i++){
        for(int j = 0;j<len - i - 1;j++){
            if(arr[j+1] < arr[j]){
                swap(arr[j+1],arr[j]);
                hasSwap = true;
            }
            if(!hasSwap){
                return;
            }
        }
    }
}
```

## 2、选择排序
```
// 每次遍历一个最小的元素，交换第一个位置
public void selectSort(int[] arr) {
    for (int i = 0, len = arr.length; i < len; i++) {
        int minIndex = i;
        for (int j = i + 1; j < len; j++) {
            if (arr[minIndex] > arr[j]) {
                minIndex = j;
            }
        }
        swap(arr[i], arr[minIndex]);
    }
}
```

## 3、插入排序
```
// 将前面的元素视为有序，后面每个遍历到的元素，在前面有序数组中寻找插入的位置
public void insertionSort(int[] arr){
    for(int i = 0,len = arr.length;i<len - 1;i++){
        int crtInsert = arr[i + 1];
        int preIndex = i;
        while(preIndex>=0 && crtInsert < arr[preIndex]){
            arr[preIndex + 1] = arr[preIndex];
            preIndex--;
        }
        arr[preIndex + 1] = crtInsert;
    }
}
```

## 4、希尔排序
```
// 插入排序的改进，把记录按下表的一定增量分组，对每组使用直接插入排序算法排序
public void shellSort(int[] arr){
    int len = arr.length;
    int gap = len / 2;
    while (gap > 0) {
        for (int i = gap; i < len; i++) {
            int current = arr[i];
            int preIndex = i - gap;
            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + gap] = arr[preIndex];
                preIndex -= gap;
            }
            arr[preIndex + gap] = current;
        }
        gap /= 2;
    }
}
```

## 5、归并排序
```
public int[] mergeSort(int[] arr) {
    if(arr.length < 2){
        return arr;
    }
    int mid = arr.length / 2;
    int[] left = Arrays.copyOfRange(arr, 0, mid);
    int[] right = Arrays.copyOfRange(arr, mid, arr.length);
    return merge(mergeSort(left),mergeSort(right));
}

public int[] merge(int[] left, int[] right) {
    int[] result = new int[left.length + right.length];
    for (int index = 0, i = 0, j = 0; index < result.length; index++) {
        if (i >= left.length)
            result[index] = right[j++];
        else if (j >= right.length)
            result[index] = left[i++];
        else if (left[i] > right[j])
            result[index] = right[j++];
        else
            result[index] = left[i++];
    }
    return result;
}
```

## 6、快速排序
```
public void quickSort(int[] arr){
    quickSort(arr,0,arr.length - 1);
}

private void quickSort(int[] arr, int low, int high) {
    if (low < high) {
        int index = getIndex(arr, low, high);
        quickSort(arr, low, index - 1);
        quickSort(arr, index + 1, high);
    }

}

private int getIndex(int[] arr, int low, int high) {
    int tmp = arr[low];
    while (low < high) {
        while (low < high && arr[high] >= tmp) {
            high--;
        }
        arr[low] = arr[high];
        while (low < high && arr[low] <= tmp) {
            low++;
        }
        arr[high] = arr[low];

    }
    arr[low] = tmp;
    return low; 
}
```

## 7、堆排序
```
public static int[] HeapSort(int[] array) {
    int len = array.length;
    if (len < 1) return array;
    //1.构建一个大顶堆
    buildMaxHeap(array);
    //2.调整堆结构+交换堆顶元素与末尾元素
    for (int j = array.length - 1; j > 0; j--) {
        swap(array, 0, j);//将【堆顶】元素与【末尾】元素进行交换
        //重新对堆进行调整（末尾元素已经最大，不再参与计算）
        adjustHeap(array, 0, j);
    }
    return array;
}

public static void buildMaxHeap(int[] array) {
    int len = array.length;
    //从最后一个叶子节点的父节点开始调整节点顺序，之后逐个向上调整，以构造最大堆
    for (int i = (len/2 - 1); i >= 0; i--) {
        adjustHeap(array, i,len);
    }
}

public static void adjustHeap(int[] array, int i,int len) {
    int maxIndex = i;
    int left = 2*i + 1; // 左子树下标
    int right = 2*i + 2; // 右子树下标
    //如果有左子树，且左子树大于父节点，则将最大指针指向左子树
    if (left < len && array[left] > array[maxIndex])
        maxIndex = left;
    //如果有右子树，且右子树大于父节点，则将最大指针指向右子树
    if (right < len && array[right] > array[maxIndex])
        maxIndex = right;
    //如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换的位置。
    if (maxIndex != i) {
        swap(array, maxIndex, i);
        adjustHeap(array, maxIndex,len);
    }
}

public static void swap(int []arr,int a ,int b){
    int temp=arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
}
```
