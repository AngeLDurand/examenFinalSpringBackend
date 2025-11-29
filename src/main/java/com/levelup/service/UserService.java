package com.levelup.service;

import com.levelup.dto.TokenDTO;
import com.levelup.dto.UserDTORequest;
import com.levelup.dto.UserDTOResponse;
import com.levelup.dto.UserLoginDTO;
import com.levelup.mapper.Mapper;
import com.levelup.model.User;
import com.levelup.repository.UserRepository;
import com.levelup.security.JwtService;
import com.levelup.security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {


    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private  final AuthenticationManager authenticationManager;


    @Override
    public UserDTOResponse crearUsuario(UserDTORequest dto) {
        User newUser  = User.builder()
                .correo(dto.getCorreo())
                .nombre(dto.getNombre())
                .clave(passwordEncoder.encode(dto.getClave()) )
                .rol(Role.USER)
                .build();

       return Mapper.toDTO(repository.save(newUser));

    }


    @Override
    public TokenDTO loguearUsuario(UserLoginDTO dto){
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getCorreo(),
                        dto.getClave()
                )
        );

        User user= (User) authentication.getPrincipal();

        String jwtToken= jwtService.generarToken(user);
        return TokenDTO.builder()
                .token(jwtToken)
                .build();
    }




}
