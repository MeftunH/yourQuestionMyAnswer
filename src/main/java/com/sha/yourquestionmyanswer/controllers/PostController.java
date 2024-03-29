package com.sha.yourquestionmyanswer.controllers;

import com.sha.yourquestionmyanswer.entities.Post;
import com.sha.yourquestionmyanswer.requests.PostCreateRequest;
import com.sha.yourquestionmyanswer.requests.PostUpdateRequest;
import com.sha.yourquestionmyanswer.response.PostResponse;
import com.sha.yourquestionmyanswer.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId) {
       return postService.getPost(userId);
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        return postService.findById(id);
    }

    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest request) {
        return postService.create(request);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostUpdateRequest request) {
        return postService.updateById(id,request);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deleteById(id);
    }


}
