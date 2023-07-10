package com.iclean.icleanapi.dto;

import lombok.Data;

@Data
public class MoneyRequest {
    private Integer userId;
    private Double newMoney;
}
