package com.example.taskmanager.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Urgent")
public class UrgentTask extends BaseTask {

    public UrgentTask() {}

    public static class UrgentTaskBuilder extends TaskBuilder<UrgentTask, UrgentTaskBuilder> {

        public UrgentTaskBuilder() {
            super();
        }

        @Override
        protected UrgentTask createTask() {
            return new UrgentTask();
        }

        @Override
        protected UrgentTaskBuilder self() {
            return this;
        }

        @Override
        public UrgentTask build() {
            return task;
        }
    }

}
