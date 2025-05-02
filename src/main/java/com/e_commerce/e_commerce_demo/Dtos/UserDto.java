package com.e_commerce.e_commerce_demo.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String name;

    private AddressDto address;

    private String phone;
}
