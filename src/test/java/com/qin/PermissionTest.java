package com.qin;

import org.junit.Test;

public class PermissionTest extends BaseTest {
    @Test
    public void testIsPermitted() {
        login("classpath:shiro-permission.ini", "zhang", "123");
        System.out.println(subject().isPermitted("user:create"));
        System.out.println(subject().isPermittedAll("user:update", "user:delete"));
        System.out.println(subject().isPermitted("user:view"));
    }
}
