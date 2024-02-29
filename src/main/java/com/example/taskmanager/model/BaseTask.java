package com.example.taskmanager.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tasks")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "task_type", discriminatorType = DiscriminatorType.STRING)
public abstract class BaseTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_type", insertable = false, updatable = false)
    private String type;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date creationDate;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "task_executors",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "executor_id"))
    private List<Person> executors = new ArrayList<>();

    public BaseTask () {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Person> getExecutors() {
        return executors;
    }

    public void addExecutors(Person executor) {
        this.executors.add(executor);
    }

    public static abstract class TaskBuilder<T extends BaseTask, B extends TaskBuilder<T, B>> {
        protected T task;

        public TaskBuilder() {
            task = createTask();
        }

        protected abstract T createTask();

        public B name(String name) {
            task.setName(name);
            return self();
        }

        public B description(String description) {
            task.setDescription(description);
            return self();
        }

        public B status(TaskStatus status) {
            task.setStatus(status);
            return self();
        }

        public B creationDate(Date creationDate) {
            task.setCreationDate(creationDate);
            return self();
        }

        public B addExecutors(List<Person> executors) {
            for (Person executor : executors) {
                task.addExecutors(executor);
            }
            return self();
        }

        protected abstract B self();

        public abstract T build();
    }
}
