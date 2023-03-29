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
    description = "CategoryDTO Model Information"
)
public class CategoryDTO {

    @Schema(
        description = "Blog Category Id"
    )
    private Long id;

    @Schema(
        description = "Blog Category name"
    )
    private String name;

    @Schema(
        description = "Blog Category description"
    )
    private String description;

}
