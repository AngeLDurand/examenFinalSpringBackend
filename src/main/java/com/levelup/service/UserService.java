package com.levelup.service;

import com.levelup.dto.*;
import com.levelup.exception.NotFoundException;
import com.levelup.mapper.Mapper;
import com.levelup.model.User;
import com.levelup.repository.UserRepository;
import com.levelup.security.JwtService;
import com.levelup.security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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


        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("nombre", user.getNombre());

        String jwtToken = jwtService.generarToken(extraClaims, user);

        return TokenDTO.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public void cambiarClave(UserPasswordChangeDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String correo = auth.getName();

        User user = repository.findById(correo)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        if (!passwordEncoder.matches(dto.getPasswordActual(), user.getClave())) {
            throw new BadCredentialsException("La contraseña actual es incorrecta");
        }
        user.setClave(passwordEncoder.encode(dto.getPasswordNueva()));
        repository.save(user);
    }


}
