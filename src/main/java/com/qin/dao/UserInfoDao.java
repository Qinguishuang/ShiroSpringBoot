package com.qin.dao;

import com.qin.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoDao extends JpaRepository<UserInfo, Long> {
    public UserInfo findByUsername(String username);
}
