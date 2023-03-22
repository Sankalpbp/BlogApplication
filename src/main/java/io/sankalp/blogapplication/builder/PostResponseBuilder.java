package io.sankalp.blogapplication.builder;

import io.sankalp.blogapplication.payload.PostDTO;
import io.sankalp.blogapplication.payload.PostResponse;

import java.util.List;

public class PostResponseBuilder {

    private PostResponse postResponse;

    public static PostResponseBuilder create ( ) {
        return new PostResponseBuilder ();
    }

    private PostResponse getPostResponse ( ) {
        if ( this.postResponse == null ) {
            this.postResponse = new PostResponse ();
        }
        return this.postResponse;
    }

    public PostResponseBuilder content ( final List<PostDTO> content ) {
        getPostResponse ().setContent ( content );
        return this;
    }

    public PostResponseBuilder pageNumber ( final int pageNumber ) {
        getPostResponse ().setPageNumber ( pageNumber );
        return this;
    }

    public PostResponseBuilder pageSize ( final int pageSize ) {
        getPostResponse ().setPageSize ( pageSize );
        return this;
    }

    public PostResponseBuilder totalElements ( final long totalElements ) {
        getPostResponse ().setTotalElements ( totalElements );
        return this;
    }

    public PostResponseBuilder totalPages ( final long totalPages ) {
        getPostResponse ().setTotalPages ( totalPages );
        return this;
    }

    public PostResponseBuilder last ( final boolean last ) {
        getPostResponse ().setLast ( last );
        return this;
    }

    public PostResponse build () {
        return this.postResponse;
    }
}
