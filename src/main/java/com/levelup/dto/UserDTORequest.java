package com.levelup.dto;


import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserDTORequest {
    private String correo;
    private String nombre;
    private String clave;
}
