package com.levelup.mapper;

import com.levelup.dto.UserDTOResponse;
import com.levelup.model.User;

public class Mapper {


    public static UserDTOResponse toDTO(User u){
        if (u == null) return null;
        return UserDTOResponse.builder()
                .correo(u.getCorreo())
                .nombre(u.getNombre())
                .build();
    }
}
