package com.example.taskmanager.service;

import com.example.taskmanager.model.BaseTask;
import com.example.taskmanager.model.Person;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.model.TaskStatus;
import com.fasterxml.jackson.databind.ser.Serializers;
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

    public List<BaseTask> getAllTasks() {
        return taskRepository.findAll();
    }

    public BaseTask getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void addTask(BaseTask task) {
        taskRepository.save(task);
    }

    public void updateTaskStatus(Long id, TaskStatus status) {
        BaseTask task = getTaskById(id);
        if (task != null) {
            task.setStatus(status);
            taskRepository.save(task);
        }
    }

    public void updateTask (Long id, BaseTask task) {
        BaseTask existingTask = getTaskById(id);

        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());
        existingTask.setCreationDate(task.getCreationDate());

        taskRepository.save(existingTask);
    }
    public void saveTask(BaseTask task) {
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<BaseTask> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }
}

