package com.example.spring.boot.demo.account.entity.extra;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @Author liujun
 * @Date 2023/8/14 14:41
 * @Description
 */
@Data
public class ExportPowerInfo extends BaseRowModel {
    @ExcelProperty(value = "用户/角色标识" ,index = 0)
    private String userRoleId;
    @ExcelProperty(value = "用户/角色名" ,index = 1)
    private String userRoleName;
    @ExcelProperty(value = "用户管理" ,index = 2)
    private Boolean userManage;
    @ExcelProperty(value = "系统管理" ,index = 3)
    private Boolean systemSetting;
    @ExcelProperty(value = "出纳管理" ,index = 4)
    private Boolean cmManage;
    @ExcelProperty(value = "凭证管理" ,index = 5)
    private Boolean voucherManage;
    @ExcelProperty(value = "账簿管理" ,index = 6)
    private Boolean bookManage;

}
