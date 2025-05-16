package com.e_commerce.e_commerce_demo.Repository;

import com.e_commerce.e_commerce_demo.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
