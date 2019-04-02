package io.zipcoder.crudapp.services;

import io.zipcoder.crudapp.models.Person;
import io.zipcoder.crudapp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonService(PersonRepository repository){
        this.personRepository = repository;
    }

    public Iterable<Person> findAll(){
        return personRepository.findAll();
    }

    public Person findOne(Integer id){
        return personRepository.findOne(id);
    }

    public Person create(Person p){
        return personRepository.save(p);
    }

    public Person update(Integer id, Person p){
        Person originalPerson = personRepository.findOne(id);
        originalPerson.setLastName(p.getLastName());
        originalPerson.setFirstName(p.getFirstName());

       return personRepository.save(originalPerson);
    }

    public Boolean delete(Integer id){
        personRepository.delete(id);
        return true;
    }
}
