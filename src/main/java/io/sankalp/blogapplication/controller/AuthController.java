package io.sankalp.blogapplication.controller;

import io.sankalp.blogapplication.payload.JwtAuthResponseDTO;
import io.sankalp.blogapplication.payload.LoginDTO;
import io.sankalp.blogapplication.payload.RegisterDTO;
import io.sankalp.blogapplication.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping ( "/api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController ( AuthService authService ) {
        this.authService = authService;
    }

    @PostMapping ( value = {
            "/login",
            "/signIn"
    })
    public ResponseEntity<JwtAuthResponseDTO> login ( @RequestBody LoginDTO login ) {
        JwtAuthResponseDTO authResponse = authService.login ( login );
        return ResponseEntity.ok ( authResponse );
    }

    @PostMapping ( value = {
            "/signUp",
            "/register"
    })
    public ResponseEntity<String> register ( @RequestBody RegisterDTO register ) {
        String response = authService.register ( register );
        return new ResponseEntity<> ( response, HttpStatus.CREATED );
    }

}
