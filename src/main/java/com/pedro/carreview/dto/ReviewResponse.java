package com.pedro.carreview.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewResponse {
  private Long id;
  private String title;
  private String body;
  private Integer rating;
  private LocalDateTime createdAt;
  private String userName;
  private String carBrand;
  private String carModel;
  private Integer carYear;
}
