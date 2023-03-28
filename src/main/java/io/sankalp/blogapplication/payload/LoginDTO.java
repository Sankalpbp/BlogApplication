package io.sankalp.blogapplication.payload;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    private String usernameOrEmail;
    private String password;

}
