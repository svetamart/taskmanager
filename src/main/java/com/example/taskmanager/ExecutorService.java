package com.example.taskmanager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExecutorService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllExecutors() {
        return personRepository.findAll();
    }

    public Person getExecutorById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public void addExecutor(Person person) {
        personRepository.save(person);
    }

    public Person getExecutorByName(String name) {
        return personRepository.findByName(name);
    }
}
