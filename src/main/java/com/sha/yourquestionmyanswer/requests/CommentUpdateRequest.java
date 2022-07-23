package com.sha.yourquestionmyanswer.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentUpdateRequest {
    private String text;
    LocalDateTime createdAt;

}
