package com.example.spring.boot.demo.account.entity.assist;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author liujun
 * @Date 2023/8/15 15:11
 * @Description
 */
@Data
@Entity
@Table(name = "currency_assist")
public class CurrencyAssistDO {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "currency_name")
    private String currencyName;
    @Column(name = "currency_type")
    private String currencyType;

}
