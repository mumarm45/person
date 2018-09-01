package com.test.details.service;

import com.test.details.repository.PersonRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * Created by mumarm45 on 01/09/2018.
 */
public class PersonServiceImplTest {


    @MockBean
    private PersonRepository personRepository;
    @InjectMocks
    private PersonServiceImpl PersonServiceImpl;

    @Test
    public void create() throws Exception {
    }

    @Test
    public void list() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void getOne() throws Exception {
    }

}