package com.iclean.icleanapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentHistory {
    private int paymentId;
    private String paymentCode;
    private Double balance;
    private LocalDateTime paymentDate;
    private int userId;
}
