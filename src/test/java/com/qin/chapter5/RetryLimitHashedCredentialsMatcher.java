package com.qin.chapter5;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
    private Ehcache passwordEhcache;

    private RetryLimitHashedCredentialsMatcher() {
        CacheManager cacheManager = CacheManager.newInstance(Objects.requireNonNull(CacheManager.class.getClassLoader().getResource("ehcache.xml")));
        passwordEhcache = cacheManager.getEhcache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        Element element = passwordEhcache.get(username);

        if (element != null) {
            element = new Element(username, new AtomicInteger(0));
            passwordEhcache.put(element);
        }
        AtomicInteger retryCount = (AtomicInteger) element.getObjectValue();
        if (retryCount.incrementAndGet() > 5) {
            throw new ExcessiveAttemptsException();
        }
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            passwordEhcache.remove(username);
        }
        return matches;
    }
}
