package com.iclean.icleanapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentHistoryResponse {
    private int paymentId;
    private String paymentCode;
    private Double balance;
    private LocalDateTime paymentDate;
    private int userId;
    private String fullname;
    private String email;
    private String imgLink;
}
