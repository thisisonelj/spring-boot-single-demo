package com.example.spring.boot.demo.account.service.assist.Impl;

import com.example.spring.boot.demo.account.dao.assist.CurrencyAssistMapper;
import com.example.spring.boot.demo.account.entity.assist.CurrencyAssistDO;
import com.example.spring.boot.demo.account.service.assist.CurrencyAssistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author liujun
 * @Date 2023/8/15 15:24
 * @Description
 */
@Service
public class CurrencyAssistServiceImpl implements CurrencyAssistService {
    @Autowired
    private CurrencyAssistMapper currencyAssistMapper;

    @Override
    public List<CurrencyAssistDO> query() {
        return currencyAssistMapper.selectAll();
    }

    @Override
    public String add(CurrencyAssistDO currencyAssistDO) {
        currencyAssistMapper.insert(currencyAssistDO);
        return currencyAssistDO.getId();
    }

    @Override
    public List<String> update(List<CurrencyAssistDO> currencyAssistDOList) {
        currencyAssistDOList.forEach(item -> {
            currencyAssistMapper.updateByPrimaryKey(item);
        });
        return currencyAssistDOList.stream().map(e -> {
            return e.getId();
        }).collect(Collectors.toList());
    }

    @Override
    public String delete(CurrencyAssistDO currencyAssistDO) {
        currencyAssistMapper.delete(currencyAssistDO);
        return currencyAssistDO.getId();
    }
}
