package com.iclean.icleanapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private int addressId;
    private double longitude;
    private double latitude;
    private String description;
    private LocalDateTime timestamp;
    private int isDefault;
    private int userId;
}
