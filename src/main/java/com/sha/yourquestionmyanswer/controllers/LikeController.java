package com.sha.yourquestionmyanswer.controllers;

import com.sha.yourquestionmyanswer.entities.Like;
import com.sha.yourquestionmyanswer.requests.CommentCreateRequest;
import com.sha.yourquestionmyanswer.response.LikeResponse;
import com.sha.yourquestionmyanswer.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/likes")
public class LikeController {
    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId,
                                          @RequestParam Optional<Long> postId) {
        return likeService.getAllLikes(userId, postId);
    }

    @GetMapping("/{id}")
    public Like getLike(@PathVariable Long id) {
        return likeService.findById(id);
    }

    @PostMapping
    public Like createLike(@RequestBody Like like, @RequestBody CommentCreateRequest request) {
        return likeService.create(like,request);
    }

    @DeleteMapping("/{id}")
    public void deleteLike(@PathVariable Long id) {
        likeService.deleteById(id);
    }
}
