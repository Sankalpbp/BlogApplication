package io.sankalp.blogapplication.service.impl;

import io.sankalp.blogapplication.entity.Role;
import io.sankalp.blogapplication.entity.User;
import io.sankalp.blogapplication.exception.BlogAPIException;
import io.sankalp.blogapplication.payload.JwtAuthResponseDTO;
import io.sankalp.blogapplication.payload.LoginDTO;
import io.sankalp.blogapplication.payload.RegisterDTO;
import io.sankalp.blogapplication.repository.RoleRepository;
import io.sankalp.blogapplication.repository.UserRepository;
import io.sankalp.blogapplication.security.JwtTokenProvider;
import io.sankalp.blogapplication.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider tokenProvider;

    public AuthServiceImpl ( AuthenticationManager authenticationManager,
                             UserRepository userRepository,
                             RoleRepository roleRepository,
                             PasswordEncoder passwordEncoder,
                             JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public JwtAuthResponseDTO login (LoginDTO login ) {
        Authentication authentication = authenticationManager.authenticate (
                new UsernamePasswordAuthenticationToken ( login.getUsernameOrEmail (),
                                                          login.getPassword () ) );

        SecurityContextHolder.getContext ().setAuthentication ( authentication );

        String token = tokenProvider.generateToken ( authentication );
        JwtAuthResponseDTO authResponse = new JwtAuthResponseDTO ();
        authResponse.setAccessToken ( token );

        return authResponse;
    }

    @Override
    public String register ( RegisterDTO register ) {

        if ( userRepository.existsByUsername ( register.getUsername () ) ) {
            throw new BlogAPIException ( HttpStatus.BAD_REQUEST, "Username already exists." );
        }

        if ( userRepository.existsByEmail ( register.getEmail () ) ) {
            throw new BlogAPIException ( HttpStatus.BAD_REQUEST, "Email already exists" );
        }

        User user = new User ();
        user.setName ( register.getName () );
        user.setEmail ( register.getEmail () );
        user.setUsername ( register.getUsername () );
        user.setPassword ( passwordEncoder.encode ( register.getPassword () ) );

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName ( "ROLE_USER" )
                                      .get();

        roles.add ( userRole );
        user.setRoles ( roles );

        userRepository.save ( user );

        return "User register successfully!";
    }

}
