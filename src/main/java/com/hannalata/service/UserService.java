package com.hannalata.service;

import com.hannalata.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getByLoginAndPassword(String login, String password) {
        if(login.equals("myhailovna") && password.equals("monolit7")) {
            return new User(1, "myhailovna", "monolit7", "Hanna", "Lata");
        }
        return null;
    }

    public User getById(Integer id) {
        if(id.equals(1)) {
            return new User(1, "myhailovna", "monolit7", "Hanna", "Lata");
        }
        return null;
    }
}
