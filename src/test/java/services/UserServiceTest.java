package services;

import com.e_commerce.e_commerce_demo.Dtos.AddressDto;
import com.e_commerce.e_commerce_demo.Dtos.UserDto;
import com.e_commerce.e_commerce_demo.Exception.ResourceNotFoundException;
import com.e_commerce.e_commerce_demo.Repository.AddressRepository;
import com.e_commerce.e_commerce_demo.Repository.UserRepository;
import com.e_commerce.e_commerce_demo.Services.UserService;
import com.e_commerce.e_commerce_demo.model.Address;
import com.e_commerce.e_commerce_demo.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private UserService userService;

    private Address address;
    private AddressDto addressDto;
    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        address = new Address(1L, "4th main", "ITPL", "KA", "IND");
        addressDto = new AddressDto(1L, "4th main", "ITPL", "KA", "IND");

        user = new User("Don", List.of(address), "123456781");
        userDto = new UserDto(1L, "Don", List.of(addressDto), "1234568999");
    }

    @Test
    void createUser_shouldReturnCreatedUserDto() {
        // Arrange
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        UserDto result = userService.createUser(userDto);

        // Assert
        assertNotNull(result);
        assertEquals("Don", result.getName());
        assertFalse(result.getAddress().isEmpty());
    }

    @Test
    void getUserById_shouldReturnUserDto_whenUserExists() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        UserDto result = userService.getUserDetail(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Don", result.getName());
    }

    @Test
    void getUserById_shouldThrowException_whenUserNotFound() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Assert
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserDetail(1L));
    }

    @Test
    void deleteUser_shouldDeleteUser_whenUserExists() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        userService.deleteUser(1L);

        // Assert
        verify(userRepository).delete(user);
    }
}
