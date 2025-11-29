package com.levelup.dto;


import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserDTOResponse {
    private String correo;
    private String nombre;
}
