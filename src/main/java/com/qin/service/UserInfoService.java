package com.qin.service;

import com.qin.entity.UserInfo;


public interface UserInfoService {
    public UserInfo findByUsername(String userName);
}
