package io.sankalp.blogapplication.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Schema (
    description = "PostDTO Model Information"
)
public class PostDTO {
    private Long id;

    @Schema (
        description = "Blog Post title"
    )
    @NotEmpty
    @Size ( min = 2, message = "The title of the post should have at least 2 characters." )
    private String title;

    @Schema (
        description = "Blog Post description"
    )
    @NotEmpty
    @Size ( min = 10, message = "The description of the post should have at least 10 characters." )
    private String description;

    @Schema (
        description = "Blog Post content"
    )
    @NotEmpty
    private String content;

    private Set<CommentDTO> comments;

    @Schema (
        description = "Blog Post Category"
    )
    private Long categoryId;
}
