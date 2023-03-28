package io.sankalp.blogapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table ( name = "roles" )
public class Role {

    @Id
    @GeneratedValue (
            strategy = GenerationType.AUTO
    )
    private Long id;
    private String name;
}
