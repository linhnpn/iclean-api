package com.iclean.icleanapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct {
    private Integer orderId;
    private Integer userId;
    private LocalDateTime orderDate;
    private Double totalAmount;
    private Integer orderStatus;
    private String address;
}
