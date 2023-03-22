package io.sankalp.blogapplication.builder;

import io.sankalp.blogapplication.payload.PostDTO;

public class PostDTOBuilder {

    private PostDTO post;

    public static PostDTOBuilder create ( ) {
        return new PostDTOBuilder ();
    }

    private PostDTO getPostDTO () {
        if ( this.post == null ) {
            this.post = new PostDTO ();
        }

        return this.post;
    }

    public PostDTOBuilder id ( final Long id ) {
        getPostDTO ().setId ( id );
        return this;
    }

    public PostDTOBuilder title ( final String title ) {
        getPostDTO ().setTitle ( title );
        return this;
    }

    public PostDTOBuilder description ( final String description ) {
        getPostDTO ().setDescription ( description );
        return this;
    }

    public PostDTOBuilder content ( final String content ) {
        getPostDTO ().setContent ( content );
        return this;
    }

    public PostDTO build ( ) {
        return this.post;
    }
}
