package com.example.spring.boot.demo.account.controller.power;

import com.example.spring.boot.demo.account.dto.AccountPowerDTO;
import com.example.spring.boot.demo.account.entity.power.AccountPowerDO;
import com.example.spring.boot.demo.account.service.AccountPowerService;
import com.example.spring.boot.demo.account.utils.AcctResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *@Author liujun
 *@Date 2023/8/13 10:10
 *@Description
 */
@RestController
@RequestMapping(value = "/rest/account/power")
public class AccountPowerController {
    @Autowired
    private AccountPowerService accountPowerService;

    @PostMapping("/queryall")
    public AcctResult<Object> queryPowerAll(@RequestBody AccountPowerDO accountPowerDO){
        return AcctResult.resultSuccessful(accountPowerService.queryPowerAll());
    }

    @PostMapping("/selectlist")
    public AcctResult<Object> queryPowerList(@RequestBody List<AccountPowerDO> accountPowerDOList){
        return AcctResult.resultSuccessful(accountPowerService.queryPowerList(accountPowerDOList));
    }

    @PostMapping("/update")
    public AcctResult<Object> updateAccountPower(@RequestBody List<AccountPowerDTO> accountPowerDTOList){
        return AcctResult.resultSuccessful(accountPowerService.updateAccountPower(accountPowerDTOList));
    }

    @PostMapping("/delete")
    public AcctResult<Object> updateAccountPower(@RequestBody AccountPowerDTO accountPowerDTO){
        return AcctResult.resultSuccessful(accountPowerService.deleteAccountPower(accountPowerDTO));
    }

    @PostMapping("/import")
    public AcctResult<Object> importAccountPower(@RequestParam("file") MultipartFile file){
        return AcctResult.resultSuccessful(accountPowerService.importAccountPower(file));
    }

    @PostMapping("/export")
    public void exportAccountPower(@RequestBody List<AccountPowerDTO> accountPowerDTOList, HttpServletResponse response){
       accountPowerService.exportAccountPower(accountPowerDTOList,response);
    }
}