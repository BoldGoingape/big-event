package com.wenjuju.top;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {
    @Test
    public void testThreadLocalSetAndGet(){
        ThreadLocal tl = new ThreadLocal();
        new Thread(()->{
            tl.set("笑啊");
            System.out.println(Thread.currentThread().getName()+" "+tl.get());
            System.out.println(Thread.currentThread().getName()+" "+tl.get());
            System.out.println(Thread.currentThread().getName()+" "+tl.get());
        },"蓝色").start();

        new Thread(()->{
            tl.set("哭啊");
            System.out.println(Thread.currentThread().getName()+" "+tl.get());
            System.out.println(Thread.currentThread().getName()+" "+tl.get());
            System.out.println(Thread.currentThread().getName()+" "+tl.get());
        },"红色").start();
    }
}
