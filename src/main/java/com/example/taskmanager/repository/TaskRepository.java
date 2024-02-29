package com.example.taskmanager.repository;

import com.example.taskmanager.model.BaseTask;
import com.example.taskmanager.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<BaseTask, Long> {
    List<BaseTask> findByStatus(TaskStatus status);
}
