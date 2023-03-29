package io.sankalp.blogapplication.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
    description = "CommentDTO Model Information"
)
public class CommentDTO {

    private Long id;

    @Schema(
        description = "Blog comment name"
    )
    @NotEmpty ( message = "name should not be null or empty" )
    private String name;

    @Schema(
        description = "Blog comment email"
    )
    @NotEmpty ( message = "email should not be null or empty" )
    @Email
    private String email;

    @Schema(
        description = "Blog comment body"
    )
    @NotEmpty ( message = "Comment body should not be null or empty" )
    @Size ( min = 10, message = "Comment body should have at least 10 characters" )
    private String body;

}
