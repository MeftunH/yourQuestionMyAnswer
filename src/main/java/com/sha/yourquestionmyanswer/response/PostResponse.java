package com.sha.yourquestionmyanswer.response;

import com.sha.yourquestionmyanswer.entities.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponse {
    Long id;
    Long userId;
    String userName;
    String title;
    String text;

    LocalDateTime createdAt;

    public PostResponse(Post entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUsername();
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.createdAt = entity.getCreatedAt();
    }
}
