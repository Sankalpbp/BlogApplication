package io.sankalp.blogapplication.config;

import io.sankalp.blogapplication.security.JwtAuthenticationEntryPoint;
import io.sankalp.blogapplication.security.JwtAuthenticationFilter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@SecurityScheme (
        name = "Bear Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SecurityConfig {

    private UserDetailsService userDetailsService;

    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    private JwtAuthenticationFilter authenticationFilter;

    public SecurityConfig ( UserDetailsService userDetailsService,
                            JwtAuthenticationEntryPoint authenticationEntryPoint,
                            JwtAuthenticationFilter authenticationFilter ) {
        this.userDetailsService = userDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder ( ) {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager ( AuthenticationConfiguration configuration ) throws Exception {
        return configuration.getAuthenticationManager ();
    }

    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity security ) throws Exception {
        security.csrf ()
                .disable ()
                .authorizeHttpRequests ( ( authorize ) -> authorize.requestMatchers ( HttpMethod.GET, "/api/**" )
                                                                   .permitAll ()
                                                                   .requestMatchers ( "/api/auth/**" )
                                                                   .permitAll ()
                                                                   .requestMatchers ( "/swagger-ui/**")
                                                                   .permitAll ()
                                                                   .requestMatchers ( "/v3/api-docs/**" )
                                                                   .permitAll ()
                                                                   .anyRequest ()
                                                                   .authenticated ()
                ).exceptionHandling (
                        exception -> exception.authenticationEntryPoint ( authenticationEntryPoint )
                ).sessionManagement (
                        session -> session.sessionCreationPolicy ( SessionCreationPolicy.STATELESS )
                );

        security.addFilterBefore ( authenticationFilter, UsernamePasswordAuthenticationFilter.class );

        return security.build ();
    }

    /*
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

     */

}
