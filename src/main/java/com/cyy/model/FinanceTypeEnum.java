package com.cyy.model;

import lombok.Getter;

@Getter
public enum FinanceTypeEnum {
    INVEST(1, "投资理财"),
    DEPOSIT(2, "固定存款"),
    EDUFUND(3, "教育基金"),
    CASH(4, "日常开销");

    private int code;
    private String value;
    FinanceTypeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
