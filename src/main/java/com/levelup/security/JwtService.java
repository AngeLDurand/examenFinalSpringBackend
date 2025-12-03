package com.levelup.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService{

    @Value("${security.jwt.secret}")
    private String SECRET_KEY;

    private Claims extraerPayload(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(obtenerKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extraerClaim(String token, Function<Claims,T> fn){
        final Claims payload = extraerPayload(token);
        return fn.apply(payload);
    }


    public String extraerCorreo(String token) {
        return extraerClaim(token, Claims::getSubject);
    }

    private Date extraerFechaExpiracion(String token){
        return extraerClaim(token,Claims::getExpiration);
    }

    private Key obtenerKey(){
        byte[] bytesDecodificados= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytesDecodificados);
    }




    public String generarToken(
            Map<String,Object> datosExtra,
            UserDetails user
    ){
        return Jwts
                .builder()
                .setClaims(datosExtra)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(obtenerKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generarToken(UserDetails user){
        return generarToken(new HashMap<>(),user);
    }


    private boolean tokenHaExpirado(String token){
        return extraerFechaExpiracion(token).before(new Date());

    }

    public boolean validarToken(String token, UserDetails user){
        final String correo = extraerCorreo(token);
        return (correo.equals(user.getUsername()))&& !tokenHaExpirado(token);
    }


}
