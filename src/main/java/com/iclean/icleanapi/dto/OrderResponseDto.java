package com.iclean.icleanapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private Integer id, userId, empId, jobId, workTime, price;
    private String username, empName, status, location, jobName, description, imgRenter, imgEmployee;
    private LocalDateTime timestamp;
    private Double latitude, longitude;
}
