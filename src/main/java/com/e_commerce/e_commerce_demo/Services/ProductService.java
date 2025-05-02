package com.e_commerce.e_commerce_demo.Services;

import com.e_commerce.e_commerce_demo.Dtos.CategoryDto;
import com.e_commerce.e_commerce_demo.Dtos.ProductDto;
import com.e_commerce.e_commerce_demo.Repository.ProductRepository;
import com.e_commerce.e_commerce_demo.model.Category;
import com.e_commerce.e_commerce_demo.model.Products;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public ProductDto createProduct(ProductDto productDto){
        CategoryDto categoryDto = productDto.getCategory();
        Category category = new Category(categoryDto.getName());
        Products products = new Products(productDto.getName(), productDto.getPrice(), category);
        Products savedProduct = productRepository.save(products);
        categoryDto.setName(savedProduct.getCategory().getName());
        categoryDto.setId(savedProduct.getCategory().getId());
        return new ProductDto(savedProduct.getId(), savedProduct.getName(), savedProduct.getPrice(), categoryDto);
    }

    public ProductDto getProduct(Long id){
        Optional<Products> optionalProducts = productRepository.findById(id);
        if(optionalProducts.isPresent()){
            Products products = optionalProducts.get();
            Category category = products.getCategory();

            CategoryDto categoryDto = new CategoryDto(category.getId(), category.getName());
            return new ProductDto(products.getId(), products.getName(), products.getPrice(), categoryDto);

        }
        return null;
    }

    public List<ProductDto> searchProducts(String name, String categoryName){

        List<Products> products = productRepository.searchProducts(name, categoryName);
        return products.stream().map(p -> new ProductDto(
                p.getId(),
                p.getName(),
                p.getPrice(), p.getCategory() != null
                                ? new CategoryDto(p.getCategory().getId(), p.getCategory().getName())
                                : null
        ))
                .collect(Collectors.toList());


    }
}


