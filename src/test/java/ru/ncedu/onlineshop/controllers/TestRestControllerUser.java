package ru.ncedu.onlineshop.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mortbay.jetty.webapp.WebAppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.ncedu.onlineshop.entities.EntityUser;
import ru.ncedu.onlineshop.services.ServiceUser;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class TestRestControllerUser {

    private MockMvc mockMvc;


    private ServiceUser serviceUser;


    @Test
    public void registration() throws Exception {
        EntityUser first = EntityUser.init()
                .id(1L)
                .login("sasha")
                .password("123")
                .createUser();

//        when()
    }
}
