package com.iclean.icleanapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewOrderProduct {
    private Integer productId;
    private Integer quantity;
    private Integer orderId;
    private Double unitPrice;
}
