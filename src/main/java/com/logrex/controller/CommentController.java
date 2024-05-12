package com.logrex.controller;

import com.logrex.dto.CommentDto;
import com.logrex.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> postComment( @PathVariable Long postId,@Valid @RequestBody CommentDto commentDto){


        return  new ResponseEntity<>(commentService.postComment(postId,commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getComment(@PathVariable Long postId){

        return  new ResponseEntity<>(commentService.findCommentById(postId), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentIdAndPostId(@PathVariable Long postId,@PathVariable Long id){

        return   ResponseEntity.ok(commentService.getCommentIdAndPostId(postId,id));
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@Valid @RequestBody CommentDto commentDto,@PathVariable Long postId,@PathVariable Long id){

        return   ResponseEntity.ok(commentService.updateComment(commentDto,postId,id));
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComments(@PathVariable Long postId,@PathVariable Long id){

        commentService.deleteComment(postId,id);
        return   ResponseEntity.ok("SucessFully deleted...!!!");
    }

}
