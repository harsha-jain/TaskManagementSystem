package com.taskmanagement.taskmanagementsystem.Controllers;

import com.taskmanagement.taskmanagementsystem.Models.Task;
import com.taskmanagement.taskmanagementsystem.Models.Taskstatus;
import com.taskmanagement.taskmanagementsystem.Services.TaskService;
import com.taskmanagement.taskmanagementsystem.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        task.setUser(userService.findByUsername(userDetails.getUsername()));
        task.setCreatedAt(new Date());
        task.setUpdatedAt(new Date());
        return taskService.saveTask(task);
    }

    @GetMapping
    public List<Task> getTasks() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return taskService.getAllTasks(userService.findByUsername(userDetails.getUsername()).getId());
    }

    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        Task task = taskService.getTaskById(taskId);
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        task.setPriority(updatedTask.getPriority());
        task.setDueDate(updatedTask.getDueDate());
        task.setUpdatedAt(new Date());
        return taskService.saveTask(task);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }

    @GetMapping("/filter")
    public List<Task> filterTasks(@RequestParam(required = false) Taskstatus status,
                                  @RequestParam(required = false) String priority,
                                  @RequestParam(required = false) Date dueDate) {
        return taskService.filterTasks(status, priority, dueDate);
    }

    @GetMapping("/search")
    public List<Task> searchTasks(@RequestParam String keyword) {
        return taskService.searchTasks(keyword);
    }
}
