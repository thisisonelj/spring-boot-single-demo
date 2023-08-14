package com.example.spring.boot.demo.account.enumutil;

/**
 * @Author liujun
 * @Date 2023/8/13 15:28
 * @Description
 */
public enum PowerEnum {
    USERMANAGE("userManage"),
    SYSTEMSETTING("systemSetting"),
    CMMANAGE("cmManage"),
    VOUCHERMANAGE("voucherManage"),
    BOOKMANAGE("bookManage");

    private final String powerId;

    private PowerEnum(String powerId) {
        this.powerId = powerId;
    }

    @Override
    public String toString() {
      return  this.powerId;
    }
}
