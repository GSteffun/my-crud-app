package org.steffun.dao;

public interface UserDao {
    void saveUser(String name, String lastName, int age);
    void removeUserById(long id);
    void setUserName(String name);
    void setUserLastName(String lastName);
    void setUserAge(int age);
}
