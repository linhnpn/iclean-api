package com.iclean.icleanapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voucher {
    private Integer voucherId;
    private String voucherName;
    private String voucherCode;
    private Double maxPriceDiscount;
    private LocalDateTime timestamp;
    private Integer status;
}
