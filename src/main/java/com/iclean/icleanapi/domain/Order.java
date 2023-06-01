package com.iclean.icleanapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer orderId;

    private String location;

    private Double longitude;

    private Double latitude;

    private LocalDateTime workDate;

    private Integer workTime;

    private LocalDateTime workStart;

    private LocalDateTime workEnd;

    private LocalDateTime orderDate;

    private Double rate;

    private String feedback;

    private LocalDateTime feedbackTime;

    private String orderscol;

    private Integer renterId;

    private Integer employeeId;

    private Integer jobId;

    private Integer statusId;

    private Integer paymentId;

}
