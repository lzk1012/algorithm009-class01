package com.xx.local;

/**
 * 641. 设计循环双端队列
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/5/24 19:16
 */
public class MyCircularDeque_641 {
    public static void main(String[] args) {
        MyCircularDeque deque = new MyCircularDeque(3);
        deque.insertLast(1);
        deque.insertLast(2);
        deque.insertFront(3);
        deque.insertFront(4);
        deque.getRear();
        deque.isFull();
        deque.deleteLast();
        deque.insertFront(4);
        deque.getFront();
    }
}
class MyCircularDeque {
    // 存储元素的数组
    int[] queue;
    // 容量
    final int CAPACITY;
    // 已有元素个数
    int size = 0;
    // 队首队尾指针
    int frontIndex = 0;
    int lastIndex = 0;
    // 队列为空时，默认返回值
    final int DEFAULT_VALUE_FOR_EMPTY = -1;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        CAPACITY = k;
        queue = new int[k];
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(isFull()){
            return false;
        }
        queue[frontIndex] = value;
        // 队首指针，指向第一个元素的前一个元素
        frontIndex = (frontIndex - 1 + CAPACITY) % CAPACITY;
        size++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(isFull()){
            return false;
        }
        lastIndex = (lastIndex + 1) % CAPACITY;
        // 队尾指针，指向最后一个元素
        queue[lastIndex] = value;
        size++;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(isEmpty()){
            return false;
        }
        size--;
        // 先变更指针，再删除值
        frontIndex = (frontIndex + 1) % CAPACITY;
        queue[frontIndex] = 0;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(isEmpty()){
            return false;
        }
        size--;
        // 先删除值，再变更指针
        queue[lastIndex] = 0;
        lastIndex = (lastIndex - 1 + CAPACITY) % CAPACITY;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if(isEmpty()){
            return DEFAULT_VALUE_FOR_EMPTY;
        }
        return queue[(frontIndex + 1) % CAPACITY];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if(isEmpty()){
            return DEFAULT_VALUE_FOR_EMPTY;
        }
        return queue[lastIndex];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size == CAPACITY;
    }
}