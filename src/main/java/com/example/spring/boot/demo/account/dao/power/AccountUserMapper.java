package com.example.spring.boot.demo.account.dao.power;

import com.example.spring.boot.demo.account.entity.power.AccountUserDO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

@Repository
public interface AccountUserMapper extends BaseMapper<AccountUserDO> {
    @InsertProvider(type= AddAccountUser.class,method ="addAccountUser")
    Integer addAccountUser(AccountUserDO accountUserDO);
        class AddAccountUser{
           public String addAccountUser(AccountUserDO accountUserDO){
                 SQL sql = new SQL() {
                     {
                        INSERT_INTO("account_user");
                        VALUES("id", "#{id}");
                        VALUES("user_name","#{userName}");
                        VALUES("role_id","#{roleId}");
                        VALUES("create_time","DATE_FORMAT(#{createTime},'%Y-%m-%d %H:%I:%s')");
                        VALUES("age","#{age}");
                        VALUES("power_id","#{powerId}");
                     }
                 };
                 return sql.toString();
             }
        }
}
