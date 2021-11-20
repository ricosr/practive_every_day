package com.sunrui.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerTest {

    private final Properties props = new Properties();
    private final int modNum = 3;

    public boolean init() {
        props.put("bootstrap.servers", "192.168.19.128:9092,192.168.19.129:9092,192.168.19.130:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return true;
    }

    public boolean sendMsg(String msg, String topic) {
        try{
            Producer<String, String> producer = new KafkaProducer<>(props);
            producer.send(new ProducerRecord<String, String>(topic, Integer.toString(msg.length() % modNum), msg));
            producer.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
