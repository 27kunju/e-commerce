package com.e_commerce.e_commerce_demo.Dtos;

import com.e_commerce.e_commerce_demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderDto {

    private Long id;

    private LocalDateTime orderedDate;

    private User user;

    private List<OrderItemDto> items;

    private Double totalAmount;

}
