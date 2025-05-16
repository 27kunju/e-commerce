package com.e_commerce.e_commerce_demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Products( String name, long price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    private String name;

    private long price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
