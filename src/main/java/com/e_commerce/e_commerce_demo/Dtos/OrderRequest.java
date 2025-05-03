package com.e_commerce.e_commerce_demo.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private Long userId;

    private List<OrderItemDto> items;
}
