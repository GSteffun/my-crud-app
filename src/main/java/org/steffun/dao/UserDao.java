package org.steffun.dao;

import org.steffun.model.Role;
import org.steffun.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    void saveUser(User user);

    User getUserById(long id);

    void update(User user);

    void removeUserById(long id);

    List<User> getAllUsers();

    User getByName(String username);

    Set<Role> getUserRoles(User user);
}
