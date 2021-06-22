package com.hannalata.service;

import com.hannalata.dao.UserDAO;
import com.hannalata.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public User getByLogin(String login) {
        return userDAO.getFirstByLogin(login);
    }

    public User getByLoginAndPassword(String login, String password) {
        return userDAO.getFirstByLoginAndPassword(login, password);
    }

    public User getById(Integer id) {
        Optional<User> user = userDAO.findById(id);
        if (user.isEmpty()) {
            return null;
        }
        return user.get();
    }

    public User save(User user) {
        if (user.getId() == null && userDAO.getFirstByLogin(user.getLogin()) == null) {
            return userDAO.save(user);
        }
        return null;
    }

    public User update(User user) {
        if (user.getId() != null && userDAO.findById(user.getId()) != null) {
            return userDAO.save(user);
        }
        return null;
    }

    public List<User> getAll() {
        return userDAO.findAll();
    }

    public void delete(User user) {
        userDAO.delete(user);
    }

    public void delete(Integer id) throws RuntimeException{
        userDAO.deleteById(id);
    }
}