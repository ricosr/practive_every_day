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

    public void doWait(){
        synchronized(myMonitorObject){
            while(!wasSignalled){
                try{
                    this.myMonitorObject.cusNum();
                    myMonitorObject.wait();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            //clear signal and continue running.
            wasSignalled = false;
        }
    }

    public void doNotify(){
        synchronized(myMonitorObject){
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
        this.myWaitNotify3.doNotify();
    }
}

class TestRunCus implements Runnable{
    private final MyWaitNotify3 myWaitNotify3;
    public TestRunCus (MyWaitNotify3 myWaitNotify3) {
        this.myWaitNotify3 = myWaitNotify3;
    }

    @Override
    public void run() {
        this.myWaitNotify3.doWait();
    }
}


class WaitNotify {
    public static void main(String args[]) {
        MonitorObject myMonitor = new MonitorObject();
        MyWaitNotify3 myWaitNotify3 = new MyWaitNotify3(myMonitor);
        new Thread(new TestRunCus(myWaitNotify3)).start();
        new Thread(new TestRunCus(myWaitNotify3)).start();
        new Thread(new TestRunPro(myWaitNotify3)).start();
        new Thread(new TestRunPro(myWaitNotify3)).start();
    }
}


