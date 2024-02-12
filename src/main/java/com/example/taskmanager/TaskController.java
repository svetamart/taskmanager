package com.example.taskmanager;

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

    @Autowired
    private TaskService taskService;

    @Autowired
    private ExecutorService executorService;


    @Operation(summary = "Get all tasks", description = "Get a list of all tasks")
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }


    @Operation(summary = "Get task by ID", description = "Get a task by its ID")
    @GetMapping("/tasks/id/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable @Parameter(description = "Task ID", example = "1")  Long id) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Add a new task", description = "Add a new task with optional executors")
    @PostMapping("/tasks")
    public ResponseEntity<String> addTask(@RequestBody Task task) {
        List<Person> executors = task.getExecutors();
        if (executors != null && !executors.isEmpty()) {
            for (Person executor : executors) {
                String executorName = executor.getName();
                if (executorName != null) {
                    Person existingExecutor = executorService.getExecutorByName(executorName);
                    if (existingExecutor == null) {
                        executorService.addExecutor(executor);
                    }
                }
            }
        }
        taskService.addTask(task);
        return ResponseEntity.ok("Task successfully added");
    }

    @PutMapping("/tasks/{id}/{status}")
    public ResponseEntity<String> updateTaskStatus(@PathVariable Long id, @PathVariable TaskStatus status) {
        taskService.updateTaskStatus(id, status);
        return ResponseEntity.ok("Task status updated");
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted");
    }

    @GetMapping("/tasks/status/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable TaskStatus status) {
        List<Task> tasks = taskService.getTasksByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/executors")
    public ResponseEntity<List<Person>> getAllExecutors() {
        List<Person> executors = executorService.getAllExecutors();
        return ResponseEntity.ok(executors);
    }

    @GetMapping("/executors/id/{id}")
    public ResponseEntity<Person> getExecutorById(@PathVariable Long id) {
        Person executor = executorService.getExecutorById(id);
        if (executor != null) {
            return ResponseEntity.ok(executor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/executors")
    public ResponseEntity<String> addExecutor(@RequestBody Person person) {
        executorService.addExecutor(person);
        return ResponseEntity.ok("Executor successfully added");
    }

    @GetMapping("/executors/name/{name}")
    public ResponseEntity<Person> getExecutorByName(@PathVariable String name) {
        Person executor = executorService.getExecutorByName(name);
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
        taskService.assignExecutor(id, executorId);
    }

}
