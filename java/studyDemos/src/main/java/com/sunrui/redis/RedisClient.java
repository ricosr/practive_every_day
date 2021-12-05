package com.sunrui.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import org.junit.Test;


import java.util.HashSet;
import java.util.Set;

public class RedisClient {

    private static final JedisCluster jedis;
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

    public void print(Object content) {
        System.out.println(content);
    }

    /**
     * 测试key->value, value为字符串的操作
     * @throws InterruptedException
     */
    @Test
    public void testString() throws InterruptedException {
        System.out.println("新增<'username','rico'>的键值对：" + jedis.set("username", "rico"));
        System.out.println("新增<'password','123456'>的键值对：" + jedis.set("password", "123456"));
        System.out.println("查询username的value：" + jedis.get("username"));
        System.out.println("查询password的value：" + jedis.get("password"));
        System.out.println("查看键password所存储的值的类型："+jedis.type("password"));
        System.out.println("设置password过期时间：" + jedis.expire("password",
                10));
        Thread.sleep(2000);
        System.out.println("查看键password的剩余生存时间："+jedis.ttl("password"));
//        System.out.println("删除password的过期时间："+jedis.persist("password"));
        Thread.sleep(8000);
        System.out.println("判断password key是否存在：" + jedis.exists("password"));
        System.out.println("查看键password的剩余生存时间："+jedis.ttl("password"));
        System.out.println("查询password的value：" + jedis.get("password"));
        System.out.println("查询password的value：" + jedis.get("password"));

        System.out.println("删除key username：" + jedis.del("username"));
        System.out.println("判断password key是否存在：" + jedis.exists("password"));
        System.out.println("判断username key是否存在：" + jedis.exists("username"));
    }

    /**
     * 数字
     */
    @Test
    public void testNumber() {
        jedis.set("key1", "1");
        jedis.set("key2", "2");
        jedis.set("key3", "2.3");
        print("key1的值：" + jedis.get("key1"));
        print("key2的值：" + jedis.get("key2"));
        print("key1的值加1：" + jedis.incr("key1"));
        print("key1的值：" + jedis.get("key1"));
        print("key2的值减1：" + jedis.decr("key2"));
        print("key2的值：" + jedis.get("key2"));
        print("将key1的值加上整数5："+jedis.incrBy("key1", 5));
        print("key1的值：" + jedis.get("key1"));
        print("将key2的值加上整数5："+jedis.decrBy("key2", 5));
        print("key2的值：" + jedis.get("key2"));
        print("key3的值：" + jedis.get("key3"));

        // 这里会报错，因为key3不是整数不能做计算:redis.clients.jedis.exceptions.JedisDataException: ERR value is not an integer or out of range
        // System.out.println("key2的值减1："+jedis.decr("key3"));
    }

    /**
     * 列表
     */
    @Test
    public void testList() {
        print("===========添加一个list===========");
        jedis.lpush("collections", "ArrayList", "Vector", "Stack", "HashMap", "WeakHashMap", "LinkedHashMap");
        jedis.lpush("collections", "HashSet"); // 叠加
        jedis.lpush("collections", "TreeSet"); // 叠加
        jedis.lpush("collections", "TreeMap"); // 叠加
        jedis.lpush("collections", "TreeMap"); // 叠加
        print("collections的内容："+jedis.lrange("collections", 0, -1));//-1代表倒数第一个元素，-2代表倒数第二个元素
        print("collections区间0-3的元素："+jedis.lrange("collections",0,3)); // 前面4个值;

        // 删除列表指定的值 ，第二个参数为删除的个数（有重复时），后add进去的值先被删，类似于出栈
        print("删除指定元素个数：" + jedis.lrem("collections", 2, "TreeMap"));
        print("collections的内容："+jedis.lrange("collections", 0, -1));

        print("删除下标0-3区间之外的元素：" + jedis.ltrim("collections", 0, 3));
        print("collections的内容：" + jedis.lrange("collections", 0, -1));


        print("collections列表出栈（左端）：" + jedis.lpop("collections"));
        print("collections的内容：" + jedis.lrange("collections", 0, -1));

        print("collections添加元素，从列表右端，与lpush相对应：" + jedis.rpush("collections", "EnumMap"));
        print("collections的内容：" + jedis.lrange("collections", 0, -1));

        print("collections列表出栈（右端）：" + jedis.rpop("collections"));
        print("collections的内容：" + jedis.lrange("collections", 0, -1));

        print("修改collections指定下标1的内容：" + jedis.lset("collections", 1, "hhhhhhhh"));
        print("collections的内容：" + jedis.lrange("collections", 0, -1));

        print("collections的长度：" + jedis.llen("collections"));

        print("===============================");
        jedis.lpush("sortedList", "3","6","2","0","7","4");
        print("sortedList的内容：" + jedis.lrange("sortedList", 0, -1));
        print("sortedList排序后：" + jedis.sort("sortedList"));
    }

    /**
     * 集合
     */
    @Test
    public void testSet() {
        print("============向集合中添加元素============");
        print(jedis.sadd("eleSet", "e1", "e2", "e4", "e3", "e0", "e8", "e7", "e5"));
        print(jedis.sadd("eleSet", "e6"));
        print(jedis.sadd("eleSet", "e6"));  // 返回0，集合中已经存在
        print("eleSet的所有元素为：" + jedis.smembers("eleSet"));
        print("删除一个元素e0：" + jedis.srem("eleSet", "e0"));
        print("eleSet的所有元素为：" + jedis.smembers("eleSet"));
        print("删除两个元素e7和e6：" + jedis.srem("eleSet", "e7", "e6"));
        print("eleSet的所有元素为：" + jedis.smembers("eleSet"));
        print("随机的移除集合中的一个元素：" + jedis.spop("eleSet"));
        print("随机的移除集合中的一个元素：" + jedis.spop("eleSet"));
        print("eleSet的所有元素为：" + jedis.smembers("eleSet"));
        print("eleSet中包含元素的个数："+jedis.scard("eleSet"));
        print("e3是否在eleSet中："+jedis.sismember("eleSet", "e3"));
        print("e5是否在eleSet中："+jedis.sismember("eleSet", "e5"));
        print("e1是否在eleSet中："+jedis.sismember("eleSet", "e1"));

//        print(jedis.sadd("eleSet1", "e1","e2","e4","e3","e0","e8","e7","e5"));
//        print(jedis.sadd("eleSet2", "e1","e2","e4","e3","e0","e8"));
//        print("将eleSet1中删除e1并存入eleSet3中："+jedis.smove("eleSet1", "eleSet3", "e1"));
//        print("将eleSet1中删除e2并存入eleSet3中："+jedis.smove("eleSet1", "eleSet3", "e2"));
//        print("eleSet1中的元素："+jedis.smembers("eleSet1"));
//        print("eleSet3中的元素："+jedis.smembers("eleSet3"));


    }




}




// 从这里抄的：https://www.pianshen.com/article/745581073/






