package com.levelup.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDTO {
    private Long id;
    private LocalDateTime fecha;
    private Long total;
    private String estado;
    private String direccionCalle;
    private String direccionCiudad;
    private List<OrderItemResponseDTO> items;
}
