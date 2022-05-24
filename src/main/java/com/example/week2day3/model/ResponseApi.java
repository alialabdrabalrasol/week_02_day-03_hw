package com.example.week2day3.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class ResponseApi {
    private String message;
    private Integer status;
}
