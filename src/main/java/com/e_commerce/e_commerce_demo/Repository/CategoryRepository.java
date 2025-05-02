package com.e_commerce.e_commerce_demo.Repository;

import com.e_commerce.e_commerce_demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
