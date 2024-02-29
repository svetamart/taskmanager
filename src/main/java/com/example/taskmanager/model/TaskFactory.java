package com.example.taskmanager.model;

public interface TaskFactory {
    BaseTask generateTask(String type);
    BaseTask createTask(TaskRequest request);
}
