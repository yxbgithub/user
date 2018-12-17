package com.yxb.server.controller;

import com.yxb.server.base.ServerResponse;
import com.yxb.server.constant.CookieConstant;
import com.yxb.server.constant.RedisConstant;
import com.yxb.server.enums.RoleEnum;
import com.yxb.server.model.UserInfo;
import com.yxb.server.service.UserService;
import com.yxb.server.utils.CookieUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Author by yanxiaobo
 * Date on 2018/12/16$.
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @GetMapping("/buyer")
    public ServerResponse buyer(@RequestParam("openid") String openid, HttpServletResponse response) {
        UserInfo userInfo = userService.findByOpenId(openid);
        if (null == userInfo)
            return ServerResponse.createByErrorMsg("用户不存在");
        if (!userInfo.getRole().equals(RoleEnum.BUYER.getCode()))
            return ServerResponse.createByErrorMsg("角色不匹配");
        CookieUtils.setCookie(response, CookieConstant.OPENID, openid, CookieConstant.expire);
        return ServerResponse.createBySuccess();
    }

    @GetMapping("/seller")
    public ServerResponse seller(@RequestParam("openid") String openid, HttpServletRequest request, HttpServletResponse response) {
        //判断是否已经登陆
        String token = CookieUtils.getCookieValue(request, CookieConstant.TOKEN);
        if (StringUtils.isNotEmpty(token)) {
            String value = stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, token));
            if (StringUtils.isNotEmpty(value))
                return ServerResponse.createBySuccess();
        }
        UserInfo userInfo = userService.findByOpenId(openid);
        if (null == userInfo)
            return ServerResponse.createByErrorMsg("用户不存在");
        if (!userInfo.getRole().equals(RoleEnum.SELLER.getCode()))
            return ServerResponse.createByErrorMsg("角色不匹配");
        //写入redis
        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, uuid),
                openid,
                CookieConstant.expire,
                TimeUnit.SECONDS);
        //写入cookie
        CookieUtils.setCookie(response, CookieConstant.TOKEN, uuid, CookieConstant.expire);
        return ServerResponse.createBySuccess();
    }
}
