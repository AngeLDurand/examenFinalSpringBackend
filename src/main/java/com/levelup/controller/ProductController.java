package com.levelup.controller;

import com.levelup.dto.ProductDTOResponse;
import com.levelup.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTOResponse>> listarTodo() {
        return ResponseEntity.ok(productService.listarProductos());
    }
}
