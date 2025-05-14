package com.e_commerce.e_commerce_demo.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String name;

    private List<AddressDto> address;

    private String phone;
}
