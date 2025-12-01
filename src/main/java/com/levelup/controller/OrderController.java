package com.levelup.controller;

import com.levelup.dto.OrderResponseDTO;
import com.levelup.dto.PurchaseRequestDTO;
import com.levelup.dto.PurchaseResponseDTO;
import com.levelup.service.IOrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {


    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity<PurchaseResponseDTO> comprar(@RequestBody PurchaseRequestDTO dto) {

        PurchaseResponseDTO response = orderService.procesarCompra(dto);

        URI location = URI.create("/api/v1/orders/" + response.getId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> misCompras() {
        return ResponseEntity.ok(orderService.listarMisCompras());
    }
}
