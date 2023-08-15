package com.example.spring.boot.demo.account.entity.assist;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author liujun
 * @Date 2023/8/15 15:08
 * @Description
 */
@Data
@Entity
@Table(name = "bank_assist")
public class BankAssistDO {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "currency_id")
    private String currencyId;
}
