package io.sankalp.blogapplication.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDTO {
    private Long id;

    @NotEmpty
    @Size ( min = 2, message = "The title of the post should have at least 2 characters." )
    private String title;

    @NotEmpty
    @Size ( min = 10, message = "The description of the post should have at least 10 characters." )
    private String description;

    @NotEmpty
    private String content;

    private Set<CommentDTO> comments;
}
