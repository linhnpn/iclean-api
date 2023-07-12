package com.iclean.icleanapi.dto;

import com.iclean.icleanapi.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductResponse {
    private Integer orderId;
    private Integer userId;
    private LocalDateTime orderDate;
    private Double totalAmount;
    private Integer orderStatus;
    private String address;
    private List<Product> products;
}
