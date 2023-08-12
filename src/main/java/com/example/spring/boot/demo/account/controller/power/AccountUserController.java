package com.example.spring.boot.demo.account.controller.power;

import com.example.spring.boot.demo.account.dto.AccountUserDTO;
import com.example.spring.boot.demo.account.entity.power.AccountUserDO;
import com.example.spring.boot.demo.account.service.power.AccountUserService;
import com.example.spring.boot.demo.account.utils.AcctResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author liujun
 * @Date 2023/8/9 10:09
 * @Description
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/account/user")
public class AccountUserController {
    @Autowired
    private AccountUserService accountUserService;

    @PostMapping(value = "/login")
    AcctResult<Object> accountUserLogin(@RequestBody AccountUserDO accountUserDO, HttpServletResponse response, HttpServletRequest request) {
        return AcctResult.resultSuccessful(accountUserService.AccountUserLogin(accountUserDO,response,request));
    }

    @PostMapping(value = "/add")
    AcctResult<Object> accountUserAdd(@RequestBody AccountUserDTO accountUserDTO, HttpServletResponse response, HttpServletRequest request) {
        return AcctResult.resultSuccessful(accountUserService.userAdd(accountUserDTO));
    }

    @PostMapping(value = "/queryall")
    AcctResult<Object> accountUserQuery(@RequestBody AccountUserDTO accountUserDTO, HttpServletResponse response, HttpServletRequest request) {
        return AcctResult.resultSuccessful(accountUserService.queryUserAll());
    }

    @PostMapping(value = "/update")
    AcctResult<Object> accountUserUpdate(@RequestBody List<AccountUserDTO> accountUserDTOList, HttpServletResponse response, HttpServletRequest request) {
        return AcctResult.resultSuccessful(accountUserService.userUpdate(accountUserDTOList));
    }

    @PostMapping(value = "/delete")
    AcctResult<Object> accountUserDelete(@RequestBody AccountUserDO accountUserDO, HttpServletResponse response, HttpServletRequest request) {
        return AcctResult.resultSuccessful(accountUserService.AccountUserLogin(accountUserDO,response,request));
    }

}
