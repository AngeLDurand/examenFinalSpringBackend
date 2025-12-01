package com.levelup.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserPasswordChangeDTO {
    private String passwordActual;
    private String passwordNueva;
}
