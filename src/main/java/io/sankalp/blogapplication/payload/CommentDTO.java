package io.sankalp.blogapplication.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDTO {

    private Long id;

    @NotEmpty ( message = "name should not be null or empty" )
    private String name;

    @NotEmpty ( message = "email should not be null or empty" )
    @Email
    private String email;

    @NotEmpty ( message = "Comment body should not be null or empty" )
    @Size ( min = 10, message = "Comment body should have at least 10 characters" )
    private String body;

}
