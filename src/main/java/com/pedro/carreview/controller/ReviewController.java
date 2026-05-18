package com.pedro.carreview.controller;

import com.pedro.carreview.dto.ReviewRequest;
import com.pedro.carreview.model.Review;
import com.pedro.carreview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
  private final ReviewService reviewService;

  @PostMapping
  public ResponseEntity<Review> save(@RequestBody ReviewRequest request) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    return ResponseEntity.ok(reviewService.save(request, email));
  }

  @GetMapping("/car/{carId}")
  public ResponseEntity<List<Review>> findByCarId(@PathVariable Long carId) {
    return ResponseEntity.ok(reviewService.findByCarId(carId));
  }
}
