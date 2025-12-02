package com.levelup.service;

import com.levelup.dto.AddressDTORequest;
import com.levelup.dto.AddressDTOResponse;

import java.util.List;

public interface IAddressService {
    AddressDTOResponse crearDireccion(AddressDTORequest dto);
    List<AddressDTOResponse> listarMisDirecciones();
}
