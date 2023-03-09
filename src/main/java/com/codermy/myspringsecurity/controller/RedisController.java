package com.codermy.myspringsecurity.controller;

import com.codermy.myspringsecurity.utils.RedisLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author huangyongqi
 * @date 2023/3/9
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisLock redisLock;


    @GetMapping("/lock")
    public String lock() {
        boolean b = redisLock.lock("123", "hhh");
        boolean b1 = redisLock.unlock("123", "hhh");
        String lock = b ? "加锁成功" : "加锁失败";
        String unlock = b1 ? "解锁成功" : "解锁锁失败";
        return lock + "," + unlock;
    }
}
