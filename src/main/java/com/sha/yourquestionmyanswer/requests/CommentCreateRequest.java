package com.sha.yourquestionmyanswer.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentCreateRequest {
    Long id;
    Long userId;
    Long postId;
    String text;
    LocalDateTime createdAt;
}
