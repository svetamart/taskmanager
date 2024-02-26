package com.example.taskmanager;


import com.example.taskmanager.model.Person;
import com.example.taskmanager.repository.PersonRepository;
import com.example.taskmanager.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllExecutorsTest() {
        Person person = new Person();
        person.setName("John Doe");
        List<Person> expectedExecutors = Collections.singletonList(person);

        when(personRepository.findAll()).thenReturn(expectedExecutors);
        List<Person> actualExecutors = personService.getAllExecutors();

        assertEquals(expectedExecutors, actualExecutors);

    }

    @Test
    public void getExecutorByIdTest() {
        Long id = 1L;
        personService.getExecutorById(id);
        Mockito.verify(personRepository, Mockito.times(1)).findById(id);
    }

    @Test
    public void addExecutorTest() {
        Person person = new Person();
        personService.addExecutor(person);
        Mockito.verify(personRepository, Mockito.times(1)).save(person);
    }

    @Test
    public void getExecutorByNameTest() {
        Person person = new Person();
        person.setName("Jane Doe");
        when(personRepository.findByName("Jane Doe")).thenReturn(person);
        Person foundPerson = personService.getExecutorByName("Jane Doe");
        assertEquals("Jane Doe", foundPerson.getName());
    }
}
