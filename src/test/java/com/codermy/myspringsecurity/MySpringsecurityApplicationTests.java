package com.codermy.myspringsecurity;


import cn.hutool.bloomfilter.BitMapBloomFilter;
import cn.hutool.crypto.SecureUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MySpringsecurityApplicationTests {
//    @Autowired
//    private PermissionDao permissionDao;

    @Test
    void contextLoads() {
//        List<PermissionDto> listByRoleId = permissionDao.listByRoleId(2);
//        List<PermissionDto> permissionDtos = permissionDao.buildAll();
//        List<PermissionDto> tree = TreeUtil.tree(listByRoleId, permissionDtos);
//        System.out.println(tree);
        BitMapBloomFilter filter = new BitMapBloomFilter(10);
        filter.add("123");
        filter.add("abc");
        filter.add("ddd");
        String s = SecureUtil.md5("123456");
        System.out.println(s);

// 查找
        boolean abc = filter.contains("abc");
        boolean contains = filter.contains("444");
        System.out.println(abc);
        System.out.println(contains);

    }

}
