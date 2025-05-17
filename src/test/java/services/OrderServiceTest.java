package services;

import com.e_commerce.e_commerce_demo.Dtos.*;
import com.e_commerce.e_commerce_demo.Repository.OrderRepository;
import com.e_commerce.e_commerce_demo.Repository.ProductRepository;
import com.e_commerce.e_commerce_demo.Repository.UserRepository;
import com.e_commerce.e_commerce_demo.Services.OrderService;
import com.e_commerce.e_commerce_demo.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private User user;
    private Products product;
    private Order order;

    @BeforeEach
    public void setup() {
        Category category = new Category("sports");
        product = new Products("Basketball", 200L, category);

        Address address = new Address(1L, "4th main", "ITPL", "KA", "IND");
        user = new User("Don", List.of(address), "123456781");

        OrderItem orderItem = new OrderItem(1L, product, 200L);
        order = new Order(1L, LocalDateTime.now(), user, List.of(orderItem), 200L);
    }

    @Test
    public void createOrder_successfullyCreatesOrder() {
        // Given
        OrderItemDto orderItemDto = new OrderItemDto(1L, "Basketball", 1L, 200L);
        List<OrderItemDto> items = new ArrayList<>();
        items.add(orderItemDto);

        OrderRequest orderRequest = new OrderRequest(1L, items);

        // When
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        // Then
        OrderDto savedOrder = orderService.createOrder(orderRequest);

        // Assert
        assertNotNull(savedOrder);
        assertEquals(1L, savedOrder.getId());
        assertEquals(200L, savedOrder.getTotalAmount());
        assertEquals(1, savedOrder.getItems().size());

        verify(orderRepository).save(any(Order.class));
    }
}
