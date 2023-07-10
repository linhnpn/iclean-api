package com.iclean.icleanapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Integer orderItemId;
    private Integer orderId;
    private Integer userId;
    private Integer quantity;
    private Double unitPrice;
}
