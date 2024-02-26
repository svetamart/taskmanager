package com.example.taskmanager.service;

import com.example.taskmanager.model.Person;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.model.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final PersonService personService;

    @Autowired
    public TaskService(TaskRepository taskRepository, PersonService personService) {
        this.personService = personService;
        this.taskRepository = taskRepository;
    }

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
        Person executor = personService.getExecutorById(executorId);
        existingTask.addExecutors(executor);

        taskRepository.save(existingTask);

    }
}

