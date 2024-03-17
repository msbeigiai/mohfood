package com.msbeigi.repository;

import com.msbeigi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByRestaurantId(Long id);
}
