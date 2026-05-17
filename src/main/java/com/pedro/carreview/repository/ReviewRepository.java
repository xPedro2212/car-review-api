package com.pedro.carreview.repository;

import com.pedro.carreview.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCarId(Long carId);

}
