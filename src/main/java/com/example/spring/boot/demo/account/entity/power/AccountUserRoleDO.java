package com.example.spring.boot.demo.account.entity.power;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author liujun
 * @Date 2023/8/12 18:02
 * @Description
 */
@Data
@Entity
@Table(name = "account_user_role")
public class AccountUserRoleDO {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "role_id")
    private String roleId;
}
