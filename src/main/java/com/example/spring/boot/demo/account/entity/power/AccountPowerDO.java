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
@Table(name = "account_power")
public class AccountPowerDO {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "user_role_id")
    private String userRoleId;
    @Column(name = "user_role_value")
    private String userRoleValue;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "power_id")
    private String powerId;
    @Column(name = "power_value")
    private Boolean powerValue;

}
