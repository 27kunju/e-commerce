package com.e_commerce.e_commerce_demo.Repository;

import com.e_commerce.e_commerce_demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
