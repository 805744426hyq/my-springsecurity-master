package com.codermy.myspringsecurity;

import cn.hutool.crypto.SecureUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author codermy
 * @createTime 2020/6/26
 */
public class md5 {
    public static void main(String[] args) {

        String ad = "123456";
        System.out.println(SecureUtil.md5(ad));
        System.out.println(new BCryptPasswordEncoder().encode(ad));

    }
}
