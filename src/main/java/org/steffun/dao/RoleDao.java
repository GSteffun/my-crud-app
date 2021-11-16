package org.steffun.dao;

import org.steffun.model.Role;

import java.util.List;

public interface RoleDao {

    void add(Role role);

    void update(Role role);

    Role getById(int id);

    void removeById(int id);

    List<Role> getListRole();

    Role getByName(String roleName);

}
