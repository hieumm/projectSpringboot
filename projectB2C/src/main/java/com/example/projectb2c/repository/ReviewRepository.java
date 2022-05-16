package com.example.projectb2c.repository;

import com.example.projectb2c.entity.Chart;
import com.example.projectb2c.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByProduct(Long id);
}
