package com.e_commerce.e_commerce_demo.controller;

import com.e_commerce.e_commerce_demo.Dtos.AddressDto;
import com.e_commerce.e_commerce_demo.Dtos.UserDto;
import com.e_commerce.e_commerce_demo.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserDto getSampleUserDto() {
        AddressDto address = new AddressDto(1L, "4th main", "ITPL", "KA", "IND");
        return new UserDto(1L, "Don", List.of(address), "123456781");
    }

    @Test
    public void createUser_shouldReturnCreatedUser() throws Exception {
        UserDto userDto = getSampleUserDto();

        Mockito.when(userService.createUser(Mockito.any(UserDto.class))).thenReturn(userDto);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(userDto)));
        Mockito.verify(userService, Mockito.times(1)).createUser(Mockito.any(UserDto.class));

    }

    @Test
    public void getUserById_shouldReturnUser() throws Exception {
        UserDto userDto = getSampleUserDto();

        Mockito.when(userService.getUserDetail(Mockito.anyLong())).thenReturn(userDto);

        mockMvc.perform(get("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userDto)));
        Mockito.verify(userService, Mockito.times(1)).getUserDetail(1L);

    }
}
