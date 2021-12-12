package com.sunrui.thread;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;


/*
    抄自：https://blog.csdn.net/diandingyin9417/article/details/102203472
    这个例子演示的是：notify线程发起通知时，wait线程尚未启动，所以等调用wait方法的线程启动时，没有机会再接收到消息，因此一直在等待
 */

public class FailedNotify {

    //持有的锁
    private static final Object lock = new Object();

    //日期格式器
    private static final DateFormat format = new SimpleDateFormat("HH:mm:ss");

    //等待线程执行的方法
    public void waitMethod() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": enter waitMethod at "
                + format.format(new Date()));
        synchronized (lock){
                //调用wait方法执行等待
                System.out.println(Thread.currentThread().getName() + ": start invoke wait method at "
                        + format.format(new Date()));
                lock.wait();
                System.out.println(Thread.currentThread().getName() + ": end invoke wait method at "
                        + format.format(new Date()));
        }
        System.out.println(Thread.currentThread().getName() + ": exit waitMethod at "
                + format.format(new Date()));
    }

    //通知线程执行的方法
    public void notifyMethod(){
        System.out.println(Thread.currentThread().getName() + ": exit notifyMethod1 at "
                + format.format(new Date()));
        synchronized (lock){
            //调用通知的方法
            System.out.println(Thread.currentThread().getName() + ": start invoke notify method at "
                    + format.format(new Date()));
            lock.notifyAll();
            System.out.println(Thread.currentThread().getName() + ": end invoke notify method at "
                    + format.format(new Date()));
        }
        System.out.println(Thread.currentThread().getName() + ": exit notifyMethod2 at "
                + format.format(new Date()));
    }

    static class WaitThread implements Runnable{
        private final FailedNotify missedNotifyDemo;

        public WaitThread(FailedNotify missedNotifyDemo) {
            this.missedNotifyDemo = missedNotifyDemo;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                missedNotifyDemo.waitMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class NotifyThread implements Runnable{

        private final FailedNotify missedNotifyDemo;

        public NotifyThread(FailedNotify missedNotifyDemo) {
            this.missedNotifyDemo = missedNotifyDemo;
        }

        @Override
        public void run() {
            try {
                //休眠的时间必须要小于等待线程的休眠时间
                TimeUnit.MILLISECONDS.sleep(500);
                missedNotifyDemo.notifyMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        FailedNotify missedNotifyDemo = new FailedNotify();
        new Thread(new WaitThread(missedNotifyDemo),"WaitThread").start();
        new Thread(new WaitThread(missedNotifyDemo),"WaitThread").start();
        new Thread(new NotifyThread(missedNotifyDemo),"NotifyThread").start();
    }
}