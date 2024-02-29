package com.example.taskmanager.model;

import java.util.ArrayList;
import java.util.List;

public class TaskRequest {
    private String type;
    private String name;
    private String description;
    private TaskStatus status;
    private List<Person> executors = new ArrayList<>();

    public TaskRequest () {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public List<Person> getExecutors() {
        return executors;
    }

    public void setExecutors(List<Person> executors) {
        this.executors = executors;
    }
}
