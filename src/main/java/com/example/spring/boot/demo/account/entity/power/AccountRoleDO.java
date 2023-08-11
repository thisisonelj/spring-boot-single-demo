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
@Table(name = "account_role")
public class AccountRoleDO {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "power_id")
    private String powerId;
    @Column(name = "create_time")
    private Date createTime;

}
