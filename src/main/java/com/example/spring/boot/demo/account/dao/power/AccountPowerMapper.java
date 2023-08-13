package com.example.spring.boot.demo.account.dao.power;

import com.example.spring.boot.demo.account.entity.power.AccountPowerDO;
import com.example.spring.boot.demo.account.entity.power.AccountUserDO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * @Author liujun
 * @Date 2023/8/8 21:54
 * @Description
 */

@Repository
public interface AccountPowerMapper extends BaseMapper<AccountPowerDO> {
    @InsertProvider(type= AccountPowerMapper.AddAccountPower.class,method ="addAccountPower")
    Integer addAccountPower(AccountPowerDO accountPowerDO);
    class AddAccountPower{
        public String addAccountPower(AccountPowerDO accountPowerDO){
            SQL sql = new SQL() {
                {
                    INSERT_INTO("account_power");
                    VALUES("id", "#{id}");
                    VALUES("user_role_id","#{userRoleId}");
                    VALUES("user_role_value","#{userRoleValue}");
                    VALUES("create_time","DATE_FORMAT(#{createTime},'%Y-%m-%d %H:%I:%s')");
                    VALUES("power_id","#{powerId}");
                    VALUES("power_value","#{powerValue}");
                }
            };
            return sql.toString();
        }
    }

}
