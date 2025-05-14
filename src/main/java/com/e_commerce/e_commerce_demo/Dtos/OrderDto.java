package com.e_commerce.e_commerce_demo.Dtos;

import com.e_commerce.e_commerce_demo.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {

    private Long id;

    private LocalDateTime orderedDate;

    private List<OrderItemDto> items;

    private Double totalAmount;

    public OrderDto(Long id, List<OrderItemDto> items,  Double totalAmount, LocalDateTime orderedDate) {
        this.id = id;
        this.items = items;
        this.totalAmount = totalAmount;
        this.orderedDate = orderedDate;

    }
}
