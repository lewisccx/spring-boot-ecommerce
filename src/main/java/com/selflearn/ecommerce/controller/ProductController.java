package com.selflearn.ecommerce.controller;

import java.util.List;

import com.selflearn.ecommerce.entity.Product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @GetMapping(path = "/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        return  null;
    }

    @GetMapping(path = "/{productId}")
    public ResponseEntity<List<Product>> getProduct(@PathVariable("productId") Long productId){
            return  null;
    }
}