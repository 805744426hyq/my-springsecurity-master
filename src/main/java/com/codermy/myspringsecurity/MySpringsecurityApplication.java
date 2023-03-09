package com.codermy.myspringsecurity;

import com.codermy.myspringsecurity.netty.NettyServerHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.annotation.Resource;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySpringsecurityApplication implements CommandLineRunner {
    @Resource
    private NettyServerHandler nettyServerHandler;

    public static void main(String[] args) {
        SpringApplication.run(MySpringsecurityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
