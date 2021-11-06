package org.steffun.service;

import org.steffun.model.User;

import java.util.List;

public interface UserService {
    void saveUser(String name, String lastName, int age);

    void removeUserById(long id);

    void setUserName(long id, String name);

    void setUserLastName(long id, String lastName);

    void setUserAge(long id, int age);

    List<User> listUsers();
}
