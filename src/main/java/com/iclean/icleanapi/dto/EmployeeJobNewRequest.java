package com.iclean.icleanapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeJobNewRequest {
    private Integer jobId;
    private Integer employeeId;
    private String description;
    private Double price;
    private LocalDateTime timestamp;
}
