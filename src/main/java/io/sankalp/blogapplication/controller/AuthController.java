package io.sankalp.blogapplication.controller;

import io.sankalp.blogapplication.payload.LoginDTO;
import io.sankalp.blogapplication.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> login ( @RequestBody LoginDTO login ) {
        String response = authService.login ( login );
        return ResponseEntity.ok ( response );
    }

}
