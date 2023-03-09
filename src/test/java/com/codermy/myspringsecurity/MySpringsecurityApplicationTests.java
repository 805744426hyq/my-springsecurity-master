package com.codermy.myspringsecurity;


import com.codermy.myspringsecurity.utils.RedisLock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class MySpringsecurityApplicationTests {

    @Resource
    private RedisLock redisLock;


    @Test
    void contextLoads() {
        boolean b = redisLock.lock("123", "hhh");
        boolean b1 = redisLock.unlock("123", "hhh");
        String lock = b ? "加锁成功" : "加锁失败";
        String unlock = b1 ? "解锁成功" : "解锁锁失败";
        System.out.println(lock + "," + unlock);

    }

}
