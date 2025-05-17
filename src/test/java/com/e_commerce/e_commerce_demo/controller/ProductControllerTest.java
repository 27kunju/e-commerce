package com.e_commerce.e_commerce_demo.controller;

import com.e_commerce.e_commerce_demo.Dtos.CategoryDto;
import com.e_commerce.e_commerce_demo.Dtos.ProductDto;
import com.e_commerce.e_commerce_demo.Services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProductService productService;

    private ProductDto getSampleProduct() {
        CategoryDto categoryDto = new CategoryDto(1L, "sports");
        return new ProductDto("VallayBall", 200L, categoryDto);
    }

    @Test
    public void createProduct() throws Exception {
        ProductDto productDto = getSampleProduct();

        Mockito.when(productService.createProduct(Mockito.any(ProductDto.class))).thenReturn(productDto);

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(productDto)));
    }
}
