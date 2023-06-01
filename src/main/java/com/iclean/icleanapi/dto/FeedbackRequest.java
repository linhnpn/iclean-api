package com.iclean.icleanapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackRequest {
    private Integer orderId;
    private Double rate;
    private String feedback;
    private LocalDateTime feedbackTime;
}
