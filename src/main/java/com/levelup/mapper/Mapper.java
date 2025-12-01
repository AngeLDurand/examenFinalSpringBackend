package com.levelup.mapper;

import com.levelup.dto.OrderItemResponseDTO;
import com.levelup.dto.OrderResponseDTO;
import com.levelup.dto.ProductDTOResponse;
import com.levelup.dto.UserDTOResponse;
import com.levelup.model.Order;
import com.levelup.model.Product;
import com.levelup.model.User;

import java.util.stream.Collectors;

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


    public static OrderResponseDTO toOrderDTO(Order order) {
        if (order == null) return null;

        var itemsDTO = order.getItems().stream()
                .map(item -> OrderItemResponseDTO.builder()
                        .productoId(item.getProduct().getId())
                        .modelo(item.getProduct().getModelo())
                        .imagenUrl(item.getProduct().getImagenUrl())
                        .cantidad(item.getCantidad())
                        .precioUnitarioPagado(item.getPrecioUnitario())
                        .build())
                .collect(Collectors.toList());

        return OrderResponseDTO.builder()
                .id(order.getId())
                .fecha(order.getFecha())
                .total(order.getTotal())
                .estado(order.getEstado())
                .direccionCalle(order.getShippingAddress().getCalle())
                .direccionCiudad(order.getShippingAddress().getCiudad())
                .items(itemsDTO)
                .build();
    }
}
