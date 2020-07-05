package com.cyy.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Plan {
    private Integer id;
    private Float invest;
    private Float deposit;
    private Float eduFund;
    private Float cash;
    private LocalDate createDate;
}
