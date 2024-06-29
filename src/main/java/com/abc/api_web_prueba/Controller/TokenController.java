package com.abc.api_web_prueba.Controller;
import com.abc.api_web_prueba.Dto.ResponseDTO;
import com.abc.api_web_prueba.Security.JwtTokenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class TokenController {

    private final JwtTokenService jwtTokenService;

    public TokenController(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @GetMapping("/generate-token")
    public ResponseDTO generateToken() {
        return jwtTokenService.generateToken();
    }
}
