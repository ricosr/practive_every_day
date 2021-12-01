package com.sunrui.redis;

public class runRedisClient {
    public static void main(String[] args) throws InterruptedException {
        RedisClient redisCli= new RedisClient();
        redisCli.testKey();
    }
}
