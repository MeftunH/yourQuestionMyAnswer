package com.sha.yourquestionmyanswer.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostCreateRequest {
    Long id;
    String text;
    String title;
    Long userId;
    LocalDateTime createdAt;
}
