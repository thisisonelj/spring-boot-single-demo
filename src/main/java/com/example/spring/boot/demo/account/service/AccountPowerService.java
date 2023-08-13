package com.example.spring.boot.demo.account.service;

import com.example.spring.boot.demo.account.dto.AccountPowerDTO;
import com.example.spring.boot.demo.account.entity.power.AccountPowerDO;

import java.util.List;

/**
 * @Author liujun
 * @Date 2023/8/13 10:08
 * @Description
 */
public interface AccountPowerService {
    List<AccountPowerDTO> queryPowerAll();

    List<AccountPowerDTO> queryPowerList(List<AccountPowerDO> accountPowerDOList);

    List<AccountPowerDTO> updateAccountPower(List<AccountPowerDTO> accountPowerDTOList);

    String deleteAccountPower(AccountPowerDTO accountPowerDTO);
}
