package com.e_commerce.e_commerce_demo.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ProductDto {

    private Long id;

    private String name;

    private Long price;

    private CategoryDto category;
}
