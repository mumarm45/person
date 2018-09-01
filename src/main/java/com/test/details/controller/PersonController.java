package com.test.details.controller;

import com.test.details.exceptions.NotFoundException;
import com.test.details.model.Person;
import com.test.details.model.PersonObject;
import com.test.details.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mumarm45 on 30/08/2018.
 */
@RestController
@RequestMapping("/rest/person/")
@Api(basePath = "/rest/person/", value = "Persons", description = "Operations with Persons", produces = "application/json")
public class PersonController {


    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create new Person", notes = "Creates new Person")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Fields are with validation errors"),
            @ApiResponse(code = 201, message = "")})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Person> save(@RequestBody Person person) {
        Person newPerson = personService.create(person);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<PersonObject> list() {
        return new ResponseEntity<>(personService.list(), HttpStatus.OK);
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id) throws NotFoundException {
        personService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Person person) {
        personService.update(person);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
