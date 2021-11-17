package org.steffun.service;

import org.steffun.model.Role;
import org.steffun.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void saveUser(User user, Set<Role> roles);

    User getUserById(long id);

    void update(User user, Set<Role> roles);

    void removeUserById(long id);

    List<User> getAllUsers();

    User getByName(String username);

    Set<Role> getUserRoles(User user);

}
