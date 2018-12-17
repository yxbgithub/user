package com.yxb.server.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Author by yanxiaobo
 * Date on 2018/12/16$.
 */
@Data
@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    private String id;
    private String username;
    private String password;
    private String openid;
    private Integer role;
    private Date create_time;
    private Date update_time;
}
