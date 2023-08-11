package com.example.spring.boot.demo.account.service.power;

import com.example.spring.boot.demo.account.entity.power.AccountUserDO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AccountUserService {
    /**
     * @Author LiuJun
     * @Description 添加用户
     * @Date 19:39 2023/8/8
     * @Param [accountUserDO]
     * @Return java.lang.Integer
     **/
    Integer addAccountUser(AccountUserDO accountUserDO);
    /**
     * @Author LiuJun
     * @Description  用户登录
     * @Date 10:03 2023/8/9
     * @Param [accountUserDO]
     * @Return java.lang.Boolean
     **/
    Boolean AccountUserLogin(AccountUserDO accountUserDO, HttpServletResponse response,HttpServletRequest request);
}
