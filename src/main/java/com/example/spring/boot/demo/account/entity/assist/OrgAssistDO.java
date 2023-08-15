package com.example.spring.boot.demo.account.entity.assist;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author liujun
 * @Date 2023/8/15 15:03
 * @Description
 */
@Data
@Entity
@Table(name = "org_assist")
public class OrgAssistDO {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "bank_account_id")
    private String bankAccountId;

    @Column(name = "leaf_status")
    private Boolean leafStatus;
}
