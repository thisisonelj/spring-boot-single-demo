package com.example.spring.boot.demo.account.dao.power;

import com.example.spring.boot.demo.account.entity.power.AccountPowerDO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * @Author liujun
 * @Date 2023/8/8 21:54
 * @Description
 */

@Repository
public interface AccountPowerMapper extends BaseMapper<AccountPowerDO> {
}
