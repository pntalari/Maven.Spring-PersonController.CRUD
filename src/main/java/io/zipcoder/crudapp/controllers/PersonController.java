package io.zipcoder.crudapp.controllers;

import io.zipcoder.crudapp.models.Person;
import io.zipcoder.crudapp.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping
public class PersonController {
    private final Logger LOG = LoggerFactory.getLogger(PersonController.class);

    private PersonService personService;


    public PersonController(PersonService service) {
        this.personService = service;
    }

    @GetMapping("/people/")
    public ResponseEntity<Iterable<Person>> findAll() {
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> findOne(@PathVariable Integer id) {
        if (personService.findOne(id) != null) {
            return new ResponseEntity<>(personService.findOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(personService.findOne(id), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/people/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        LOG.info("Creating new Person: {}", person);
        // Iterable<Person> personList = personService.findAll();
        return new ResponseEntity<Person>(personService.create(person), HttpStatus.CREATED);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> update(@PathVariable Integer id, @RequestBody Person person) {
        Person currentPerson = personService.findOne(id);

        if (currentPerson == null) {
            LOG.info("Unable to update, Person with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentPerson.setFirstName(person.getFirstName());
        currentPerson.setLastName(person.getLastName());

        return new ResponseEntity<>(currentPerson, HttpStatus.OK);
    }

}
