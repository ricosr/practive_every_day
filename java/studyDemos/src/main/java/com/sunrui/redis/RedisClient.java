package com.sunrui.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import org.junit.Test;


import java.util.HashSet;
import java.util.Set;

public class RedisClient {

    private static JedisCluster jedis;
    static {
        Set<HostAndPort> hostAndPortSet = new HashSet<HostAndPort>();
        hostAndPortSet.add(new HostAndPort("192.168.19.128", 7001));
        hostAndPortSet.add(new HostAndPort("192.168.19.128", 7002));
        hostAndPortSet.add(new HostAndPort("192.168.19.128", 7003));
        hostAndPortSet.add(new HostAndPort("192.168.19.129", 7004));
        hostAndPortSet.add(new HostAndPort("192.168.19.129", 7005));
        hostAndPortSet.add(new HostAndPort("192.168.19.129", 7006));
        hostAndPortSet.add(new HostAndPort("192.168.19.130", 7007));
        hostAndPortSet.add(new HostAndPort("192.168.19.130", 7008));
        hostAndPortSet.add(new HostAndPort("192.168.19.130", 7009));

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMinIdle(1);
        jedisPoolConfig.setMaxWaitMillis(10000);

        jedisPoolConfig.setTestOnBorrow(true);
        jedis= new JedisCluster(hostAndPortSet, jedisPoolConfig);

    }

    @Test
    public void testKey() throws InterruptedException {
        System.out.println("判断某个键是否存在：" + jedis.exists("name"));
    }


}
