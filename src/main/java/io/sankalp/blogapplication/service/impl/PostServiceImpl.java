package io.sankalp.blogapplication.service.impl;

import io.sankalp.blogapplication.builder.PostResponseBuilder;
import io.sankalp.blogapplication.entity.Post;
import io.sankalp.blogapplication.exception.ResourceNotFoundException;
import io.sankalp.blogapplication.payload.PostDTO;
import io.sankalp.blogapplication.payload.PostResponse;
import io.sankalp.blogapplication.repository.PostRepository;
import io.sankalp.blogapplication.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private ModelMapper mapper;

    public PostServiceImpl ( PostRepository postRepository,
                             ModelMapper mapper ) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public PostDTO createPost ( PostDTO post ) {
        Post newPost = dtoToEntity ( post );
        Post createdPost = postRepository.save ( newPost );

        return entityToDTO ( createdPost );
    }

    public PostDTO getPostById ( Long id ) {
        Post post = postRepository.findById ( id )
                                  .orElseThrow ( () -> new ResourceNotFoundException ( "Post",
                                                                                       "id",
                                                                                       id.toString () ) );

        return entityToDTO ( post );
    }

    public PostDTO updatePost ( PostDTO postDTO, Long id ) {
        Post post = postRepository.findById ( id )
                                  .orElseThrow ( () -> new ResourceNotFoundException ( "Post",
                                                                                       "id",
                                                                                       id.toString () ) );

        post.setTitle ( postDTO.getTitle () );
        post.setDescription( postDTO.getDescription () );
        post.setContent ( postDTO.getContent () );

        Post updatedPost = postRepository.save ( post );
        return entityToDTO ( updatedPost );
    }

    public String deletePostById ( Long id ) {
        postRepository.deleteById ( id );

        return "Post Deleted successfully!";
    }

    public PostResponse getAllPosts ( int pageNumber, int pageSize, String sortBy, String sortDir ) {

        Sort sort = sortDir.equalsIgnoreCase ( Sort.Direction.ASC.name () )
                        ? Sort.by ( sortBy ).ascending ()
                        : Sort.by ( sortBy ).descending ();

        Pageable pageable = PageRequest.of ( pageNumber, pageSize, sort );
        Page<Post> pageOfPosts = postRepository.findAll ( pageable );

        List<Post> posts = pageOfPosts.getContent ();

        List<PostDTO> content = posts.stream ( )
                                     .map ( this::entityToDTO )
                                     .toList ();

        return PostResponseBuilder.create ()
                .content ( content )
                        .totalPages ( pageOfPosts.getTotalPages () )
                                .totalElements ( pageOfPosts.getTotalElements () )
                                        .last ( pageOfPosts.isLast () )
                                                .pageNumber ( pageNumber )
                                                        .pageSize ( pageSize )
                                                            .build ();
    }

    private PostDTO entityToDTO ( Post post ) {
        return mapper.map ( post, PostDTO.class );
    }

    private Post dtoToEntity ( PostDTO postDTO ) {
        return mapper.map ( postDTO, Post.class );
    }

}
