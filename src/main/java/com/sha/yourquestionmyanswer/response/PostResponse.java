package com.sha.yourquestionmyanswer.response;

import com.sha.yourquestionmyanswer.entities.Post;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostResponse {
    Long id;
    Long userId;
    String userName;
    String title;
    String text;

    LocalDateTime createdAt;
    List<LikeResponse> postLikes;

    public PostResponse(Post entity, List<LikeResponse> likes) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUsername();
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.createdAt = entity.getCreatedAt();
        this.postLikes = likes;
    }
}
