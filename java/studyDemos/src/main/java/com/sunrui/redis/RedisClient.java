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

    /**
     * 测试key->value
     * @throws InterruptedException
     */
    @Test
    public void testKey() throws InterruptedException {
        System.out.println("新增<'username','rico'>的键值对：" + jedis.set("username", "rico"));
        System.out.println("新增<'password','123456'>的键值对：" + jedis.set("password", "123456"));
        System.out.println("查询username的value：" + jedis.get("username"));
        System.out.println("查询password的value：" + jedis.get("password"));
        System.out.println("查看键password所存储的值的类型："+jedis.type("password"));


        System.out.println("设置password过期时间：" + jedis.expire("password", 10));
        Thread.sleep(2000);
        System.out.println("查看键password的剩余生存时间："+jedis.ttl("password"));
//        System.out.println("删除password的过期时间："+jedis.persist("password"));
        Thread.sleep(8000);
        System.out.println("判断password key是否存在：" + jedis.exists("password"));
        System.out.println("查看键password的剩余生存时间："+jedis.ttl("password"));
        System.out.println("查询password的value：" + jedis.get("password"));
        System.out.println("查询password的value：" + jedis.get("password"));

//        Set<String> allKeys = jedis.keys("*");
//        for (String key: allKeys) {
//            System.out.println(key);
//        }
//        System.out.println("查询所有的key:" + jedis.keys("*").toString());
        System.out.println("删除key username：" + jedis.del("username"));
        System.out.println("判断password key是否存在：" + jedis.exists("password"));
        System.out.println("判断username key是否存在：" + jedis.exists("username"));

    }


}
