package com.logrex.controller;

import com.logrex.dto.PostDto;
import com.logrex.dto.PostResponse;
import com.logrex.service.PostService;
import com.logrex.utils.AppConstants;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private PostService postService;

    @PostMapping
   public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){

       return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
   }

   @GetMapping
   public ResponseEntity<PostResponse> getAllPostByPagination(

           @RequestParam(value = "pageNo",defaultValue= AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
           @RequestParam(value="pageSize",defaultValue =AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
           @RequestParam(value="sortBy",defaultValue = AppConstants.DEFAULT_SORY_BY,required = false) String sortBy,
           @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir
   ){
    return ResponseEntity.ok(postService.getAllPostByPage(pageNo,pageSize,sortBy,sortDir));
   }

   @GetMapping("/all")
   public ResponseEntity<List<PostDto>> getAllPost(){

        return  ResponseEntity.ok(postService.getAllPost());
   }

   @GetMapping("/{id}")
   public ResponseEntity<PostDto> getById(@PathVariable Long id){

        return  ResponseEntity.ok(postService.getById(id));
   }


   @PutMapping("/{id}")
   public ResponseEntity<String> updateById(@Valid @RequestBody PostDto postDto,@PathVariable Long id){
       System.out.println(id);
        postService.updatePost(postDto,id);
        return  ResponseEntity.ok("Sucessfully updated...!");
   }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id){

        postService.deletePost(id);
        return  ResponseEntity.ok("Sucessfully Deleted...!");

    }


}
