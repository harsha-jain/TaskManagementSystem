package com.taskmanagement.taskmanagementsystem.Repository;

import com.taskmanagement.taskmanagementsystem.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
