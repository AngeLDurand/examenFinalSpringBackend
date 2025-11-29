package com.levelup.controller;

import com.levelup.dto.TokenDTO;
import com.levelup.dto.UserDTORequest;
import com.levelup.dto.UserDTOResponse;
import com.levelup.dto.UserLoginDTO;
import com.levelup.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> crearUsuario(@RequestBody UserDTORequest dto){
        UserDTOResponse usuarioCreado= userService.crearUsuario(dto);
        URI location = URI.create("/api/v1/users/" + usuarioCreado.getCorreo());
        return ResponseEntity.created(location).build(); // 201 created con encabezado Location
    }


    @PostMapping("/login")
    public ResponseEntity<TokenDTO> loguearUsuario(@RequestBody UserLoginDTO dto){
        TokenDTO token = userService.loguearUsuario(dto);
        return ResponseEntity.ok(token); // 200 OK con token en el body
    }
}
