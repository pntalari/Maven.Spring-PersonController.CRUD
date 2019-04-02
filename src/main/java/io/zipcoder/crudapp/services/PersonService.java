package io.zipcoder.crudapp.services;

import io.zipcoder.crudapp.models.Person;
import io.zipcoder.crudapp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PersonService {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private static Integer id;

    @Autowired
    private PersonRepository personRepository;

    private static Iterable<Person> personIterable = new ArrayList<>(
            Arrays.asList(
                    new Person("First1","Last1"),
                    new Person("First2","Last2"),
                    new Person("First3","Last3"),
                    new Person("First4","Last4")
    ));

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
        p.setId(id);
        return personRepository.save(p);
    }

    public Person update(Integer id, Person p){
       return personRepository.save(p);
    }

    public Boolean delete(Integer id){
        personRepository.delete(id);
        return true;
    }



//    private List<Person> personList;
//    private Person personObj;
//
//    private Person createPerson(Person p) {
//        if (p != null) {
//            personList.add(new Person(p.getFirstName(), p.getLastName()));
//            return p;
//        } else throw new IllegalArgumentException();
//    }
//
//    private Person getPerson(int id) {
//
//        return this.personList.get(id);
//    }
//
//    private List<Person> getPersonList() {
//        return this.personList;
//    }
//
//    private Person updatePerson(Person p) {
//        if (p != null && this.personList.contains(p)) {
//            int index = this.personList.indexOf(p);
//            this.personList.get(index).setFirstName(p.getFirstName());
//            this.personList.get(index).setLastName(p.getLastName());
//            return p;
//        } else throw new IllegalArgumentException();
//    }
//
//    private void deletePerson(int id) {
//
//        if (this.personList.contains(personList.get(id))) {
//            this.personList.remove(id);
//        } else throw new IllegalArgumentException();
//    }

}
