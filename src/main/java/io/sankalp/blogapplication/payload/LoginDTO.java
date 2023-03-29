package io.sankalp.blogapplication.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
    description = "LoginDTO Model Information"
)
public class LoginDTO {

    @Schema(
        description = "Username or Email of the user"
    )
    private String usernameOrEmail;

    @Schema(
        description = "Password of the user"
    )
    private String password;

}
