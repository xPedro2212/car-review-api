package com.pedro.carreview.dto;

import lombok.Data;

@Data
public class ReviewRequest {
    private String title;
    private String body;
    private Integer rating;
    private Long carId;
}
