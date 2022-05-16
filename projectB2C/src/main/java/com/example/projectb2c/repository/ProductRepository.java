package com.example.projectb2c.repository;


import com.example.projectb2c.entity.Chart;
import com.example.projectb2c.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT u FROM Product u WHERE u.productName LIKE %:name%"  )
    Page<Product> findProductByProductName(Pageable pageable, @Param("name") String name);
    Page<Product> findProductByCategoryId(Pageable pageable, Long id);
    @Query("SELECT new com.example.projectb2c.entity.Chart(c.nameCategory , count(c)) from Product p JOIN Category c ON p.category.id= c.id GROUP BY p.category.id ")
    List<Chart> sumOfCategory();



}
