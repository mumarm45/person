package com.test.details.service;

import com.test.details.exceptions.NotFoundException;
import com.test.details.model.Person;
import com.test.details.model.PersonObject;
import com.test.details.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 * Created by mumarm45 on 30/08/2018.
 */
@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;
    @Value("${message.person.notfound}")
    private String errorMessage;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person create(Person person) {
        return personRepository.save(person);
    }

    @Override
    public PersonObject list() {
        return new PersonObject(personRepository.findAll());
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        try {
            personRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException(errorMessage);
        }

    }

    @Override
    public Person update(Person person) {
        return personRepository.save(person);
    }

}
