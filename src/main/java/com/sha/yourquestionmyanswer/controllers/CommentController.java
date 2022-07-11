package com.sha.yourquestionmyanswer.controllers;

import com.sha.yourquestionmyanswer.entities.Comment;
import com.sha.yourquestionmyanswer.requests.CommentCreateRequest;
import com.sha.yourquestionmyanswer.requests.CommentUpdateRequest;
import com.sha.yourquestionmyanswer.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId,
                                        @RequestParam Optional<Long> postId)
    {
        return commentService.getAllComments(userId, postId);
    }

    @GetMapping("/{id}")
    public Comment getComment(@PathVariable Long id) {
        return commentService.findById(id);
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentCreateRequest request) {
        return commentService.create(request);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody CommentUpdateRequest request) {
        return commentService.updateById(id,request);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteById(id);
    }

}
