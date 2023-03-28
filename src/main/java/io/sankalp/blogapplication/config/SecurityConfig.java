package io.sankalp.blogapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder ( ) {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity security ) throws Exception {
        security.csrf ()
                .disable ()
                .authorizeHttpRequests ( ( authorize ) -> authorize.anyRequest ().authenticated () )
                .httpBasic ( Customizer.withDefaults () );

        return security.build ();
    }

    @Bean
    public UserDetailsService userDetailsService ( ) {
        UserDetails main = User.builder ()
                               .username ( "sankalp" )
                               .password ( passwordEncoder ( ).encode ( "sankalp" ) )
                               .roles ( "USER" )
                               .build ();

        UserDetails admin = User.builder ()
                                .username ( "admin" )
                                .password ( passwordEncoder ( ).encode ( "admin" ) )
                                .roles ( "ADMIN" )
                                .build ();

        return new InMemoryUserDetailsManager ( main, admin );
    }

}
