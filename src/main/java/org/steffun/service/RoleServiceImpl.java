package org.steffun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.steffun.dao.RoleDao;
import org.steffun.model.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public void add(Role role) {
       roleDao.add(role);
    }

    @Transactional
    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public Role getById(int id) {
        return roleDao.getById(id);
    }

    @Transactional
    @Override
    public void removeById(int id) {
        roleDao.removeById(id);
    }

    @Override
    public List<Role> getListRole() {
        return roleDao.getListRole();
    }

    @Override
    public Role getByName(String roleName) {
        return roleDao.getByName(roleName);
    }

    @Transactional
    @Override
    public Set<Role> getRoleSet(String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for(String role: roles) {
            roleSet.add(roleDao.getByName(role));
        }
        return roleSet;
    }
}
