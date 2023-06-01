package com.iclean.icleanapi.dto;

import com.iclean.icleanapi.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewOrderForm {
    private LocalDateTime workDate;
    private LocalDateTime orderDate;
    private Integer workTime;
    private Integer renterId;
    private Integer employeeId;
    private Integer jobId;
    private String voucherCode;

}
