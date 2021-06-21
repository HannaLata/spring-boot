package com.hannalata.dao;

import com.hannalata.Application;
import com.hannalata.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(Application.class)
class UserDAOTest {

    @Autowired
    UserDAO userDAO;

    @Test
    void getAllBySomeFilters() {
        User user = new User("testLogin", "testPassword", "testFirstName", "testLastName");
        User savedUser = userDAO.save(user);
        List<User> foundUsers = userDAO.getAllBySomeFilters("testFirstName", "testLastName");


        assertNotNull(foundUsers);
        assertTrue(!foundUsers.isEmpty());
        assertTrue(foundUsers.size() == 1);
        assertEquals(foundUsers.get(0).getFirstName(), savedUser.getFirstName());

    }
}