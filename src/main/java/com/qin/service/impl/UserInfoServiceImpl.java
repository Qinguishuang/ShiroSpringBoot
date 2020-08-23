package com.qin.service.impl;

import com.qin.dao.UserInfoDao;
import com.qin.entity.UserInfo;
import com.qin.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    UserInfoDao userInfoDao;

    @Override
    public UserInfo findByUsername(String userName) {
        return userInfoDao.findByUsername(userName);
    }
}
