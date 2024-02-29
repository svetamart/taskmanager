package com.example.taskmanager.service;

import com.example.taskmanager.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class TaskServiceFacadeImpl implements TaskServiceFacade{

    private final TaskService taskService;
    private final PersonService personService;

    private final MyPersonFactory personFactory;
    private final MyTaskFactory taskFactory;


    @Autowired
    public TaskServiceFacadeImpl(PersonService personService, TaskService taskService,
                                 MyPersonFactory personFactory, MyTaskFactory taskFactory) {
        this.personService = personService;
        this.taskService = taskService;
        this.personFactory = personFactory;
        this.taskFactory = taskFactory;
    }

    @Override
    public List<BaseTask> getAllTasks() {
        return taskService.getAllTasks();
    }

    @Override
    public BaseTask getTaskById(Long id) {
        return taskService.getTaskById(id);
    }

    @Override
    public void addTask(TaskRequest task) {
        BaseTask newTask = taskFactory.createTask(task);
        taskService.addTask(newTask);
    }

    @Override
    public void generateTask() {
        List<String> taskTypes = Arrays.asList("Regular", "Urgent");
        Person newExecutor = personFactory.createPerson();
        personService.addExecutor(newExecutor);

        Random random = new Random();
        int randomIndex = random.nextInt(taskTypes.size());
        String randomTaskType = taskTypes.get(randomIndex);

        BaseTask newTask = taskFactory.generateTask(randomTaskType);
        newTask.addExecutors(newExecutor);
        taskService.addTask(newTask);
    }

    @Override
    public void updateTaskStatus(Long id, TaskStatus status) {
        taskService.updateTaskStatus(id, status);
    }

    @Override
    public void deleteTask(Long id) {
        taskService.deleteTask(id);
    }

    @Override
    public List<BaseTask> getTasksByStatus(TaskStatus status) {
        return taskService.getTasksByStatus(status);
    }

    @Override
    public void assignExecutor(Long id, Long executorId) {
        BaseTask existingTask = taskService.getTaskById(id);
        Person executor = personService.getExecutorById(executorId);
        if (!existingTask.getExecutors().contains(executor)) {
            existingTask.addExecutors(executor);
            taskService.saveTask(existingTask);
        } else {
            throw new IllegalArgumentException("This executor is already assigned to the task.");
        }
    }

    @Override
    public List<Person> getAllExecutors() {
        return personService.getAllExecutors();
    }

    @Override
    public Person getExecutorById(Long id) {
        return personService.getExecutorById(id);
    }

    @Override
    public void addExecutor(Person person) {
        personService.addExecutor(person);
    }

    @Override
    public Person getExecutorByName(String name) {
        return personService.getExecutorByName(name);
    }
}
