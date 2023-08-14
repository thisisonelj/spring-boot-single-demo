package com.example.spring.boot.demo.account.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.spring.boot.demo.account.dto.AccountPowerDTO;
import com.example.spring.boot.demo.account.entity.power.AccountPowerDO;

import javax.servlet.http.HttpServletResponse;

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

    List<AccountPowerDTO> importAccountPower(MultipartFile uploadFile);

    void exportAccountPower(List<AccountPowerDTO> accountPowerDTOS, HttpServletResponse response);

}
