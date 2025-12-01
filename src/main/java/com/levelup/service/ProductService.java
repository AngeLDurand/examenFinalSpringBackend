package com.levelup.service;

import com.levelup.dto.ProductDTOResponse;
import com.levelup.mapper.Mapper;
import com.levelup.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTOResponse> listarProductos() {

        return productRepository.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }
}
