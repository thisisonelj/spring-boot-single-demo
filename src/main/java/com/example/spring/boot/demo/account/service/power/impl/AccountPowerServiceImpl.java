package com.example.spring.boot.demo.account.service.power.impl;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.boot.demo.account.dao.power.AccountPowerMapper;
import com.example.spring.boot.demo.account.dao.power.AccountRoleMapper;
import com.example.spring.boot.demo.account.dto.AccountPowerDTO;
import com.example.spring.boot.demo.account.entity.power.AccountPowerDO;
import com.example.spring.boot.demo.account.entity.power.PowerEnum;
import com.example.spring.boot.demo.account.service.AccountPowerService;

/**
 * @Author liujun
 * @Date 2023/8/13 10:08
 * @Description
 */
@Service
public class AccountPowerServiceImpl implements AccountPowerService {
    @Autowired
    private AccountPowerMapper accountPowerMapper;
    @Autowired
    private AccountRoleMapper accountRoleMapper;

    @Override
    public List<AccountPowerDTO> queryPowerAll() {
        List<AccountPowerDTO> accountPowerDTOS = new ArrayList<>();
        List<AccountPowerDO> accountPowerDOList = accountPowerMapper.selectAll();
        Map<String, List<AccountPowerDO>> powerMap = accountPowerDOList.stream()
            .collect(Collectors.groupingBy(AccountPowerDO::getUserRoleId, Collectors.toList()));
        powerMap.forEach((item, powerList) -> {
            AccountPowerDO accountPowerDO = powerList.stream().findFirst().get();
            AccountPowerDTO accountPowerDTO = new AccountPowerDTO();
            accountPowerDTO.setUserRoleId(item);
            accountPowerDTO.setUserRoleValue(accountPowerDO.getUserRoleValue());
            accountPowerDTO.setCreateTime(accountPowerDO.getCreateTime());
            powerList.forEach(e -> {
                if (e.getPowerId().equals(PowerEnum.USERMANAGE.toString())) {
                    accountPowerDTO.setUserManage(e.getPowerValue());
                }
                if (e.getPowerId().equals(PowerEnum.CMMANAGE.toString())) {
                    accountPowerDTO.setCmManage(e.getPowerValue());
                }
                if (e.getPowerId().equals(PowerEnum.SYSTEMSETTING.toString())) {
                    accountPowerDTO.setSystemSetting(e.getPowerValue());
                }
                if (e.getPowerId().equals(PowerEnum.VOUCHERMANAGE.toString())) {
                    accountPowerDTO.setVoucherManage(e.getPowerValue());
                }
                if (e.getPowerId().equals(PowerEnum.BOOKMANAGE.toString())) {
                    accountPowerDTO.setBookManage(e.getPowerValue());
                }
            });
            accountPowerDTOS.add(accountPowerDTO);
        });
        return accountPowerDTOS;
    }

