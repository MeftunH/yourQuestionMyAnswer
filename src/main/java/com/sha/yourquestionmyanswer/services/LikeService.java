package com.sha.yourquestionmyanswer.services;

import com.sha.yourquestionmyanswer.entities.Like;
import com.sha.yourquestionmyanswer.entities.Post;
import com.sha.yourquestionmyanswer.entities.User;
import com.sha.yourquestionmyanswer.repos.LikeRepository;
import com.sha.yourquestionmyanswer.requests.CommentCreateRequest;
import com.sha.yourquestionmyanswer.response.LikeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<LikeResponse> getAllLikes(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if (userId.isPresent() && postId.isPresent()) {
            list =  likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            list = likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            list = likeRepository.findByPostId(postId.get());
        } else {
            list = likeRepository.findAll();
        }
        return list.stream().map(l -> new LikeResponse(l)).collect(Collectors.toList());
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
