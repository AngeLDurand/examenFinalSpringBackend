package com.levelup.mapper;

import com.levelup.dto.ProductDTOResponse;
import com.levelup.dto.UserDTOResponse;
import com.levelup.model.Product;
import com.levelup.model.User;

public class Mapper {


    public static UserDTOResponse toDTO(User u){
        if (u == null) return null;
        return UserDTOResponse.builder()
                .correo(u.getCorreo())
                .nombre(u.getNombre())
                .build();
    }

    public static ProductDTOResponse toDTO(Product p) {
        if (p == null) return null;
        return ProductDTOResponse.builder()
                .id(p.getId())
                .modelo(p.getModelo())
                .descripcion(p.getDescripcion())
                .precio(p.getPrecio())
                .categoria(p.getCategoria())
                .marca(p.getMarca())
                .imagenUrl(p.getImagenUrl())
                .build();
    }
}
