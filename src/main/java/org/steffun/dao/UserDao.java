package org.steffun.dao;

import org.steffun.model.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);

    User show(long id);

    void update(User user, long id);

    void removeUserById(long id);

    List<User> listUsers();
}
