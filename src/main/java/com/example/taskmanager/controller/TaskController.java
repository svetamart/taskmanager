package com.example.taskmanager.controller;

import com.example.taskmanager.model.Person;
import com.example.taskmanager.model.BaseTask;
import com.example.taskmanager.model.TaskRequest;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.service.PersonService;
import com.example.taskmanager.service.TaskService;
import com.example.taskmanager.service.TaskServiceFacadeImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Task Management", description = "API for managing tasks and executors")
public class TaskController {

    private final TaskServiceFacadeImpl taskManager;

    @Autowired
    public TaskController(TaskServiceFacadeImpl taskManager) {
        this.taskManager = taskManager;
    }


    @Operation(summary = "Get all tasks", description = "Get a list of all tasks")
    @GetMapping("/tasks")
    public ResponseEntity<List<BaseTask>> getAllTasks() {
        List<BaseTask> tasks = taskManager.getAllTasks();
        return ResponseEntity.ok(tasks);
    }


    @Operation(summary = "Get task by ID", description = "Get a task by its ID")
    @GetMapping("/tasks/id/{id}")
    public ResponseEntity<BaseTask> getTaskById(@PathVariable @Parameter(description = "Task ID", example = "1")  Long id) {
        BaseTask task = taskManager.getTaskById(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Add a new task", description = "Add a new task with optional executors")
    @PostMapping("/tasks/add")
    public ResponseEntity<String> addTask(@RequestBody TaskRequest task) {
        List<Person> executors = task.getExecutors();
        if (executors != null && !executors.isEmpty()) {
            for (Person executor : executors) {
                String executorName = executor.getName();
                if (executorName != null) {
                    Person existingExecutor = taskManager.getExecutorByName(executorName);
                    if (existingExecutor == null) {
                        taskManager.addExecutor(executor);
                    }
                }
            }
        }
        taskManager.addTask(task);
        return ResponseEntity.ok("Task successfully added");
    }

    @Operation(summary = "Generate a new task", description = "The program will generate a new random task")
    @PostMapping("/tasks/generate")
    public ResponseEntity<String> generateTask() {
        taskManager.generateTask();
        return ResponseEntity.ok("Task successfully generated");
    }

    @PutMapping("/tasks/{id}/{status}")
    public ResponseEntity<String> updateTaskStatus(@PathVariable Long id, @PathVariable TaskStatus status) {
        taskManager.updateTaskStatus(id, status);
        return ResponseEntity.ok("Task status updated");
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskManager.deleteTask(id);
        return ResponseEntity.ok("Task deleted");
    }

    @GetMapping("/tasks/status/{status}")
    public ResponseEntity<List<BaseTask>> getTasksByStatus(@PathVariable TaskStatus status) {
        List<BaseTask> tasks = taskManager.getTasksByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/executors")
    public ResponseEntity<List<Person>> getAllExecutors() {
        List<Person> executors = taskManager.getAllExecutors();
        return ResponseEntity.ok(executors);
    }

    @GetMapping("/executors/id/{id}")
    public ResponseEntity<Person> getExecutorById(@PathVariable Long id) {
        Person executor = taskManager.getExecutorById(id);
        if (executor != null) {
            return ResponseEntity.ok(executor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/executors")
    public ResponseEntity<String> addExecutor(@RequestBody Person person) {
        taskManager.addExecutor(person);
        return ResponseEntity.ok("Executor successfully added");
    }

    @GetMapping("/executors/name/{name}")
    public ResponseEntity<Person> getExecutorByName(@PathVariable String name) {
        Person executor = taskManager.getExecutorByName(name);
        if (executor != null) {
            return ResponseEntity.ok(executor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Assign executor", description = "Assign an executor to a task")
    @PutMapping("/tasks/{id}/executors/{executorId}")
    public void assignExecutorToTask(
            @PathVariable
            @Parameter(description = "Task ID", example = "1")
            Long id,
            @PathVariable
            @Parameter(description = "Executor ID", example = "1")
            Long executorId) {
        taskManager.assignExecutor(id, executorId);
    }

}
