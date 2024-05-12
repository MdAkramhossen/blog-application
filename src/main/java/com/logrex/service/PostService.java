package com.logrex.service;

import com.logrex.dto.PostDto;
import com.logrex.dto.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPost();

    PostDto getById(Long id);


    void updatePost(PostDto postDto,Long id);

    void deletePost(Long id);

    PostResponse getAllPostByPage(int pageNo, int pageSize,String sortBy,String sortDir);
}
