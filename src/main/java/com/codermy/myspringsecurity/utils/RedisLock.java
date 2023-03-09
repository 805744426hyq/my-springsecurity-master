package com.codermy.myspringsecurity.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


@Component
public class RedisLock {
    /**
     * 解锁lua脚本
     */
    private static final String UNLOCK_SCRIPT = "if redis.call('get', KEYS[1]) == KEYS[2] then return redis.call('del', KEYS[1]) else return 0 end";
    // 2分钟的毫秒数
    private static final long TIMEOUT = 120L * 100;
    private static final Long RELEASE_SUCCESS = 1L;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private DefaultRedisScript<Long> redisScript;

    /**
     * 上锁
     *
     * @param key   锁标识
     * @param value 线程标识
     * @return 上锁状态
     */
    public boolean lock(String key, String value) {
        long start = System.currentTimeMillis();
        // 检测是否超时
        if (System.currentTimeMillis() - start > TIMEOUT) {
            return false;
        }
        // 执行set命令,是否成功获取锁
        // 基于Redis命令：SET key value NX EX milliseconds
        // NX表示只有当键key不存在的时候才会设置key的值，PX表示设置键key的过期时间，单位是毫秒
        return Objects.equals(Boolean.TRUE, redisTemplate.opsForValue().setIfAbsent(key, value, TIMEOUT, TimeUnit.MILLISECONDS));
    }

    /**
     * 解锁,成功会删除key
     * 如果传入的key value和加锁时不一致，解锁失败，返回false
     *
     * @param key   锁标识
     * @param value 线程标识
     * @return 解锁状态
     */
    public boolean unlock(String key, String value) {
        // 使用Lua脚本：先判断是否是自己设置的锁，再执行删除
        Long result = redisTemplate.execute(redisScript, Arrays.asList(key, value));
        // 返回最终结果
        return Objects.equals(RELEASE_SUCCESS, result);
    }

    /**
     * @return lua脚本
     */
    @Bean
    private DefaultRedisScript<Long> defaultRedisScript() {
        return new DefaultRedisScript<>(UNLOCK_SCRIPT, Long.class);
    }


}
