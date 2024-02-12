package com.example.taskmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ExecutorService executorService;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void addTask(Task task) {
        task.setCreationDate(new Date());
        taskRepository.save(task);
    }

    public void updateTaskStatus(Long id, TaskStatus status) {
        Task task = getTaskById(id);
        if (task != null) {
            task.setStatus(status);
            taskRepository.save(task);
        }
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public void assignExecutor(Long id, Long executorId) {
        Task existingTask = taskRepository.findById(id).get();
        Person executor = executorService.getExecutorById(executorId);
        existingTask.addExecutors(executor);

        taskRepository.save(existingTask);

    }
}

