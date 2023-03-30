package io.sankalp.blogapplication;

import io.sankalp.blogapplication.entity.Role;
import io.sankalp.blogapplication.repository.RoleRepository;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition (
    info = @Info (
        title = "Blog App REST APIs",
        description = "Blog App REST APIs Documentation",
        version = "v1.0",
        contact = @Contact (
            name = "Sankalp",
            email = "sankalpnitj@gmail.com",
            url = "https://linkedin.com/arora-sankalp"
        ),
        license = @License (
            name = "Apache 2.0",
            url = "https://google.com"
        )
    ),
    externalDocs = @ExternalDocumentation (
        description = "Blog App documentation",
        url = "https://github.com/Sankalpbp/BlogApplication"
    )
)
public class BlogApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Bean
    public ModelMapper getModelMapper () {
        return new ModelMapper ();
    }

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run ( String... args ) throws Exception {
        Role adminRole = new Role ();
        adminRole.setName ( "ROLE_ADMIN" );
        roleRepository.save ( adminRole );

        Role userRole = new Role ();
        userRole.setName ( "USER_ROLE" );
        roleRepository.save ( userRole );

    }
}
