package com.test.details.service;

import com.test.details.exceptions.NotFoundException;
import com.test.details.model.Person;
import com.test.details.model.PersonObject;

/**
 * Created by mumarm45 on 30/08/2018.
 */

public interface PersonService {
    Person create(Person person);

    PersonObject list();

    void delete(Long id) throws NotFoundException;

    Person update(Person person);
}
