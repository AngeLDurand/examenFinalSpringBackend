package com.levelup.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponseDTO {
    private Long productoId;
    private String modelo;
    private String imagenUrl;
    private Integer cantidad;
    private Long precioUnitarioPagado;
}
