package com.example.spring.boot.demo.account.service.power;

import com.example.spring.boot.demo.account.entity.power.AccountRoleDO;

import java.util.List;

/**
 * @Author liujun
 * @Date 2023/8/12 7:46
 * @Description
 */
public interface AccountRoleService {
    Integer roleAdd(AccountRoleDO accountRoleDO);
    List<AccountRoleDO> queryRoleAll(AccountRoleDO accountRoleDO);
    Integer roleUpdate(List<AccountRoleDO> accountRoleDOList);
    Integer roleDelete(AccountRoleDO accountRoleDO);
}
