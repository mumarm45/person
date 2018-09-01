package com.test.details.model;

/**
 * Created by mumarm45 on 31/08/2018.
 */
public class PersonObject {

   private Iterable<Person> person;

    public PersonObject(Iterable<Person> person) {
        this.person = person;
    }

    public Iterable<Person> getPerson() {
        return person;
    }

    public void setPerson(Iterable<Person> person) {
        this.person = person;
    }
}
