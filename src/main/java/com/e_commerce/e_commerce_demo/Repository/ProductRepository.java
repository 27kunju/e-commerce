package com.e_commerce.e_commerce_demo.Repository;

import com.e_commerce.e_commerce_demo.model.Category;
import com.e_commerce.e_commerce_demo.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Products, Long> {


    //p.category is the way to access the related Category entity from the Products entity.
    @Query("SELECT p FROM Products p JOIN p.category c WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(c.name) LIKE LOWER(CONCAT('%', :categoryName, '%'))")
    List<Products> searchProducts(@Param("name") String name, @Param("categoryName") String categoryName);

}
