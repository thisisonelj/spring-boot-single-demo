package com.example.spring.boot.demo.account.service.power.impl;

import com.example.spring.boot.demo.account.dao.power.AccountRoleMapper;
import com.example.spring.boot.demo.account.entity.power.AccountRoleDO;
import com.example.spring.boot.demo.account.service.power.AccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author liujun
 * @Date 2023/8/12 7:48
 * @Description
 */
@Service
public class AccountRoleServiceImpl implements AccountRoleService {
    @Autowired
    private AccountRoleMapper accountRoleMapper;

    @Override
    public Integer roleAdd(AccountRoleDO accountRoleDO) {
        String roleId = UUID.randomUUID().toString();
        accountRoleDO.setId(roleId);
        return accountRoleMapper.insert(accountRoleDO);
    }

    @Override
    public List<AccountRoleDO> queryRoleAll(AccountRoleDO accountRoleDO) {
        return accountRoleMapper.selectAll();
    }

    @Override
    public Integer roleUpdate(List<AccountRoleDO> accountRoleDOList) {
        List<Integer> updateBatchList = new ArrayList<>();
        accountRoleDOList.forEach((item) -> {
            updateBatchList.add(accountRoleMapper.updateByPrimaryKey(item));
        });
        return updateBatchList.size();
    }

    @Override
    public Integer roleDelete(AccountRoleDO accountRoleDO) {
        return accountRoleMapper.delete(accountRoleDO);
    }
}
