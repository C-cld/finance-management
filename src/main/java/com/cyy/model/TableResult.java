package com.cyy.model;

import lombok.Data;

import java.util.List;

@Data
public class TableResult {
    private int code;
    private String msg;
    private int count;
    private List data;
}
