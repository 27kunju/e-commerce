package services;

import com.e_commerce.e_commerce_demo.Dtos.CategoryDto;
import com.e_commerce.e_commerce_demo.Dtos.ProductDto;
import com.e_commerce.e_commerce_demo.Exception.ResourceNotFoundException;
import com.e_commerce.e_commerce_demo.Repository.CategoryRepository;
import com.e_commerce.e_commerce_demo.Repository.ProductRepository;
import com.e_commerce.e_commerce_demo.Services.ProductService;
import com.e_commerce.e_commerce_demo.model.Category;
import com.e_commerce.e_commerce_demo.model.Products;

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
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    private Category category;
    private Products product;
    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        category = new Category("sports");
        CategoryDto categoryDto = new CategoryDto(1L, "sports");

        product = new Products("Basketball", 200L, category);
        productDto = new ProductDto("Basketball", 200L, categoryDto);
    }

    @Test
    void createProduct_shouldReturnProductWithCorrectCategory() {
        // Given
        when(categoryRepository.findByName("sports")).thenReturn(Optional.of(category));
        when(productRepository.save(any(Products.class))).thenReturn(product);

        // When
        ProductDto result = productService.createProduct(productDto);

        // Then
        assertEquals("sports", result.getCategory().getName());
    }

    @Test
    void getProduct_shouldReturnCorrectProduct() {
        // Given
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // When
        ProductDto result = productService.getProduct(1L);

        // Then
        assertEquals(200L, result.getPrice());
    }

    @Test
    void getProduct_shouldThrowExceptionWhenNotFound() {
        // Given
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Then
        assertThrows(ResourceNotFoundException.class, () -> productService.getProduct(1L));
    }

    @Test
    void searchProducts_shouldReturnMatchingList() {
        // Given
        when(productRepository.searchProducts("BasketBall", "")).thenReturn(List.of(product));

        // When
        List<ProductDto> result = productService.searchProducts("BasketBall", "");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Basketball", result.get(0).getName());
    }

    @Test
    void deleteProduct_shouldDeleteExistingProduct() {
        // Given
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // When
        productService.deleteProduct(1L);

        // Then
        verify(productRepository).delete(product);
    }
}
