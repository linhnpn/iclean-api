package com.iclean.icleanapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    private Integer jobId;

    private String name;

    private Double minPrice;

    private Double maxPrice;

    private String thumnailImg;

    private String measureUnit;

    private Integer timePenalty;

    private Integer pointPenalty;

    private Double moneyPenalty;
}
