package com.hannalata.controller;

import com.hannalata.dao.UserDAO;
import com.hannalata.model.User;
import com.hannalata.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @MockBean
    UserService userService;

    @MockBean
    UserDAO userDAO;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void save() throws URISyntaxException {
        User user = new User();
        RequestEntity<User> request = new RequestEntity<>(user, HttpMethod.POST, new URI("/user"));

        when(userService.save(any(User.class))).thenReturn(user);
        ResponseEntity<User> response = testRestTemplate.exchange(request, User.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    void update() throws URISyntaxException {
        User user = new User();
        RequestEntity<User> request = new RequestEntity<>(user, HttpMethod.PUT, new URI("/user"));

        when(userService.update(any(User.class))).thenReturn(user);
        ResponseEntity<User> response = testRestTemplate.exchange(request, User.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(userService, times(1)).update(any(User.class));
    }

    @Test
    void getByLoginAndPasswordNegativeCase() throws URISyntaxException {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("login", "myhailovna");
        parameters.put("password", "monolit7");

        RequestEntity request = new RequestEntity(parameters, HttpMethod.POST, new URI("/user/auth"));

        when(userService.getByLoginAndPassword("myhailovna", "monolit7")).thenReturn(null);
        ResponseEntity response = testRestTemplate.exchange("/user/auth", HttpMethod.POST, request, User.class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertTrue(response.getBody() == null);

        verify(userService, times(1)).getByLoginAndPassword("myhailovna", "monolit7");
    }

    @Test
    void getUserById() throws URISyntaxException {
        User user = new User();
        RequestEntity<User> request = new RequestEntity<>(user, HttpMethod.GET, new URI("/user/1515"));

        when(userService.getById(anyInt())).thenReturn(user);
        ResponseEntity<User> response = testRestTemplate.exchange(request, User.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(userService, times(1)).getById(anyInt());
    }

    @Test
    void getAllUsers() {
        User user1 = new User();
        User user2 = new User();

        List<User> users = Arrays.asList(user1, user2);
        RequestEntity request = new RequestEntity(users, HttpMethod.GET, URI.create("/user"));

        when(userService.getAll()).thenReturn(users);
        ResponseEntity<List> response = testRestTemplate.exchange(request, List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());

        verify(userService, times(1)).getAll();
    }

    @Test
    void delete() {
        User user = new User();
        RequestEntity<User> request = new RequestEntity<>(user, HttpMethod.DELETE, URI.create("/user"));

        doNothing().when(userService).delete(any(User.class));
        ResponseEntity<User> response = testRestTemplate.exchange(request, User.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(userService, times(1)).delete(any(User.class));
    }

    @Test
    void deleteById() {
        User user = new User();
        RequestEntity<User> request = new RequestEntity<>(user, HttpMethod.DELETE, URI.create("/user/1515"));

        doNothing().when(userService).delete(anyInt());
        ResponseEntity<User> response = testRestTemplate.exchange(request, User.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(userService, times(1)).delete(anyInt());
    }
}