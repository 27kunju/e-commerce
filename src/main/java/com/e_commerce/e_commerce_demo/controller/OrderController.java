package com.e_commerce.e_commerce_demo.controller;


import com.e_commerce.e_commerce_demo.Dtos.OrderDto;
import com.e_commerce.e_commerce_demo.Dtos.OrderRequest;
import com.e_commerce.e_commerce_demo.Repository.OrderRepository;
import com.e_commerce.e_commerce_demo.Services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequest request){
        OrderDto order = orderService.createOrder(request);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrders(@PathVariable("id") Long id){
        OrderDto order = orderService.getOrders(id);
        return new ResponseEntity<>(order, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
     public ResponseEntity<String> deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return  ResponseEntity.ok("order delered successfully");

     }


}
