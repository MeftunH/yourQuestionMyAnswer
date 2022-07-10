package com.sha.yourquestionmyanswer.services;

import com.sha.yourquestionmyanswer.entities.Post;
import com.sha.yourquestionmyanswer.entities.User;
import com.sha.yourquestionmyanswer.repos.PostRepository;
import com.sha.yourquestionmyanswer.requests.PostCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserService userService;
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
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

    public Post create(PostCreateRequest request) {
       User user = userService.findById(request.getUserId());
       if (user == null) {
           return null;
       }
       Post toSave = new Post();
       toSave.setId(request.getId());
       toSave.setText(request.getText());
       toSave.setTitle(request.getTitle());
       toSave.setUser(user);
       return postRepository.save(toSave);
    }

    public Post updateById(Long id) {
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
}
