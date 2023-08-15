package com.example.spring.boot.demo.account.controller.assist;

import com.example.spring.boot.demo.account.entity.assist.CurrencyAssistDO;
import com.example.spring.boot.demo.account.service.assist.CurrencyAssistService;
import com.example.spring.boot.demo.account.utils.AcctResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author liujun
 * @Date 2023/8/15 15:29
 * @Description
 */
@RestController
@RequestMapping("/rest/account/currency")
public class CurrencyAssistController {
    @Autowired
    private CurrencyAssistService currencyAssistService;
    @PostMapping("/query")
    public AcctResult<Object> queryCurrencyAssists(){
     return AcctResult.resultSuccessful(currencyAssistService.query());
    }
    @PostMapping("/update")
    public AcctResult<Object> updateCurrencyAssists(@RequestBody List<CurrencyAssistDO> currencyAssistDOList){
        return AcctResult.resultSuccessful(currencyAssistService.update(currencyAssistDOList));
    }
    @PostMapping("/insert")
    public AcctResult<Object> addCurrencyAssists(@RequestBody CurrencyAssistDO currencyAssistDO){
        return AcctResult.resultSuccessful(currencyAssistService.add(currencyAssistDO));
    }
    @PostMapping("/delete")
    public AcctResult<Object> deleteCurrencyAssists(@RequestBody CurrencyAssistDO currencyAssistDO){
        return AcctResult.resultSuccessful(currencyAssistService.delete(currencyAssistDO));
    }
}
