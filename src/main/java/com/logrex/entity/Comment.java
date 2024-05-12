package com.logrex.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "names")
   private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "body")
    private String body;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name ="post_id",nullable = false)
         private  Post post;
}
