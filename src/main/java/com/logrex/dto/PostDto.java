package com.logrex.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {


    private long id;

   @NotEmpty
   @Size(min=2, message = "post title atleast should have 2 chracter")
    private  String title;

   @NotEmpty
   @Size(min = 10,message = "Descriptions atleast should 10 chracter")
    private  String description;

  @NotEmpty
    private  String content;

    private Set<CommentDto> commestDto;
}
