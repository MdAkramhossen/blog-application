package com.logrex.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "posts",uniqueConstraints = {@UniqueConstraint(columnNames = "title")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title",nullable = false)
    private  String title;

    @Column(name = "descriotions",nullable = false)
    private  String description;

    @Column(name = "content",nullable = false)
    private  String content;

         @OneToMany(cascade=CascadeType.ALL,mappedBy = "post")
         private List<Comment> comments;
}
