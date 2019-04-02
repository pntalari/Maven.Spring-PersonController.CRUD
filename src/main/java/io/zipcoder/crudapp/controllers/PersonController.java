package io.zipcoder.crudapp.controllers;

import io.zipcoder.crudapp.models.Person;
import io.zipcoder.crudapp.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
            LOG.info("Found Person with ID: {}",id);
            return new ResponseEntity<>(personService.findOne(id), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/people/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        LOG.info("Creating new Person: {}", person);
        return new ResponseEntity<>(personService.create(person), HttpStatus.CREATED);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> update(@PathVariable Integer id, @RequestBody Person person) {
        Person currentPerson = personService.findOne(id);

        if (currentPerson == null) {
            LOG.info("Unable to update, Person with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        LOG.info("Updating Person: {}",id);

        return new ResponseEntity<>(personService.update(id,person), HttpStatus.OK);
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<Boolean> destroy(@PathVariable Integer id){
        LOG.info("Deleting Person with ID: {}",id);
        return new ResponseEntity<>(personService.delete(id),HttpStatus.OK);
    }

}
