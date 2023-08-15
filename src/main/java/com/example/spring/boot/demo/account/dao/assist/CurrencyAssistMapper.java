package com.example.spring.boot.demo.account.dao.assist;

import com.example.spring.boot.demo.account.entity.assist.CurrencyAssistDO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * @Author liujun
 * @Date 2023/8/15 15:19
 * @Description
 */
@Repository
public interface CurrencyAssistMapper extends BaseMapper<CurrencyAssistDO> {
}
