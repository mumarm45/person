package com.test.details.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.details.exceptions.NotFoundException;
import com.test.details.model.Person;
import com.test.details.model.PersonObject;
import com.test.details.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by mumarm45 on 31/08/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonControllerTest {
    @MockBean
    private PersonService personService;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @InjectMocks
    private PersonController personController;

    private List<Person> persons = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void list() throws Exception {
        Person person = new Person();
        person.setAge(27);
        person.setFavourite_color("color");
        person.setFirst_name("Omar");
        person.setLast_name("Muneer");
        persons.add(person);
        PersonObject personObject = new PersonObject(persons);

        when(personService.list()).thenReturn(personObject);

        this.mockMvc.perform(get("/rest/person/list"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.person", hasSize(1)))
                .andExpect(jsonPath("$.person[0].first_name", is("Omar")))
                .andExpect(jsonPath("$.person[0].last_name", is("Muneer")))
                .andExpect(jsonPath("$.person[0].age", is(27)));

        verify(personService, times(1)).list();
        verifyNoMoreInteractions(personService);
    }

    @Test
    public void save() throws Exception {
        Person person = new Person();
        person.setAge(26);
        person.setFavourite_color("white");
        person.setFirst_name("Jhon");
        person.setLast_name("AB");

        when(personService.create(person)).thenReturn(person);

        this.mockMvc.perform(post("/rest/person/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(person)))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteSuccessPerson() throws Exception {
        doNothing().when(personService).delete(any());
        this.mockMvc.perform(delete("/rest/person/delete/{id}",0)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    public void deleteNotFoundPerson() throws Exception {
        doThrow(new NotFoundException("Person not found")).when(personService).delete(any());
        this.mockMvc.perform(delete("/rest/person/delete/{id}",0)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }


    @Test
    public void update() throws Exception {
        Person person = new Person();
        person.setAge(27);
        person.setFavourite_color("color");
        person.setFirst_name("Omar");
        person.setLast_name("Muneer");

        when(personService.update(person)).thenReturn(person);

        this.mockMvc.perform(put("/rest/person/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(person)))
                .andExpect(status().isOk());

    }
}