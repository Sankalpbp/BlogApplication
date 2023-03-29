package io.sankalp.blogapplication.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
    description = "PostResponse Model Information"
)
public class PostResponse {

    @Schema(
        description = "Posts"
    )
    private List<PostDTO> content;

    @Schema(
        description = "Page Number of the Posts"
    )
    private int pageNumber;

    @Schema(
        description = "Page Size of the Page showing Posts"
    )
    private int pageSize;

    @Schema(
        description = "Total number of Posts"
    )
    private long totalElements;

    @Schema(
        description = "Total number of Pages"
    )
    private long totalPages;

    @Schema(
        description = "Is this page the last one?"
    )
    private boolean last;

}
