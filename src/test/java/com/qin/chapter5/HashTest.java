package com.qin.chapter5;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Test;

public class HashTest {
    @Test
    public void hashTest() {

        DefaultHashService service = new DefaultHashService();
        service.setHashAlgorithmName("sha-512"); // 设置默认算法
        service.setPrivateSalt(new SimpleByteSource("123")); // 私盐 默认没有
        service.setGeneratePublicSalt(true); // 没有传入时生成公盐
        service.setRandomNumberGenerator(new SecureRandomNumberGenerator()); // 生成公盐
        service.setHashIterations(1); // 迭代次数
        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello"))
                .setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();
        String hex = service.computeHash(request).toHex();
        System.out.println(hex);


//        String str = "hello";
//        String salt = "123";
//        String simpleHash = new SimpleHash("sha-1", str, salt).toString();
//        System.out.println(simpleHash);
    }
}
