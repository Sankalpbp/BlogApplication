package io.sankalp.blogapplication.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
    description = "RegisterDTO Model Information"
)
public class RegisterDTO {

    @Schema(
        description = "Name of the user trying to register"
    )
    private String name;

    @Schema(
        description = "Username of the user trying to register"
    )
    private String username;

    @Schema(
        description = "Email of the user trying to register"
    )
    private String email;

    @Schema(
        description = "Password of the user trying to register"
    )
    private String password;

}
