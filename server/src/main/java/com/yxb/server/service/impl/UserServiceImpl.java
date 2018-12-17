package com.yxb.server.service.impl;

import com.yxb.server.model.UserInfo;
import com.yxb.server.repository.UserRepository;
import com.yxb.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author by yanxiaobo
 * Date on 2018/12/16$.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserInfo findByOpenId(String openId) {
        return userRepository.findByOpenid(openId);
    }
}
