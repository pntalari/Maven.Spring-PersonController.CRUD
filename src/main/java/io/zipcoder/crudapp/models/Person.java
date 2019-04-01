package io.zipcoder.crudapp.models;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {

    String firstName = "";
    String lastName = "";

    List<Person> personList;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id = 0L;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personList = new ArrayList<>();
    }

    private Person createPerson(Person p) {
        if (p != null) {
            personList.add(new Person(p.firstName, p.lastName));
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
            this.personList.get(index).firstName = p.firstName;
            this.personList.get(index).firstName = p.lastName;
            return p;
        } else throw new IllegalArgumentException();
    }

    private void deletePerson(int id) {

        if (this.personList.contains(personList.get(id))) {
            this.personList.remove(id);
        } else throw new IllegalArgumentException();
    }

        public Long getId () {
            return id;
        }

    }
