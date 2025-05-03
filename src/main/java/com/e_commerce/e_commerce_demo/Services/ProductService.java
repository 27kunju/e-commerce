package com.e_commerce.e_commerce_demo.Services;

import com.e_commerce.e_commerce_demo.Dtos.CategoryDto;
import com.e_commerce.e_commerce_demo.Dtos.ProductDto;
import com.e_commerce.e_commerce_demo.Exception.ResourceNotFoundException;
import com.e_commerce.e_commerce_demo.Repository.CategoryRepository;
import com.e_commerce.e_commerce_demo.Repository.ProductRepository;
import com.e_commerce.e_commerce_demo.model.Category;
import com.e_commerce.e_commerce_demo.model.Products;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    public ProductDto createProduct(ProductDto productDto) {
        String categoryName = productDto.getCategory().getName();
        Category category = categoryRepository.findByName(categoryName)
                .orElseGet(() -> new Category(categoryName)); // create new if not found

        Products product = new Products(productDto.getName(), productDto.getPrice(), category);
        Products savedProduct = productRepository.save(product);

        // Ensure CategoryDto is updated with persisted category (especially if new)
        Category savedCategory = savedProduct.getCategory();
        CategoryDto categoryDto = new CategoryDto(savedCategory.getId(), savedCategory.getName());

        return new ProductDto(savedProduct.getId(), savedProduct.getName(), savedProduct.getPrice(), categoryDto);
    }


    public ProductDto getProduct(Long id) {
        Products product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id, HttpStatus.NOT_FOUND));

        Category category = product.getCategory();
        CategoryDto categoryDto = new CategoryDto(category.getId(), category.getName());

        return new ProductDto(product.getId(), product.getName(), product.getPrice(), categoryDto);
    }


    public List<ProductDto> searchProducts(String name, String categoryName) {
        List<Products> products = productRepository.searchProducts(name, categoryName);

        if (products.isEmpty()) {
            throw new ResourceNotFoundException("No products found with the given criteria.", HttpStatus.NOT_FOUND);
        }

        return products.stream()
                .map(p -> new ProductDto(
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getCategory() != null
                                ? new CategoryDto(p.getCategory().getId(), p.getCategory().getName())
                                : null
                ))
                .collect(Collectors.toList());
    }

}


