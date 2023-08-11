package com.example.spring.boot.demo.account.entity.power;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author liujun
 * @Date 2023/8/8 19:55
 * @Description
 */
@Data
@Entity
@Table(name = "account_user")
public class AccountUserDO {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "role_id")
    private String roleId;
    @Column(name = "power_id")
    private String powerId;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "age")
    private Integer age;
    @Column(name = "password")
    private String passWord;

}
