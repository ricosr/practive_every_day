package com.sunrui.kafka;

import com.sunrui.kafka.CustomerTest;

public class runCustomer {

    public static void main(String[] args) {
        CustomerTest cusObj = new CustomerTest();
        cusObj.init();
        String topic = "test1122";
        cusObj.consumeMsg(topic);

    }
}
