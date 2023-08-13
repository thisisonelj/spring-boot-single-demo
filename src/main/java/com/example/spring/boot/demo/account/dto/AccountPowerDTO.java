package com.example.spring.boot.demo.account.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author liujun
 * @Date 2023/8/13 16:14
 * @Description
 */
@Data
public class AccountPowerDTO {
    private String id;
    private String userRoleId;
    private String userRoleValue;
    private Boolean userManage;
    private Boolean systemSetting;
    private Boolean cmManage;
    private Boolean voucherManage;
    private Boolean bookManage;
    private Date createTime;
}
