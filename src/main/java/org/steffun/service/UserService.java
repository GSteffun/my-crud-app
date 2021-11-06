package org.steffun.service;

public interface UserService {
    void saveUser(String name, String lastName, int age);
    void removeUserById(long id);
    void setUserName(String name);
    void setUserLastName(String lastName);
    void setUserAge(int age);
}
