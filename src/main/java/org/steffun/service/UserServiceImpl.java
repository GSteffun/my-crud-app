package org.steffun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steffun.dao.UserDao;
import org.steffun.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void saveUser(String name, String lastName, int age) {
        userDao.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    @Override
    public void setUserName(long id, String name) {
        userDao.setUserName(id, name);
    }

    @Override
    public void setUserLastName(long id, String lastName) {
        userDao.setUserLastName(id, lastName);
    }

    @Override
    public void setUserAge(long id, int age) {
        userDao.setUserAge(id, age);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }
}
