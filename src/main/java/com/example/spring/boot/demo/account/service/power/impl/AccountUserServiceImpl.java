package com.example.spring.boot.demo.account.service.power.impl;

import com.example.spring.boot.demo.account.dao.power.AccountUserMapper;
import com.example.spring.boot.demo.account.dao.power.AccountUserRoleMapper;
import com.example.spring.boot.demo.account.dto.AccountUserDTO;
import com.example.spring.boot.demo.account.entity.power.AccountRoleDO;
import com.example.spring.boot.demo.account.entity.power.AccountUserDO;
import com.example.spring.boot.demo.account.entity.power.AccountUserRoleDO;
import com.example.spring.boot.demo.account.service.power.AccountUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class AccountUserServiceImpl implements AccountUserService {
    @Autowired
    private AccountUserMapper accountUserMapper;
    @Autowired
    private AccountUserRoleMapper accountUserRoleMapper;

    @Qualifier("redisOperation")
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Integer addAccountUser(AccountUserDO accountUserDO) {
        return accountUserMapper.addAccountUser(accountUserDO);
    }

    @Override
    public Boolean AccountUserLogin(AccountUserDO accountUserDO, HttpServletResponse response,
        HttpServletRequest request) {
        List<AccountUserDO> accountUserDOlist = accountUserMapper.select(accountUserDO);
        if (accountUserDOlist.isEmpty() || accountUserDOlist == null || accountUserDOlist.size() == 0) {
            return false;
        }
        if (StringUtils.isEmpty(request.getHeader("token")) || "undefined".equals(request.getHeader("token"))
            || "null".equals(request.getHeader("token"))) {
            String token = UUID.randomUUID().toString();
            redisTemplate.opsForValue().set(token, accountUserDO, 60 * 10, TimeUnit.SECONDS);
            response.setHeader("token", token);
            response.setHeader("Access-Control-Expose-Headers", "token");
        }
        response.setHeader("errorInfo", "token success");
        return true;
    }

    @Override
    public List<String> userAdd(AccountUserDTO accountUserDTO) {
        AccountUserDO accountUserDO = new AccountUserDO();
        String id = UUID.randomUUID().toString();
        accountUserDO.setId(id);
        accountUserDO.setUserName(accountUserDTO.getUserName());
        accountUserDO.setAge(accountUserDTO.getAge());
        accountUserDO.setPassWord(accountUserDTO.getPassWord());
        accountUserDO.setCreateTime(accountUserDTO.getCreateTime());
        accountUserMapper.insert(accountUserDO);
        List<AccountRoleDO> accountRoleDOList = accountUserDTO.getAccountRoleDOList();
        accountRoleDOList.forEach((item) -> {
            AccountUserRoleDO accountUserRoleDO = new AccountUserRoleDO();
            String relationId = UUID.randomUUID().toString();
            accountUserRoleDO.setId(relationId);
            accountUserRoleDO.setUserId(id);
            accountUserRoleDO.setRoleId(item.getId());
            accountUserRoleMapper.insert(accountUserRoleDO);
        });

        return accountRoleDOList.stream().map(item -> {
            return item.getId();
        }).collect(Collectors.toList());
    }

    @Override
    public List<AccountUserDTO> queryUserAll() {
        List<AccountUserDTO> accountUserDTOList = accountUserRoleMapper.selectAccountUser();
        Map<String, List<AccountUserDTO>> accountUserDTOGroupMap =
            accountUserDTOList.stream().collect(Collectors.groupingBy(AccountUserDTO::getId, Collectors.toList()));
        List<AccountUserDTO> accountUserDTOS = new ArrayList<>();
        accountUserDTOGroupMap.forEach((item,accountUserGroupDTOS) -> {
            AccountUserDTO accountUserDTO = new AccountUserDTO();
            accountUserDTO.setId(accountUserGroupDTOS.get(0).getId());
            accountUserDTO.setAge(accountUserGroupDTOS.get(0).getAge());
            accountUserDTO.setUserName(accountUserGroupDTOS.get(0).getUserName());
            accountUserDTO.setPassWord(accountUserGroupDTOS.get(0).getPassWord());
            accountUserDTO.setCreateTime(accountUserGroupDTOS.get(0).getCreateTime());
            List<AccountRoleDO> accountRoleDOList=new ArrayList<>();
            accountUserGroupDTOS.forEach((element) ->{
                AccountRoleDO accountRoleDO=new AccountRoleDO();
                accountRoleDO.setId(element.getRoleId());
                accountRoleDO.setRoleName(element.getRoleName());
                accountRoleDOList.add(accountRoleDO);
            });
            accountUserDTO.setAccountRoleDOList(accountRoleDOList);
            String roleName = accountUserDTO.getAccountRoleDOList().stream().map((e) -> {
                return e.getRoleName();
            }).collect(Collectors.joining(","));
            accountUserDTO.setRoleName(roleName);
            accountUserDTOS.add(accountUserDTO);
        });
        return accountUserDTOS;
    }

    @Override
    public List<String> userUpdate(List<AccountUserDTO> accountRoleDTOList) {
        accountUserRoleMapper.delete(new AccountUserRoleDO());
        accountRoleDTOList.forEach(item ->{
            AccountUserDO accountUserDO=new AccountUserDO();
            accountUserDO.setId(item.getId());
            accountUserDO.setUserName(item.getUserName());
            accountUserDO.setAge(item.getAge());
            accountUserDO.setPassWord(item.getPassWord());
            accountUserDO.setCreateTime(item.getCreateTime());
            accountUserMapper.updateByPrimaryKey(accountUserDO);
            List<AccountRoleDO> accountRoleDOList = item.getAccountRoleDOList();
            accountRoleDOList.forEach(e ->{
                AccountUserRoleDO accountUserRoleDO=new AccountUserRoleDO();
                String id = UUID.randomUUID().toString();
                accountUserRoleDO.setId(id);
                accountUserRoleDO.setUserId(item.getId());
                accountUserRoleDO.setRoleId(e.getId());
                accountUserRoleMapper.insert(accountUserRoleDO);
            });
        });
        return accountRoleDTOList.stream().map(item ->{return item.getId();}).collect(Collectors.toList());
    }

    @Override
    public Integer userDelete(AccountRoleDO accountRoleDO) {
        return null;
    }
}
