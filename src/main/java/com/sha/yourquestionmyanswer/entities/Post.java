package com.sha.yourquestionmyanswer.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "user_id", nullable = false)
    Long userId;

    @Column(name = "title", nullable = false)
    String title;

    @Lob
    @Column(columnDefinition = "text", nullable = false)
    String text;
}
