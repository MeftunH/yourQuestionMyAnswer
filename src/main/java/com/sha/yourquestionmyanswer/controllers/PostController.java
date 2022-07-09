package com.sha.yourquestionmyanswer.controllers;

import com.sha.yourquestionmyanswer.entities.Post;
import com.sha.yourquestionmyanswer.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId) {
       return postService.getPost(userId);
    }
}
