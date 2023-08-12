package com.example.spring.boot.demo.account.service.power;

import com.example.spring.boot.demo.account.dto.AccountUserDTO;
import com.example.spring.boot.demo.account.entity.power.AccountRoleDO;
import com.example.spring.boot.demo.account.entity.power.AccountUserDO;
import com.example.spring.boot.demo.account.entity.power.AccountUserRoleDO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    List<String> userAdd(AccountUserDTO accountUserDTO);
    List<AccountUserDTO> queryUserAll();
    Integer userUpdate(List<AccountUserDO> accountRoleDOList);
    Integer userDelete(AccountRoleDO accountRoleDO);
}
