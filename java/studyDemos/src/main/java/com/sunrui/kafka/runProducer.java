package com.sunrui.kafka;

import com.sunrui.kafka.ProducerTest;

public class runProducer {

    public static void main(String[] args) {
        ProducerTest proObj = new ProducerTest();
        proObj.init();
        String topic = "test1121";
        String msg = "12345";
        proObj.sendMsg(msg, topic);
    }
}
