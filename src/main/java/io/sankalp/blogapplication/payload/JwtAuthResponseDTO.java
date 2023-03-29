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
    description = "JwtAuthResponseDTO Model Information"
)
public class JwtAuthResponseDTO {

    @Schema(
        description = "Login Access Token"
    )
    private String accessToken;

    @Schema(
        description = "Login Access Token Type"
    )
    private final String tokenType = "Bearer";
}
