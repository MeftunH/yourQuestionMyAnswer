package com.sha.yourquestionmyanswer.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "user_id", nullable = false)
    Long userId;

    @Column(name = "post_id", nullable = false)
    Long postId;

    @Lob
    @Column(columnDefinition = "text", nullable = false)
    String text;
}
