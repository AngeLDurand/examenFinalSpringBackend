package com.levelup.service;

import com.levelup.dto.*;


public interface IUserService {
    UserDTOResponse crearUsuario(UserDTORequest dto);
    TokenDTO loguearUsuario(UserLoginDTO dto);
    void cambiarClave( UserPasswordChangeDTO dto);

}
