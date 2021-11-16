package org.steffun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.steffun.dao.UserDao;
import org.steffun.model.Role;
import org.steffun.model.User;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Transactional
    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getByName(String username) {
        return userDao.getByName(username);
    }

    @Override
    public Set<Role> getUserRoles(User user) {
        return userDao.getUserRoles(user);
    }
}
