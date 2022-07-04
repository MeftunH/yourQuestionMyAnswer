package com.sha.yourquestionmyanswer.repos;

import com.sha.yourquestionmyanswer.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
