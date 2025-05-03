package com.e_commerce.e_commerce_demo.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long productId;

    private String productName;

    private Long quantity;

    private double price;
}
