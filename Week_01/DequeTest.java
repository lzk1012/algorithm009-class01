package com.xx.local;

import java.util.*;

/**
 * TODO 类描述信息
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/5/16 12:12
 */
public class DequeTest {
    public static void main(String[] args) {
        new DequeTest().doDequeTest();
    }

    public void doDequeTest() {
        Deque<String> deque = new LinkedList<>();
        // add/remove/get → exception
        // offer/poll/peek → null
        deque.add("a");
        deque.addFirst("b");
        deque.addLast("c");
        deque.offer("o");
        deque.offerFirst("p");
        deque.offerLast("q");
        System.out.println(deque);

        // get/peek不会删除元素
        System.out.println("getFirst：" + deque.getFirst());
        System.out.println("getLast：" + deque.getLast());
        System.out.println("peek：" + deque.peek());
        System.out.println("peekFirst：" + deque.peekFirst());
        System.out.println("peekLast：" + deque.peekLast());
        System.out.println(deque);

        System.out.println("remove：" + deque.remove());
        System.out.println("removeFirst：" + deque.removeFirst());
        System.out.println("removeLast：" + deque.removeLast());
        System.out.println("poll：" + deque.poll());
        System.out.println("pollFirst：" + deque.pollFirst());
        System.out.println("pollLast：" + deque.pollLast());
        // deque.remove();// NoSuchElementException

        System.out.println(deque.peekFirst());// null
//        System.out.println(deque.getFirst());// NoSuchElementException

        // element即getFirst，pop即removeFirst，push即addFirst
        System.out.println(deque);
    }
}