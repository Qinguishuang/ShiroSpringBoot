package com.qin.chapter5;

import com.qin.BaseTest;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class PasswordTest extends BaseTest {
    @Test
    public void testGeneratePassword() {
        String algorithmName = "md5";
        String username = "liu";
        String password = "123";
        String salt1 = username;
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashIterations = 2;

        SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt1, hashIterations);
        String encodePassword = hash.toHex();
        System.out.println(salt2);
        System.out.println(encodePassword);
    }
}
