package com.iclean.icleanapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobEmployee {
    private Integer jobId;

    private Integer employeeId;

    private String description;

    private Double price;

    private Date timestamp;

}
