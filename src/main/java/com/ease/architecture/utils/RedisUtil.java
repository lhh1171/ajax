package com.ease.architecture.utils;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedisUtil {

    private static RedisUtil instance = new RedisUtil();

    private RedissonClient redissonClient;

    private RedisUtil() {

    }


    public static RedisUtil getInstance() {
        if (instance == null) {
            synchronized (MysqlConnectionUtil.class) {
                if (instance == null) {
                    instance = new RedisUtil();
                }
            }
        }
        return instance;
    }


    public RedissonClient getConnection() {
        Config config = new Config();

        config.useSingleServer().setAddress("127.0.0.1:6379");

        redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
