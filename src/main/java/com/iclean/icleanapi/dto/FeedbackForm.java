package com.iclean.icleanapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackForm {
    private Integer orderId;

    private Double rate;

    private String feedback;

    private Date feedbackTime;

    private String orderscol;

    private Integer renterId;

    private Integer employeeId;

    private Integer jobId;

    private Integer statusId;
}
