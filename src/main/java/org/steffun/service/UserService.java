package org.steffun.service;

import org.steffun.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    User getUserById(long id);

    void update(User user);

    void removeUserById(long id);

    List<User> getAllUsers();
}
