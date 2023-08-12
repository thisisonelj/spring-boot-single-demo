package com.example.spring.boot.demo.account.dto;

import com.example.spring.boot.demo.account.entity.power.AccountRoleDO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author liujun
 * @Date 2023/8/12 16:27
 * @Description
 */
@Data
public class AccountUserDTO {
    private String id;
    private String userName;
    private String roleName;
    private Integer age;
    private String passWord;
    private Date createTime;
    private List<AccountRoleDO> accountRoleDOList;
    private String roleId;
}
