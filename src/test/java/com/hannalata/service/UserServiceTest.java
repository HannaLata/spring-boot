package com.hannalata.service;

import com.hannalata.Application;
import com.hannalata.dao.UserDAO;
import com.hannalata.model.User;
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

    @Test
    void save() {

        User user = new User(1,"myhailovna", "monolit7",
                "Hanna", "Lata");
        when(userDAO.save(any(User.class))).thenReturn(user);
        User savedUser = userDAO.save(user);
        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());

        verify(userDAO, times(1)).save(any(User.class));
    }


    @Test
    void getById() {
        User user = new User(1, "myhailovna", "monolit7",
                "Hanna", "Lata");
        when(userDAO.getById(anyInt())).thenReturn(user);
        userService.getById(user.getId());
        assertNotNull(user.getId());
        assertEquals(1, user.getId());
    }


    @Test
    void getByLoginAndPassword() {
        User user = new User("myhailovna", "monolit7",
                "Hanna", "Lata");

        when(userDAO.getFirstByLoginAndPassword(anyString(), anyString())).thenReturn(user);

        userService.getByLoginAndPassword("myhailovna", "monolit7");
        assertEquals("Hanna", user.getFirstName());

        verify(userDAO, times(1)).getFirstByLoginAndPassword(anyString(), anyString());
    }

    @Test
    void delete() {
        User user = new User("myhailovna", "monolit7",
                "Hanna", "Lata");

        doNothing().when(userDAO).delete(any(User.class));

        userService.delete(user);


        verify(userDAO, times(1)).delete(any(User.class));

    }
}