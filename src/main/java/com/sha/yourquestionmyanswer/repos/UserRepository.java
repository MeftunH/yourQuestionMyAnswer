package com.sha.yourquestionmyanswer.repos;

import com.sha.yourquestionmyanswer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
