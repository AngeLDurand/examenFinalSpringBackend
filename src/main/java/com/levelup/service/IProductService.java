package com.levelup.service;

import com.levelup.dto.ProductDTOResponse;

import java.util.List;

public interface IProductService {
    List<ProductDTOResponse> listarProductos();
}
