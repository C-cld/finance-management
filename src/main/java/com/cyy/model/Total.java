package com.cyy.model;

import lombok.Data;

/**
 * 各类总额
 */
@Data
public class Total {
    private float invest;
    private float deposit;
    private float eduFund;
    private float sum;

    public void setSum(int flag) {
        this.sum = invest + deposit + eduFund;
    }
}
