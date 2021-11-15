package org.steffun.service;

import org.steffun.model.Role;
import org.steffun.model.User;

import java.util.List;
import java.util.Set;

public interface RoleService {

    void add(Role role);

    void update(Role role);

    Role getById(int id);

    void removeById(int id);

    List<User> getListUser();

    Role getByName(String roleName);

    Set<Role> getRoleSet(String[] roles);
}
