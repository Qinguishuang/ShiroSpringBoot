package com.qin;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class RoleTest extends BaseTest {

    @Test
    public void testHasRole() {
        login("classpath:shiro-role.ini", "zhang", "123");
        System.out.println(subject().hasRole("role1"));
        System.out.println(subject().hasAllRoles(Arrays.asList("role1", "role2")));

        boolean[] result = subject().hasRoles(Arrays.asList("role1", "role2", "role3"));
        System.out.println(Arrays.toString(result));
    }

    @Test
    public void testCheckRole() {
        login("classpath:shiro-role.ini", "zhang", "123");
        subject().checkRole("role1");
        subject().checkRoles("role1", "role3");
    }
}
