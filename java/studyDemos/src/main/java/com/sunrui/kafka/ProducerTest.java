package com.sunrui.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerTest {

    private final Properties props = new Properties();
    private final int modNum = 4;

    public boolean init() {
        props.put("bootstrap.servers", "192.168.19.128:9092,192.168.19.129:9092,192.168.19.130:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);

        // Producer 默认会把两次发送时间间隔内收集到的所有 Requests 进行一次聚合然后再发送，以此提高吞吐量，
        // 而 linger.ms 就是为每次发送到 broker 的请求增加一些 delay，以此来聚合更多的 Message 请求。
        props.put("linger.ms", 5);

        // 生产者发送多个消息到 broker 上的同一个分区时，为了减少网络请求带来的性能开销，通过批量的方式来提交消息，
        // 可以通过这个参数来控制批量提交的字节数大小，默认大小是 16384byte,也就是 16kb，
        // 意味着当一批消息大小达到指定的 batch.size 的时候会统一发送
        props.put("buffer.memory", 102400);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // max.request.size: 设置请求的数据的最大字节数，为了防止发生较大的数据包影响到吞吐量，默认值为 1MB。
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
