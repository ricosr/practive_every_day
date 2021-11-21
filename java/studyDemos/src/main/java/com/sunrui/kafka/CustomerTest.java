package com.sunrui.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class CustomerTest {
    private final Properties props = new Properties();

    public boolean init() {
        props.put("bootstrap.servers", "192.168.19.128:9092,192.168.19.129:9092,192.168.19.130:9092");
        props.put("group.id", "test1121");
        props.put("enable.auto.commit", "true");   // 自动提交偏移量
        props.put("auto.commit.interval.ms", "1000");   // 控制自动提交的频率，ms
        props.put("session.timeout.ms", "30000");    // group coordinator检测consumer发生崩溃所需的时间。一个consumer group里面的某个consumer挂掉了，最长需要 session.timeout.ms 毫秒检测出来
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return true;
    }

    public boolean consumeMsg(String topic) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
        }
    }
}
