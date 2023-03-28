package io.sankalp.blogapplication.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.sankalp.blogapplication.exception.BlogAPIException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value ( "${app.jwt-secret}" )
    private String jwtSecret;

    @Value ( "${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    public String generateToken ( Authentication authentication ) {
        String username = authentication.getName ();
        Date currentDate = new Date ();
        Date expiryDate = new Date ( currentDate.getTime () + jwtExpirationDate );

        String token = Jwts.builder ()
                           .setSubject ( username )
                           .setIssuedAt ( currentDate )
                           .setExpiration ( expiryDate )
                           .signWith ( key () )
                           .compact ();

        return token;
    }

    private Key key ( ) {
        return Keys.hmacShaKeyFor (
                   Decoders.BASE64.decode ( jwtSecret )
               );
    }

    public String getUsername ( String token ) {
        Claims claims = Jwts.parserBuilder ()
                .setSigningKey ( key () )
                .build ()
                .parseClaimsJws ( token )
                .getBody ();

        String username = claims.getSubject ();
        return username;
    }

    public boolean validateToken ( String token ) {
        try {
            Jwts.parserBuilder ()
                    .setSigningKey ( key () )
                    .build ()
                    .parse ( token );

            return true;
        } catch ( MalformedJwtException exception ) {
            throw new BlogAPIException ( HttpStatus.BAD_REQUEST, "Invalid JWT Token" );
        } catch ( ExpiredJwtException exception ) {
            throw new BlogAPIException ( HttpStatus.BAD_REQUEST, "Expired JWT Token" );
        } catch ( UnsupportedJwtException exception ) {
            throw new BlogAPIException ( HttpStatus.BAD_REQUEST, "Unsupported JWT Token" );
        } catch ( IllegalArgumentException exception ) {
            throw new BlogAPIException ( HttpStatus.BAD_REQUEST, "JWT claims string is empty" );
        }
    }

}
