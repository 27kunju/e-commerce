package com.e_commerce.e_commerce_demo.controller;

import com.e_commerce.e_commerce_demo.Dtos.ProductDto;
import com.e_commerce.e_commerce_demo.Services.ProductService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        ProductDto products = productService.createProduct(productDto);
        return new ResponseEntity<>(products, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id){
        ProductDto productDto = productService.getProduct(id);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProducts(@RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String categoryName){
        List<ProductDto> productDtoList = productService.searchProducts(name, categoryName);

        return  ResponseEntity.ok(productDtoList);
    }
}
