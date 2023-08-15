package com.example.spring.boot.demo.account.service.assist;

import com.example.spring.boot.demo.account.entity.assist.CurrencyAssistDO;

import java.util.List;

/**
 * @Author liujun
 * @Date 2023/8/15 15:22
 * @Description
 */
public interface CurrencyAssistService {
    List<CurrencyAssistDO> query();
    String add(CurrencyAssistDO currencyAssistDO);
    List<String> update(List<CurrencyAssistDO> currencyAssistDOList);
    String delete(CurrencyAssistDO currencyAssistDO);
}
