package com.e_commerce.e_commerce_demo.controller;

import com.e_commerce.e_commerce_demo.Dtos.OrderDto;
import com.e_commerce.e_commerce_demo.Dtos.OrderItemDto;
import com.e_commerce.e_commerce_demo.Dtos.OrderRequest;
import com.e_commerce.e_commerce_demo.Services.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    // Sample OrderItemDto
    private OrderItemDto getSampleOrderItem() {
        return new OrderItemDto(1L, "Vallayball", 1L, 100.0);  // You must have this constructor
    }

    // Sample OrderDto
    private OrderDto getSampleOrderDto() {
        List<OrderItemDto> items = Collections.singletonList(getSampleOrderItem());
        return new OrderDto(1L, items, 200.0, LocalDateTime.of(2024, 1, 1, 12, 0));
    }

    // Sample OrderRequest
    private OrderRequest getSampleOrderRequest() {
        OrderRequest request = new OrderRequest();
        request.setUserId(1L);
        request.setItems(Collections.singletonList(getSampleOrderItem()));
        return request;
    }

    @Test
    public void testCreateOrder() throws Exception {
        OrderRequest request = getSampleOrderRequest();
        OrderDto response = getSampleOrderDto();

        Mockito.when(orderService.createOrder(Mockito.any(OrderRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void testGetOrderById() throws Exception {
        OrderDto response = getSampleOrderDto();

        Mockito.when(orderService.getOrders(1L)).thenReturn(response);

        mockMvc.perform(get("/api/v1/order/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void testDeleteOrder() throws Exception {
        Mockito.doNothing().when(orderService).deleteOrder(1L);

        mockMvc.perform(delete("/api/v1/order/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("order deleted successfully"));
    }

    @Test
    public void testGetOrdersByUserId() throws Exception {
        List<OrderDto> response = Collections.singletonList(getSampleOrderDto());

        Mockito.when(orderService.getOrdersByUserId(1L)).thenReturn(response);

        mockMvc.perform(get("/api/v1/order/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
}
