package com.example.spring.boot.demo.account.dao.power;

import com.example.spring.boot.demo.account.dto.AccountUserDTO;
import com.example.spring.boot.demo.account.entity.power.AccountUserRoleDO;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @Author liujun
 * @Date 2023/8/12 18:08
 * @Description 查询所有用户信息
 */
@Repository
public interface AccountUserRoleMapper extends BaseMapper<AccountUserRoleDO> {
    @SelectProvider(type = AccountUserRoleMapper.AccountUser.class, method = "selectAccountUser")
    List<AccountUserDTO> selectAccountUser();
    @UpdateProvider(type = AccountUserRoleMapper.AccountUserUpdate.class, method = "updateAccountUser")
    Integer updateAccountUser(AccountUserRoleDO accountUserRoleDO);
    class AccountUser {
        public String selectAccountUser() {
            SQL sql = new SQL() {
                {
                    SELECT("u.id as id,u.user_name as userName,u.age,u.pass_word as passWord,u.create_time as createTime");
                    SELECT("r.id as roleId,r.role_name as roleName");
                    FROM("account_user as u");
                    LEFT_OUTER_JOIN("account_user_role as ur on u.id=ur.user_id");
                    LEFT_OUTER_JOIN("account_role as r on ur.role_id=r.id");
                    ORDER_BY("u.create_time");
                }
            };
            return sql.toString();
        }
    }

    class AccountUserUpdate {
        public String updateAccountUser() {
            SQL sql = new SQL() {
                {
                   UPDATE("account_user_role");
                   SET("role_id=#{roleId}");
                   WHERE("user_id=#{userId}");
                }
            };
            return sql.toString();
        }
    }
}
