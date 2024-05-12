package com.logrex.service;

import com.logrex.dto.CommentDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CommentService {
    CommentDto postComment(Long postId,CommentDto commentDto);

    List<CommentDto> findCommentById(Long postId);
   CommentDto  getCommentIdAndPostId(@PathVariable Long postId, @PathVariable Long id);

    CommentDto updateComment(CommentDto commentDto, Long postId, Long id);

    void deleteComment(Long postId, Long id);
}