    @Override
    public List<AccountPowerDTO> queryPowerList(List<AccountPowerDO> accountPowerDOList) {
        if (!accountPowerDOList.isEmpty()) {
            List<AccountPowerDTO> accountPowerDTOS = new ArrayList<>();
            accountPowerDOList.forEach(item -> {
                Date powerTime = new Date();
                if (accountPowerMapper.select(item).size() == 0) {
                    AccountPowerDTO accountPowerDTO = new AccountPowerDTO();
                    accountPowerDTO.setUserRoleId(item.getUserRoleId());
                    accountPowerDTO.setUserRoleValue(item.getUserRoleValue());
                    accountPowerDTO.setCreateTime(powerTime);
                    accountPowerDTO.setUserManage(false);
                    accountPowerDTO.setVoucherManage(false);
                    accountPowerDTO.setCmManage(false);
                    accountPowerDTO.setSystemSetting(false);
                    accountPowerDTO.setBookManage(false);
                    accountPowerDTOS.add(accountPowerDTO);
                    Arrays.stream(PowerEnum.values()).forEach(e -> {
                        AccountPowerDO accountPowerDO = new AccountPowerDO();
                        String id = UUID.randomUUID().toString();
                        accountPowerDO.setId(id);
                        accountPowerDO.setUserRoleId(item.getUserRoleId());
                        accountPowerDO.setUserRoleValue(item.getUserRoleValue());
                        accountPowerDO.setPowerId(e.toString());
                        accountPowerDO.setPowerValue(false);
                        accountPowerDO.setCreateTime(powerTime);
                        accountPowerMapper.addAccountPower(accountPowerDO);
                    });
                } else {
                    List<AccountPowerDO> powerList = accountPowerMapper.select(item);
                    AccountPowerDTO accountPowerDTO = new AccountPowerDTO();
                    accountPowerDTO.setUserRoleId(item.getUserRoleId());
                    accountPowerDTO.setUserRoleValue(item.getUserRoleValue());
                    accountPowerDTO.setCreateTime(powerList.stream().findFirst().get().getCreateTime());
                    for (AccountPowerDO e : powerList) {
                        if (e.getPowerId().equals(PowerEnum.USERMANAGE.toString())) {
                            accountPowerDTO.setUserManage(e.getPowerValue());
                        }
                        if (e.getPowerId().equals(PowerEnum.CMMANAGE.toString())) {
                            accountPowerDTO.setCmManage(e.getPowerValue());
                        }
                        if (e.getPowerId().equals(PowerEnum.SYSTEMSETTING.toString())) {
                            accountPowerDTO.setSystemSetting(e.getPowerValue());
                        }
                        if (e.getPowerId().equals(PowerEnum.VOUCHERMANAGE.toString())) {
                            accountPowerDTO.setVoucherManage(e.getPowerValue());
                        }
                        if (e.getPowerId().equals(PowerEnum.BOOKMANAGE.toString())) {
                            accountPowerDTO.setBookManage(e.getPowerValue());
                        }
                    } ;
                    accountPowerDTOS.add(accountPowerDTO);
                }
            });
            return accountPowerDTOS;
        }
        return queryPowerAll();
    }

    @Override
    public List<AccountPowerDTO> updateAccountPower(List<AccountPowerDTO> accountPowerDTOList) {
        accountPowerDTOList.forEach(item -> {
            AccountPowerDO accountPowerDO = new AccountPowerDO();
            accountPowerDO.setUserRoleId(item.getUserRoleId());
            accountPowerMapper.delete(accountPowerDO);
            HashMap<String, Boolean> powerMap = new HashMap<>();
            powerMap.put(PowerEnum.USERMANAGE.toString(), item.getUserManage());
            powerMap.put(PowerEnum.SYSTEMSETTING.toString(), item.getSystemSetting());
            powerMap.put(PowerEnum.VOUCHERMANAGE.toString(), item.getVoucherManage());
            powerMap.put(PowerEnum.CMMANAGE.toString(), item.getCmManage());
            powerMap.put(PowerEnum.BOOKMANAGE.toString(), item.getBookManage());
            powerMap.forEach((e, value) -> {
                String id = UUID.randomUUID().toString();
                AccountPowerDO accountPowerDO1 = new AccountPowerDO();
                accountPowerDO1.setId(id);
                accountPowerDO1.setCreateTime(item.getCreateTime());
                accountPowerDO1.setUserRoleId(item.getUserRoleId());
                accountPowerDO1.setUserRoleValue(item.getUserRoleValue());
                accountPowerDO1.setPowerId(e);
                accountPowerDO1.setPowerValue(value);
                accountPowerMapper.addAccountPower(accountPowerDO1);
            });
        });
        List<AccountPowerDO> powerList = accountPowerDTOList.stream().map(k -> {
            AccountPowerDO accountPowerDO = new AccountPowerDO();
            accountPowerDO.setUserRoleId(k.getUserRoleId());
            accountPowerDO.setUserRoleValue(k.getUserRoleValue());
            return accountPowerDO;
        }).collect(Collectors.toList());
        if (powerList.size() == 0) {
            return queryPowerAll();
        }
        return queryPowerList(powerList);
    }

    @Override
    public String deleteAccountPower(AccountPowerDTO accountPowerDTO) {
      AccountPowerDO accountPowerDO=new AccountPowerDO();
      accountPowerDO.setUserRoleId(accountPowerDTO.getUserRoleId());
      accountPowerMapper.delete(accountPowerDO);
      return accountPowerDTO.getUserRoleId();
    }
}
