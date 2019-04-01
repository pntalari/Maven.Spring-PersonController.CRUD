package io.zipcoder.crudapp.controllers;

import io.zipcoder.crudapp.models.Person;
import io.zipcoder.crudapp.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PersonController {

    private PersonService personService;
    private List<Person> personList;
    private Person personObj;

    private Person createPerson(Person p) {
        if (p != null) {
            personList.add(new Person(p.getFirstName(), p.getLastName()));
            return p;
        } else throw new IllegalArgumentException();
    }

    private Person getPerson(int id) {

        return this.personList.get(id);
    }

    private List<Person> getPersonList() {
        return this.personList;
    }

    private Person updatePerson(Person p) {
        if (p != null && this.personList.contains(p)) {
            int index = this.personList.indexOf(p);
            this.personList.get(index).setFirstName(p.getFirstName());
            this.personList.get(index).setLastName(p.getLastName());
            return p;
        } else throw new IllegalArgumentException();
    }

    private void deletePerson(int id) {

        if (this.personList.contains(personList.get(id))) {
            this.personList.remove(id);
        } else throw new IllegalArgumentException();
    }


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
        return new ResponseEntity<>(personService.create(person), HttpStatus.CREATED);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> update(@PathVariable Integer id, @RequestBody Person person) {
        return new ResponseEntity<>(personService.update(id, person));
    }


}
