package com.iclean.icleanapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticResponse {
    private LocalDateTime paymentDay;
    private Double totalBalance;
}
