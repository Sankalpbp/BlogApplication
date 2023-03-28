package io.sankalp.blogapplication.security;

import io.sankalp.blogapplication.entity.User;
import io.sankalp.blogapplication.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;
import java.util.stream.Collectors;

public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService ( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername ( String usernameOrEmail ) {
        User user = userRepository.findByUsernameOrEmail ( usernameOrEmail, usernameOrEmail )
                      .orElseThrow ( () ->
                              new UsernameNotFoundException ( "User not found with username or email"
                                                                + usernameOrEmail ) );

        Set<GrantedAuthority> authorities = user.getRoles ()
                                                .stream ()
                                                .map ( ( role ) -> new SimpleGrantedAuthority ( role.getName () ) )
                                                .collect ( Collectors.toSet () );

        return new org.springframework.security.core.userdetails.User ( user.getEmail (),
                                                                        user.getPassword (),
                                                                        authorities );
    }
}
