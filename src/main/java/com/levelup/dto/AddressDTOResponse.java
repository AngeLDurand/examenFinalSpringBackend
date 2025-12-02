package com.levelup.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AddressDTOResponse {
    private Long id;
    private String nombre;
    private String calle;
    private String ciudad;
}
