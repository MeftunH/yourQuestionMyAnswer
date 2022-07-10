package com.sha.yourquestionmyanswer.services;

import com.sha.yourquestionmyanswer.entities.Post;
import com.sha.yourquestionmyanswer.repos.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> findAllByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    public List<Post> getPost(Optional<Long> userId) {
        if (userId.isPresent()) {
            return findAllByUserId(userId.get());
        }
        return findAll();
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post create(Post post) {
        return postRepository.save(post);
    }
}
