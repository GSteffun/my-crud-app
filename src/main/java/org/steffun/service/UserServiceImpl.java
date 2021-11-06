package org.steffun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steffun.dao.UserDao;

@Service
public class UserServiceImpl implements  UserService {

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
    public void setUserName(String name) {
        userDao.setUserName(name);
    }

    @Override
    public void setUserLastName(String lastName) {
        userDao.setUserLastName(lastName);
    }

    @Override
    public void setUserAge(int age) {
        userDao.setUserAge(age);
    }
}
