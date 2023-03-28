package io.sankalp.blogapplication.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.Base64;

public class PasswordGenerator {

    public static void main ( String... args ) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder ();
        /*
        System.out.println ( passwordEncoder.encode ( "admin" ) );
        System.out.println ( passwordEncoder.encode ( "sankalp" ) );
        */

        System.out.println ( generateSafeToken() );
    }

    private static String generateSafeToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[36]; // 36 bytes * 8 = 288 bits, a little bit more than
        // the 256 required bits
        random.nextBytes(bytes);
        var encoder = Base64.getUrlEncoder().withoutPadding();
        return encoder.encodeToString(bytes);
    }
}
