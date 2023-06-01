package com.iclean.icleanapi.dto;

import lombok.Data;

import java.util.List;
@Data
public class SubscriptionRequestDto {
    private List<String> tokens;
    private String topicName;
}
