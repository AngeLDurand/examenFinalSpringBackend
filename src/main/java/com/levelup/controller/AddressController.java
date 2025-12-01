package com.levelup.controller;

import com.levelup.dto.AddressDTORequest;
import com.levelup.dto.AddressDTOResponse;
import com.levelup.service.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final IAddressService addressService;

    @PostMapping
    public ResponseEntity<Void> crearDireccion(@RequestBody AddressDTORequest dto) {
        AddressDTOResponse direccionCreada = addressService.crearDireccion(dto);
        URI location = URI.create("/api/v1/addresses/" + direccionCreada.getId());
        return ResponseEntity.created(location).build();
    }
}
