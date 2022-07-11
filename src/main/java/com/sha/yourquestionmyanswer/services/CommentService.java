package com.sha.yourquestionmyanswer.services;

import com.sha.yourquestionmyanswer.entities.Comment;
import com.sha.yourquestionmyanswer.entities.Post;
import com.sha.yourquestionmyanswer.entities.User;
import com.sha.yourquestionmyanswer.repos.CommentRepository;
import com.sha.yourquestionmyanswer.requests.CommentCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    CommentRepository commentRepository;
    PostService postService;
    UserService userService;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments(@RequestParam Optional<Long> userId,
                                        @RequestParam Optional<Long> postId)
    {
        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        } else {
            return commentRepository.findAll();
        }
    }

    public Comment findById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public Comment create(CommentCreateRequest request) {
        User user = userService.findById(request.getUserId());
        Post post = postService.findById(request.getPostId());
        if (!(user == null || post == null)) {
            Comment toSave = new Comment();
            toSave.setId(request.getId());
            toSave.setText(request.getText());
            toSave.setUser(user);
            toSave.setPost(post);
            return commentRepository.save(toSave);
        }
        return null;
    }
}

