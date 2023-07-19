package com.iclean.icleanapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddNewPaymentRequest {
    private int userId;
    private double balance;
    private String code;
}
