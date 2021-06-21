package com.hannalata.dao;

import com.hannalata.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

    User getFirstByLoginAndPassword(String login, String password);

    @Query(nativeQuery = true,
            value = "SELECT * FROM users WHERE first_name=:firstName AND last_name=:lastName")
    List<User> getAllBySomeFilters(String firstName, String lastName);

    User getFirstByLogin(String login);
}
