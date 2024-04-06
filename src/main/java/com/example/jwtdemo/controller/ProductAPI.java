package com.example.jwtdemo.controller;

import com.example.jwtdemo.entity.ProductEntity;
import com.example.jwtdemo.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductAPI {
    private final ProductRepository productRepository;

    public ProductAPI(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    @RolesAllowed("ROLE_EDITOR") //-----role-based-----//
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity product) {
        ProductEntity savedProduct = productRepository.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

//    @CrossOrigin(origins = "*")
    @GetMapping
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_EDITOR"}) //-----role-based-----//
    public List<ProductEntity> getAllProducts() {
        List<ProductEntity> results = productRepository.findAll();
        return results;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable("id") int id, @RequestBody ProductEntity product) {
        ProductEntity prod = productRepository.findById(id).get();
        prod.setName(product.getName());
        prod.setPrice(product.getPrice());
        ProductEntity updatedProduct = productRepository.save(prod);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}
