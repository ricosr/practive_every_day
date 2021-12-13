package com.sunrui.thread;



class MonitorObject {
    public static int num = 4;

    void proNum() {
        num ++;
        System.out.println("add-----------------> " + num);
    }

    void cusNum() {
        num --;
        System.out.println("reduce-----------------> " + num);
    }
}

class MyWaitNotify3{

    private final MonitorObject myMonitorObject;
    public MyWaitNotify3(MonitorObject myMonitorObject) {
        this.myMonitorObject = myMonitorObject;
    }

    boolean wasSignalled = false;

    public void doWait(String threadName){
        synchronized(myMonitorObject){
            System.out.println(threadName + " 1--------------->" + wasSignalled);
            while(!wasSignalled){
                try{
                    System.out.println(threadName + " 11111111111111111111111111");
                    myMonitorObject.wait();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(threadName + " 22222222222222222222222222");
            }
            this.myMonitorObject.cusNum();
            //clear signal and continue running.
            wasSignalled = false;
        }
    }

    public void doNotify(String threadName){
        synchronized(myMonitorObject){
            System.out.println(threadName + " 2--------------->" + wasSignalled);
            this.myMonitorObject.proNum();
            wasSignalled = true;
            myMonitorObject.notify();
        }
    }
}

class TestRunPro implements Runnable{
    private final MyWaitNotify3 myWaitNotify3;
    public TestRunPro (MyWaitNotify3 myWaitNotify3) {
        this.myWaitNotify3 = myWaitNotify3;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.myWaitNotify3.doNotify(Thread.currentThread().getName());
        }
    }
}

class TestRunCus implements Runnable{
    private final MyWaitNotify3 myWaitNotify3;
    public TestRunCus (MyWaitNotify3 myWaitNotify3) {
        this.myWaitNotify3 = myWaitNotify3;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.myWaitNotify3.doWait(Thread.currentThread().getName());
        }
    }
}


class WaitNotify {
    public static void main(String args[]) {
        MonitorObject myMonitor = new MonitorObject();
        MyWaitNotify3 myWaitNotify3 = new MyWaitNotify3(myMonitor);
//        new Thread(new TestRunCus(myWaitNotify3)).start();
        new Thread(new TestRunCus(myWaitNotify3)).start();
        new Thread(new TestRunCus(myWaitNotify3)).start();
        new Thread(new TestRunCus(myWaitNotify3)).start();
        new Thread(new TestRunPro(myWaitNotify3)).start();
//        new Thread(new TestRunPro(myWaitNotify3)).start();
//        new Thread(new TestRunCus(myWaitNotify3)).start();
    }
}


/*

可以清楚看出每次做reduce的不是同一个线程，即打印出22222222222222222222222222的，即这个线程被notify唤醒

Thread-3 2--------------->false
add-----------------> 5
Thread-1 1--------------->true
reduce-----------------> 4
Thread-0 1--------------->false
Thread-0 11111111111111111111111111
Thread-2 1--------------->false
Thread-2 11111111111111111111111111
Thread-1 1--------------->false
Thread-1 11111111111111111111111111
Thread-3 2--------------->false
add-----------------> 5
Thread-0 22222222222222222222222222
reduce-----------------> 4
Thread-0 1--------------->false
Thread-0 11111111111111111111111111
Thread-3 2--------------->false
add-----------------> 5
Thread-2 22222222222222222222222222
reduce-----------------> 4
Thread-3 2--------------->false
add-----------------> 5
Thread-1 22222222222222222222222222
reduce-----------------> 4
Thread-2 1--------------->false
Thread-2 11111111111111111111111111
Thread-1 1--------------->false
Thread-1 11111111111111111111111111
Thread-3 2--------------->false
add-----------------> 5
Thread-0 22222222222222222222222222
reduce-----------------> 4

 */


