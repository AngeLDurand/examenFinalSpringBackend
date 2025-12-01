package com.levelup.service;

import com.levelup.dto.OrderResponseDTO;
import com.levelup.dto.PurchaseRequestDTO;
import com.levelup.dto.PurchaseResponseDTO;
import com.levelup.exception.NotFoundException;
import com.levelup.mapper.Mapper;
import com.levelup.model.*;
import com.levelup.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {


    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ShippingAddressRepository addressRepository;


    private String getCorreoActual() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    @Transactional
    public PurchaseResponseDTO procesarCompra(PurchaseRequestDTO dto) {
        String correo = getCorreoActual();

        User user = userRepository.findById(correo)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        ShippingAddress address = addressRepository.findById(dto.getAddressId())
                .orElseThrow(() -> new NotFoundException("Dirección no encontrada"));

        if (!address.getUser().getCorreo().equals(correo)) {
            throw new AccessDeniedException("La dirección no pertenece al usuario");
        }

        Order order = Order.builder()
                .user(user)
                .shippingAddress(address)
                .fecha(LocalDateTime.now())
                .estado("PAGADO")
                .build();

        List<OrderItem> items = new ArrayList<>();
        long totalCalculado = 0L;

        for (var itemDTO : dto.getItems()) {
            Product p = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new NotFoundException("Producto ID " + itemDTO.getProductId() + " no existe"));

            OrderItem item = OrderItem.builder()
                    .order(order)
                    .product(p)
                    .cantidad(itemDTO.getCantidad())
                    .precioUnitario(p.getPrecio())
                    .build();

            items.add(item);
            totalCalculado += (p.getPrecio() * itemDTO.getCantidad());
        }

        order.setItems(items);
        order.setTotal(totalCalculado);

     Order compraGuardada=   orderRepository.save(order);

     return PurchaseResponseDTO
             .builder()
             .id(compraGuardada.getId())
             .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDTO> listarMisCompras() {
        String correo = getCorreoActual();


        return orderRepository.findByUser_CorreoOrderByFechaDesc(correo)
                .stream()
                .map(Mapper::toOrderDTO)
                .collect(Collectors.toList());
    }





}
