package com.iclean.icleanapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer productId;
    private String title;
    private String description;
    private Double price;
    private String category;
    private String imgLink;
    private String link;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
