package io.sankalp.blogapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table ( name = "categories" )
public class Category {

    @Id
    @GeneratedValue(
        strategy = GenerationType.AUTO
    )
    private Long id;
    private String name;
    private String description;

    @OneToMany (
        mappedBy = "category",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Post> posts;
}
