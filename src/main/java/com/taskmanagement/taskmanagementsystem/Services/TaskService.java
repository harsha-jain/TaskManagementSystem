package com.taskmanagement.taskmanagementsystem.Services;

import com.taskmanagement.taskmanagementsystem.Models.Task;
import com.taskmanagement.taskmanagementsystem.Models.Taskstatus;
import com.taskmanagement.taskmanagementsystem.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public List<Task> filterTasks(Taskstatus status, String priority, Date dueDate) {
        if (status != null) {
            return taskRepository.findByStatus(status);
        }
        if (priority != null) {
            return taskRepository.findByPriority(priority);
        }
        if (dueDate != null) {
            return taskRepository.findByDueDate(dueDate);
        }
        return taskRepository.findAll();
    }

    public List<Task> searchTasks(String keyword) {
        return taskRepository.findByTitleContainingOrDescriptionContaining(keyword, keyword);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
    }

}
