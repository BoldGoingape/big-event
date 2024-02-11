package com.wenjuju.top.utils;

public class ThreadLocalUtil {
//    提供threadLocal 对象
    private static final ThreadLocal THREAD_LOCAL=new ThreadLocal();
//    根据键获取数据
    public static <T> T get(){
      return (T) THREAD_LOCAL.get();
    };
//    设置键数据
    public static void set(Object value){
      THREAD_LOCAL.set(value);
};
//    清空threadLocal 防止内存泄露
    public static void remove(){
        THREAD_LOCAL.remove();
    }
}
