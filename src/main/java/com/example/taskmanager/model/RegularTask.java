package com.example.taskmanager.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Regular")
public class RegularTask extends BaseTask {

    public RegularTask () {
    }

    public static class RegularTaskBuilder extends TaskBuilder<RegularTask, RegularTaskBuilder> {

        public RegularTaskBuilder() {
            super();
        }

        @Override
        protected RegularTask createTask() {
            return new RegularTask();
        }

        @Override
        protected RegularTaskBuilder self() {
            return this;
        }

        @Override
        public RegularTask build() {
            return task;
        }
    }

}
