package com.iclean.icleanapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer orderId;

    private String location;

    private Double longitude;

    private Double latitude;

    private Date workDate;

    private Date workStart;

    private Date workEnd;

    private Date orderDate;

    private Double rate;

    private String feedback;

    private Date feedbackTime;

    private String orderscol;

    private Integer renterId;

    private Integer employeeId;

    private Integer jobId;

    private Integer statusId;

    private Integer paymentId;

}
