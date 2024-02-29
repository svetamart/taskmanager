package com.example.taskmanager.model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MyPersonFactory implements PersonFactory{

    private final List<String> availableFirstNames = Arrays.asList("John", "Mary", "Peter", "Anna", "Alexander", "Catherine");
    private final List<String> availableLastNames = Arrays.asList("Smith", "Johnson", "Williams", "Jones", "Brown", "Davis");
    @Override
    public Person createPerson() {
        Random random = new Random();
        String randomFirstName = availableFirstNames.get(random.nextInt(availableFirstNames.size()));
        String randomLastName = availableLastNames.get(random.nextInt(availableLastNames.size()));

        return new Person(randomFirstName + " " + randomLastName);
    }
}
