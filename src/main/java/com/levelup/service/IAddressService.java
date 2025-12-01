package com.levelup.service;

import com.levelup.dto.AddressDTORequest;
import com.levelup.dto.AddressDTOResponse;

public interface IAddressService {
    AddressDTOResponse crearDireccion(AddressDTORequest dto);
}
