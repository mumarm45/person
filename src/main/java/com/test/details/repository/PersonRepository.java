package com.test.details.repository;

import com.test.details.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mumarm45 on 30/08/2018.
 */
@Repository
public interface PersonRepository extends CrudRepository<Person,Long>{
}
