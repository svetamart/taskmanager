package com.example.taskmanager.service;

import com.example.taskmanager.model.BaseTask;
import com.example.taskmanager.model.Person;
import com.example.taskmanager.model.TaskRequest;
import com.example.taskmanager.model.TaskStatus;

import java.util.Date;
import java.util.List;

public interface TaskServiceFacade {
    List<BaseTask> getAllTasks();
    BaseTask getTaskById(Long id);
    void addTask(TaskRequest task);
    void updateTaskStatus(Long id, TaskStatus status);
    void deleteTask(Long id);
    List<BaseTask> getTasksByStatus(TaskStatus status);
    void assignExecutor(Long id, Long executorId);

    List<Person> getAllExecutors();
    Person getExecutorById(Long id);
    void addExecutor(Person person);
    Person getExecutorByName(String name);
    void generateTask();
}
