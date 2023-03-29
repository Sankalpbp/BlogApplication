package io.sankalp.blogapplication.controller;

import io.sankalp.blogapplication.payload.JwtAuthResponseDTO;
import io.sankalp.blogapplication.payload.LoginDTO;
import io.sankalp.blogapplication.payload.RegisterDTO;
import io.sankalp.blogapplication.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ( "/api/auth")
@Tag (
    name = "Login/Register REST APIs for authenticating the user"
)
public class AuthController {

    private AuthService authService;

    public AuthController ( AuthService authService ) {
        this.authService = authService;
    }

    @Operation (
        summary = "Login API",
        description = "Login API for authenticating the user and sending the token"
    )
    @ApiResponse (
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @PostMapping ( value = {
        "/login",
        "/signIn"
    })
    public ResponseEntity<JwtAuthResponseDTO> login ( @RequestBody LoginDTO login ) {
        JwtAuthResponseDTO authResponse = authService.login ( login );
        return ResponseEntity.ok ( authResponse );
    }

    @Operation (
        summary = "Register API",
        description = "Register API for the incoming user"
    )
    @ApiResponse (
        responseCode = "201",
        description = ""
    )
    @PostMapping ( value = {
            "/signUp",
            "/register"
    })
    public ResponseEntity<String> register ( @RequestBody RegisterDTO register ) {
        String response = authService.register ( register );
        return new ResponseEntity<> ( response, HttpStatus.CREATED );
    }

}
