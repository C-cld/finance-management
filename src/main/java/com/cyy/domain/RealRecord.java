package com.cyy.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 各类实际金额更新记录
 */
@Data
public class RealRecord {
    private Integer id;
    private Integer type;
    private Float value;
    private String note;
    private LocalDateTime createTime;
}
