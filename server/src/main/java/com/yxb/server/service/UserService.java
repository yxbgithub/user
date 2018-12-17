package com.yxb.server.service;

import com.yxb.server.model.UserInfo;

/**
 * Author by yanxiaobo
 * Date on 2018/12/16$.
 */
public interface UserService {
    UserInfo findByOpenId(String openId);

}
