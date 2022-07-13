package com.sha.yourquestionmyanswer.services;

import com.sha.yourquestionmyanswer.entities.Post;
import com.sha.yourquestionmyanswer.entities.User;
import com.sha.yourquestionmyanswer.repos.PostRepository;
import com.sha.yourquestionmyanswer.requests.PostCreateRequest;
import com.sha.yourquestionmyanswer.requests.PostUpdateRequest;
import com.sha.yourquestionmyanswer.response.PostResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<PostResponse> getPost(Optional<Long> userId) {
        List<Post> posts;
        if (userId.isPresent()) {
             posts = findAllByUserId(userId.get());
        }
        else{
            posts = findAll();
            return posts.stream().map(p-> new PostResponse(p)).collect(Collectors.toList());
        }
        return null;
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
       toSave.setCreatedAt(LocalDateTime.now());
       return postRepository.save(toSave);
    }

    public Post updateById(Long id, PostUpdateRequest request) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            Post toUpdate = post.get();
            toUpdate.setText(request.getText());
            toUpdate.setTitle(request.getTitle());
            return postRepository.save(toUpdate);
        }
        return null;
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
}
