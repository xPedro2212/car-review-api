package com.pedro.carreview.service;

import com.pedro.carreview.dto.ReviewRequest;
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

    public Review save(ReviewRequest request, String email){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Car car = carRepository.findById(request.getCarId()).orElseThrow(()-> new RuntimeException("Carro não encontrado"));

        Review review = new Review();
        review.setUser(user);
        review.setCar(car);
        review.setTitle(request.getTitle());
        review.setBody(request.getBody());
        review.setRating(request.getRating());
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }
    public List<Review> findByCarId(Long carId){
        return  reviewRepository.findByCarId(carId);
    }
}
