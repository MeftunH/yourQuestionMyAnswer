package com.sha.yourquestionmyanswer.repos;

import com.sha.yourquestionmyanswer.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}