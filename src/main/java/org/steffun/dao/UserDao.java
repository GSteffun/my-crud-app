package org.steffun.dao;

import org.steffun.model.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);

    User getUserById(long id);

    void update(User user);

    void removeUserById(long id);

    List<User> getAllUsers();

    User getByName(String username);
}
