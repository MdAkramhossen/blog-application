package com.logrex.service.impl;

import com.logrex.dto.CommentDto;
import com.logrex.entity.Comment;
import com.logrex.entity.Post;
import com.logrex.exception.BlogApiExceptions;
import com.logrex.exception.NotFoundExceptions;
import com.logrex.repository.CommentRepository;
import com.logrex.repository.PostRepository;
import com.logrex.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto postComment(Long postId,CommentDto commentDto) {

        Post post=postRepository.findById(postId).orElseThrow(()-> new NotFoundExceptions("Post","id :",postId));

        Comment comment=modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
        Comment comment1=  commentRepository.save(comment);
        return modelMapper.map(comment1,CommentDto.class);
    }

    @Override
    public List<CommentDto> findCommentById(Long postId) {

       List<Comment> comments= commentRepository.findByPostId(postId);
        return comments.stream().map((cmnt)->modelMapper.map(cmnt,CommentDto.class)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentIdAndPostId(Long postId, Long id) {

        Post post=postRepository.findById(postId).orElseThrow(()-> new NotFoundExceptions("Get Post","id : ",id));
        Comment comment= commentRepository.findById(id).orElseThrow(()-> new NotFoundExceptions("Get comment","Id :",id));

            if (comment.getPost().getId()!=post.getId())
                throw  new BlogApiExceptions(HttpStatus.BAD_REQUEST,"comment does't belond to post");

        return modelMapper.map(comment,CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Long postId, Long id) {

        Post post= postRepository.findById(postId).orElseThrow(()-> new NotFoundExceptions("Put","id : ",postId));
        Comment comment= commentRepository.findById(id).orElseThrow(()->new NotFoundExceptions("update Comment ","id : ",id));

        if (comment.getPost().getId()!=post.getId())
            throw  new BlogApiExceptions(HttpStatus.BAD_REQUEST,"comment are't belongs in the post");

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        commentRepository.save(comment);


        return modelMapper.map(comment,CommentDto.class);
    }

    @Override
    public void deleteComment(Long postId, Long id) {

        Post post= postRepository.findById(postId).orElseThrow(()-> new NotFoundExceptions("delete"," id",postId));

        Comment comment= commentRepository.findById(id).orElseThrow(()->new NotFoundExceptions("Delete Comment ","id : ",id));
if (comment.getPost().getId()!=post.getId())
    throw new BlogApiExceptions(HttpStatus.BAD_REQUEST,"comment are't belongs in this post");
commentRepository.delete(comment);


    }


}
