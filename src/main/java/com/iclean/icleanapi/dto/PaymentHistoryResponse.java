package com.iclean.icleanapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentHistoryResponse {
    private String paymentId;
    private String paymentCode;
    private Double balance;
    private LocalDateTime paymentDate;
    private String userId;
    private String email;
    private String imgLink;
}
