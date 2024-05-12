package com.logrex.service.impl;

import com.logrex.dto.PostDto;
import com.logrex.dto.PostResponse;
import com.logrex.entity.Post;
import com.logrex.exception.NotFoundExceptions;
import com.logrex.repository.PostRepository;
import com.logrex.service.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

  private PostRepository postRepository;

    @Override
    public PostDto getById(Long id) {

        Post post=postRepository.findById(id).orElseThrow(()->new NotFoundExceptions("Post","id",id));

        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public void updatePost(PostDto postDto,Long id) {

        Post post=postRepository.findById(id).orElseThrow(()->new NotFoundExceptions("Update","id",id));

        System.out.println(post);

       // post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        //postRepository.save(modelMapper.map(postDto,Post.class));
        postRepository.save(post);


    }

    @Override
    public void deletePost(Long id) {

        Post post= postRepository.findById(id).orElseThrow(()-> new NotFoundExceptions("Deleted","Id",id));
        postRepository.delete(post);
    }

    @Override
    public PostResponse getAllPostByPage(int pageNo, int pageSize,String sortBy,String sortDir) {

        Sort sort= sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);

        Page<Post> page=postRepository.findAll(pageable);

        List<Post> posts=page.getContent();

        List<PostDto> contents= posts.stream().map((str)->modelMapper.map(str,PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse= new PostResponse();

        postResponse.setContent(contents);
        postResponse.setPageNo(page.getNumber());
        postResponse.setPageSize(page.getSize());
        postResponse.setTotalElement((int) page.getTotalElements());
        postResponse.setTotalPages(page.getTotalPages());
        postResponse.setLast(page.isLast());

        return  postResponse;

    }

    private ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto postDto) {

        Post post= modelMapper.map(postDto,Post.class);
        System.out.println(post.getComments().getId());
       Post post1= postRepository.save(post);

       return modelMapper.map(post1,PostDto.class);
    }

    @Override
    public List<PostDto> getAllPost() {

        List<Post> post=postRepository.findAll();

        return post.stream().map((single)->modelMapper.map(single,PostDto.class)).collect(Collectors.toList());
    }
}
