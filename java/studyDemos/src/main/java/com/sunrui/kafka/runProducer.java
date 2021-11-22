package com.sunrui.kafka;

import com.sunrui.kafka.ProducerTest;

public class runProducer {

    public static void main(String[] args) throws InterruptedException {
        ProducerTest proObj = new ProducerTest();
        proObj.init();
        String topic = "test1122";
        String msg = "12345";
        String key = "4";

//        long start1 = System.currentTimeMillis();
//        for (int i=0; i < 9000 ; i++) {
//            proObj.sendMsg(msg, null, topic);
//        }
//        System.out.printf("sync timestamp:%d%n", System.currentTimeMillis() - start1);

        long start2 = System.currentTimeMillis();
        for (int i=0; i < 9000 ; i++) {
            proObj.sendAsyncMsg(msg, null, topic);
        }
        System.out.printf("sync timestamp:%d%n", System.currentTimeMillis() - start2);
//
        Thread.sleep(5000);

    }
}


