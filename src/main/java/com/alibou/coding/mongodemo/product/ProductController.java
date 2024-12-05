package com.alibou.coding.mongodemo.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    public ResponseEntity<String> save(
            @RequestBody Product product
    ){
        return ResponseEntity.ok(service.save(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<Product> findById(
            @PathVariable("product-id") String productId
    ){
        return ResponseEntity.ok(service.findById(productId));
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("product-id") String productId
    ){
        service.delete(productId);
        return ResponseEntity.accepted().build();
    }

}
