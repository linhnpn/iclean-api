package com.iclean.icleanapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeJobResponse {
    private Integer jobId;
    private String jobName;
    private String thumnailImg;
    private Integer employeeId;
    private String employeeName;
    private String srcPicture;
    private String description;
    private Double price;
    private Double averageRate;
    private Date timestamp;
    private String location;
}
