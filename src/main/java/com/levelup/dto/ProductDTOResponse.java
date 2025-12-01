package com.levelup.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductDTOResponse {
    private Long id;
    private String modelo;
    private String descripcion;
    private Long precio;
    private String categoria;
    private String marca;
    private String imagenUrl;
}
