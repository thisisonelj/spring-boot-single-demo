package com.example.spring.boot.demo.account.service.power.impl;

import com.example.spring.boot.demo.account.dao.power.AccountUserMapper;
import com.example.spring.boot.demo.account.entity.power.AccountUserDO;
import com.example.spring.boot.demo.account.service.power.AccountUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class AccountUserServiceImpl implements AccountUserService {
    @Autowired
    private AccountUserMapper accountUserMapper;

    @Qualifier("redisOperation")
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Integer addAccountUser(AccountUserDO accountUserDO) {
        return accountUserMapper.addAccountUser(accountUserDO);
    }

    @Override
    public Boolean AccountUserLogin(AccountUserDO accountUserDO, HttpServletResponse response, HttpServletRequest request) {
        List<AccountUserDO> accountUserDOlist = accountUserMapper.select(accountUserDO);
        if (accountUserDOlist.isEmpty() || accountUserDOlist == null || accountUserDOlist.size() == 0) {
            return false;
        }
        if (StringUtils.isEmpty(request.getHeader("token"))||"undefined".equals(request.getHeader("token"))|| "null".equals(request.getHeader("token"))) {
            String token = UUID.randomUUID().toString();
            redisTemplate.opsForValue().set(token, accountUserDO,60*10, TimeUnit.SECONDS);
            response.setHeader("token", token);
            response.setHeader("Access-Control-Expose-Headers", "token");
        }
        response.setHeader("errorInfo", "token success");
        return true;
    }
}
