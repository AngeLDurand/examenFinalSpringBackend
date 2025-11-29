package com.levelup.service;

import com.levelup.dto.TokenDTO;
import com.levelup.dto.UserDTORequest;
import com.levelup.dto.UserDTOResponse;
import com.levelup.dto.UserLoginDTO;


public interface IUserService {
    UserDTOResponse crearUsuario(UserDTORequest dto);
    public TokenDTO loguearUsuario(UserLoginDTO dto);

}
