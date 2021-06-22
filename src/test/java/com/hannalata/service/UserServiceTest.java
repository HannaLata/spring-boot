package com.hannalata.service;

import com.hannalata.Application;
import com.hannalata.dao.UserDAO;
import com.hannalata.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(Application.class)
class UserServiceTest {


    @MockBean
    UserDAO userDAO;
    
    @Autowired
    UserService userService;

    User user;
    @BeforeEach
    void setUp() {
        user = new User(1, "myhailovna", "monolit7",
                "Hanna", "Lata");

    }

    @Test
    void save() {

        when(userDAO.save(any())).thenReturn(user);
        User savedUser = userDAO.save(user);
        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
    }


    @Test
    void getById() {
        when(userDAO.getById(anyInt())).thenReturn(user);
        userService.getById(user.getId());
        assertNotNull(user.getId());
        assertEquals(1, user.getId());
    }


    @Test
    void getByLoginAndPassword() {

        when(userDAO.getFirstByLoginAndPassword(anyString(), anyString())).thenReturn(user);

        User savedUser = userService.getByLoginAndPassword("myhailovna", "monolit7");
        assertEquals("Hanna", savedUser.getFirstName());

        verify(userDAO, times(1)).getFirstByLoginAndPassword(anyString(), anyString());
    }
}