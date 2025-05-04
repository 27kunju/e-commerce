package com.e_commerce.e_commerce_demo.Services;


import com.e_commerce.e_commerce_demo.Dtos.OrderDto;
import com.e_commerce.e_commerce_demo.Dtos.OrderItemDto;
import com.e_commerce.e_commerce_demo.Dtos.OrderRequest;
import com.e_commerce.e_commerce_demo.Exception.ResourceNotFoundException;
import com.e_commerce.e_commerce_demo.Repository.OrderRepository;
import com.e_commerce.e_commerce_demo.Repository.ProductRepository;
import com.e_commerce.e_commerce_demo.Repository.UserRepository;
import com.e_commerce.e_commerce_demo.model.Order;
import com.e_commerce.e_commerce_demo.model.OrderItem;
import com.e_commerce.e_commerce_demo.model.Products;
import com.e_commerce.e_commerce_demo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    private UserRepository userRepository;

    private ProductRepository productRepository;

    public OrderDto createOrder(OrderRequest request) {
        Long userId = request.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found", HttpStatus.NOT_FOUND));

        Order order = new Order();
        order.setUser(user);
        order.setOrdered_date(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        double totalAmount = 0;

        for (var itemRequest : request.getItems()) {
            Products product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product with id " + itemRequest.getProductId() + " not found", HttpStatus.NOT_FOUND));

            OrderItem orderItem = new OrderItem();
            orderItem.setProducts(product);
            orderItem.setOrder(order);
            orderItem.setQuantity(itemRequest.getQuantity());

            totalAmount += product.getPrice() * itemRequest.getQuantity();
            orderItems.add(orderItem);

            // Map to OrderItemDto
            OrderItemDto itemDto = new OrderItemDto();
            itemDto.setProductId(product.getId());
            itemDto.setProductName(product.getName());
            itemDto.setQuantity(itemRequest.getQuantity());
            itemDto.setPrice(product.getPrice());

            orderItemDtos.add(itemDto);
        }

        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);
        Order savedOrder = orderRepository.save(order);

        return new OrderDto(
                savedOrder.getId(),
                savedOrder.getOrdered_date(),
                savedOrder.getUser(),
                orderItemDtos,
                savedOrder.getTotalAmount()
        );
    }

    public OrderDto getOrders(Long id){
       Order  order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No order found", HttpStatus.NOT_FOUND));

        // Convert each OrderItem to OrderItemDto
        List<OrderItemDto> orderItemDtos = order.getItems().stream().map(item -> {
            return new OrderItemDto(
                    item.getProducts().getId(),
                    item.getProducts().getName(),
                    item.getProducts().getPrice(),
                    item.getQuantity()
            );
        }).collect(Collectors.toList());

        // Construct and return OrderDto
        return new OrderDto(
                order.getId(),
                order.getOrdered_date(),
                order.getUser(),
                orderItemDtos,
                order.getTotalAmount()
        );
    }

    public void deleteOrder(Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("order not found", HttpStatus.NOT_FOUND));
        orderRepository.delete(order);
    }

    public List<OrderDto> getOrdersByUserId(Long userId) {
        // Fetch orders by userId
        List<Order> orders = orderRepository.findByUserId(userId);

        // Map Order entities to OrderDto
        return orders.stream()
                .map(order -> new OrderDto(
                        order.getId(),
                        order.getItems().stream()
                                .map(item -> new OrderItemDto(item.getProducts().getId(), item.getProducts().getName(),item.getQuantity(), item.getProducts().getPrice()))
                                .collect(Collectors.toList()),
                                        order.getTotalAmount(),  order.getOrdered_date())
                )
                .collect(Collectors.toList());
    }

}
