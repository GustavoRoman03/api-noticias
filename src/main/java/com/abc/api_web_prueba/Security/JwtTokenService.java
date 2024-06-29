package com.abc.api_web_prueba.Security;

import com.abc.api_web_prueba.Dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

@Service
public class JwtTokenService {

    private static final String SECRET_KEY = "SecretKeyToGenJWTs";

    public ResponseDTO generateToken() {
        try {
            String token = Jwts.builder()
                    .setSubject("exampleUser")
                    .setExpiration(new Date(System.currentTimeMillis() + 864_000_000)) // 10 días
                    .signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes())
                    .compact();

            return new ResponseDTO(new Date(), HttpStatus.OK, "Token generado exitosamente", token);
        } catch (Exception e) {
            return new ResponseDTO("Error al generar el token", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
