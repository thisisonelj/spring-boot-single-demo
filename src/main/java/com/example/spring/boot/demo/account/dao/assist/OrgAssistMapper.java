package com.example.spring.boot.demo.account.dao.assist;

import com.example.spring.boot.demo.account.entity.assist.OrgAssistDO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * @Author liujun
 * @Date 2023/8/15 15:14
 * @Description
 */
@Repository
public interface OrgAssistMapper extends BaseMapper<OrgAssistDO> {
}
