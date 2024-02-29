package com.example.taskmanager.model;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class MyTaskFactory implements TaskFactory {

    @Override
    public BaseTask generateTask(String type) {
        String number = String.valueOf(ThreadLocalRandom.current().nextInt(1, 10001));
        return switch (type.toLowerCase()) {
            case "urgent" -> new UrgentTask.UrgentTaskBuilder()
                    .name("Urgent Task #" + number)
                    .description("You have to do something very urgent!")
                    .status(TaskStatus.NOT_STARTED)
                    .creationDate(new Date())
                    .build();
            case "regular" -> new RegularTask.RegularTaskBuilder()
                    .name("Regular Task #" + number)
                    .description("You have to do something.")
                    .status(TaskStatus.NOT_STARTED)
                    .creationDate(new Date())
                    .build();
            default -> throw new IllegalArgumentException("Unsupported task type: " + type);
        };
    }

    @Override
    public BaseTask createTask(TaskRequest request) {
        String type = request.getType();

        return switch (type.toLowerCase()) {
            case "urgent" -> new UrgentTask.UrgentTaskBuilder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .status(request.getStatus())
                    .creationDate(new Date())
                    .addExecutors(request.getExecutors())
                    .build();
            case "regular" -> new RegularTask.RegularTaskBuilder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .status(request.getStatus())
                    .creationDate(new Date())
                    .addExecutors(request.getExecutors())
                    .build();
            default -> throw new IllegalArgumentException("Unsupported task type: " + type);
        };
    }
}
