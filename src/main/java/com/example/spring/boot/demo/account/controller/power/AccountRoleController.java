package com.example.spring.boot.demo.account.controller.power;

import com.example.spring.boot.demo.account.entity.power.AccountRoleDO;
import com.example.spring.boot.demo.account.service.power.AccountRoleService;
import com.example.spring.boot.demo.account.utils.AcctResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author liujun
 * @Date 2023/8/12 7:50
 * @Description
 */
@RestController
@RequestMapping(value = "/rest/account/role")
public class AccountRoleController {
    @Autowired
    private AccountRoleService accountRoleService;

    @PostMapping("/add")
    public AcctResult<Object> accountRoleAdd(@RequestBody AccountRoleDO accountRoleDO) {
        return AcctResult.resultSuccessful(accountRoleService.roleAdd(accountRoleDO));
    }
    @PostMapping("/queryall")
    public AcctResult<Object> accountRoleQueryAll(@RequestBody AccountRoleDO accountRoleDO) {
        return AcctResult.resultSuccessful(accountRoleService.queryRoleAll(accountRoleDO));
    }
    @PostMapping("/update")
    public AcctResult<Object> accountRoleUpdate(@RequestBody List<AccountRoleDO> accountRoleDOList) {
        return AcctResult.resultSuccessful(accountRoleService.roleUpdate(accountRoleDOList));
    }
    @PostMapping("/delete")
    public AcctResult<Object> accountRoleUpdate(@RequestBody AccountRoleDO accountRoleDO) {
        return AcctResult.resultSuccessful(accountRoleService.roleDelete(accountRoleDO));
    }
}
