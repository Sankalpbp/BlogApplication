package io.sankalp.blogapplication.service;

import io.sankalp.blogapplication.payload.JwtAuthResponseDTO;
import io.sankalp.blogapplication.payload.LoginDTO;
import io.sankalp.blogapplication.payload.RegisterDTO;

public interface AuthService {

    public JwtAuthResponseDTO login (LoginDTO login );

    public String register ( RegisterDTO register );

}
