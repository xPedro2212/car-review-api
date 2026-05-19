package com.pedro.carreview.service;

import com.pedro.carreview.dto.ReviewRequest;
import com.pedro.carreview.dto.ReviewResponse;
import com.pedro.carreview.model.Car;
import com.pedro.carreview.model.Review;
import com.pedro.carreview.model.User;
import com.pedro.carreview.repository.CarRepository;
import com.pedro.carreview.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.pedro.carreview.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
  private final UserRepository userRepository;
  private final CarRepository carRepository;
  private final ReviewRepository reviewRepository;

  public ReviewResponse save(ReviewRequest request, String email) {
    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    Car car =
        carRepository
            .findById(request.getCarId())
            .orElseThrow(() -> new RuntimeException("Carro não encontrado"));

    Review review = new Review();
    review.setUser(user);
    review.setCar(car);
    review.setTitle(request.getTitle());
    review.setBody(request.getBody());
    review.setRating(request.getRating());
    review.setCreatedAt(LocalDateTime.now());

    Review saved = reviewRepository.save(review);

    ReviewResponse response = new ReviewResponse();
    response.setId(saved.getId());
    response.setUserName(saved.getUser().getName());
    response.setTitle(saved.getTitle());
    response.setBody(saved.getBody());
    response.setRating(saved.getRating());
    response.setCarModel(saved.getCar().getModel());
    response.setCarBrand(saved.getCar().getBrand());
    response.setCarYear(saved.getCar().getYear());
    response.setCreatedAt(saved.getCreatedAt());

    return response;
  }

  public List<ReviewResponse> findByCarId(Long carId) {

    return reviewRepository.findByCarId(carId).stream()
        .map(
            review -> {
              ReviewResponse response = new ReviewResponse();
              response.setId(review.getId());
              response.setUserName(review.getUser().getName());
              response.setTitle(review.getTitle());
              response.setBody(review.getBody());
              response.setRating(review.getRating());
              response.setCarModel(review.getCar().getModel());
              response.setCarBrand(review.getCar().getBrand());
              response.setCarYear(review.getCar().getYear());
              response.setCreatedAt(review.getCreatedAt());
              return response;
            })
        .toList();
  }
}
