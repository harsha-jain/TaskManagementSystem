package com.taskmanagement.taskmanagementsystem.Repository;

import com.taskmanagement.taskmanagementsystem.Models.Task;
import com.taskmanagement.taskmanagementsystem.Models.Taskstatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByUserId(Long userId);
    List<Task> findByStatus(Taskstatus status);
    List<Task> findByPriority(String priority);
    List<Task> findByDueDate(Date dueDate);
    List<Task> findByTitleContainingOrDescriptionContaining(String title, String description);
}
