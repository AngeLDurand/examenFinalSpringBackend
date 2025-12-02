package com.levelup.service;

import com.levelup.dto.AddressDTORequest;
import com.levelup.dto.AddressDTOResponse;
import com.levelup.exception.NotFoundException;
import com.levelup.model.ShippingAddress;
import com.levelup.model.User;
import com.levelup.repository.ShippingAddressRepository;
import com.levelup.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AddressService implements IAddressService {

    private final ShippingAddressRepository addressRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public AddressDTOResponse crearDireccion(AddressDTORequest dto) {
        String correoAuth = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findById(correoAuth)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado en el contexto de seguridad"));

        ShippingAddress nuevaDireccion = ShippingAddress.builder()
                .user(user)
                .nombre(dto.getNombre())
                .calle(dto.getCalle())
                .ciudad(dto.getCiudad())
                .build();

        ShippingAddress addr = addressRepository.save(nuevaDireccion);

        return AddressDTOResponse.builder()
                .id(addr.getId())
                .nombre(addr.getNombre())
                .calle(addr.getCalle())
                .ciudad(addr.getCiudad())
                .build();
    }


    @Override
    @Transactional(readOnly = true)
    public List<AddressDTOResponse> listarMisDirecciones() {

        String correoAuth = SecurityContextHolder.getContext().getAuthentication().getName();

        return addressRepository.findByUser_Correo(correoAuth)
                .stream()
                .map(addr -> AddressDTOResponse.builder()
                        .id(addr.getId())
                        .nombre(addr.getNombre())
                        .calle(addr.getCalle())
                        .ciudad(addr.getCiudad())
                        .build())
                .collect(Collectors.toList());
    }




}
