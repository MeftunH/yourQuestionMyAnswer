package com.sha.yourquestionmyanswer.services;

import com.sha.yourquestionmyanswer.entities.Like;
import com.sha.yourquestionmyanswer.entities.Post;
import com.sha.yourquestionmyanswer.entities.User;
import com.sha.yourquestionmyanswer.repos.LikeRepository;
import com.sha.yourquestionmyanswer.requests.CommentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserService userService;
    private final PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Like> getAllLikes(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return likeRepository.findByPostId(postId.get());
        } else {
            return likeRepository.findAll();
        }
    }


    public Like findById(Long id) {
        return likeRepository.findById(id).orElse(null);
    }

    public Like create(Like like, CommentCreateRequest request) {
        User user = userService.findById(request.getUserId());
        Post post = postService.findById(request.getPostId());
        if (!(user == null || post == null)) {
            Like toSave = new Like();
            toSave.setId(request.getId());
            toSave.setUser(user);
            toSave.setPost(post);
            return likeRepository.save(toSave);
        }
        return null;
    }

    public void deleteById(Long id) {
        likeRepository.deleteById(id);
    }
}
