package com.e_commerce.e_commerce_demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String pin_code;

    private String state;

    private String country;

    public Address( String street, String pin_code, String state, String country) {
        this.street = street;
        this.pin_code = pin_code;
        this.state = state;
        this.country = country;

    }
}
