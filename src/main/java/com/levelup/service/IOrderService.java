package com.levelup.service;

import com.levelup.dto.OrderResponseDTO;
import com.levelup.dto.PurchaseRequestDTO;
import com.levelup.dto.PurchaseResponseDTO;

import java.util.List;

public interface IOrderService {

    PurchaseResponseDTO procesarCompra(PurchaseRequestDTO dto);
    List<OrderResponseDTO> listarMisCompras();

}
