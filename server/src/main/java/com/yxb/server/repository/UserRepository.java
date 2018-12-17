package com.yxb.server.repository;

import com.yxb.server.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author by yanxiaobo
 * Date on 2018/12/16$.
 */
public interface UserRepository extends JpaRepository<UserInfo,String> {

    UserInfo findByOpenid(String openId);
}
