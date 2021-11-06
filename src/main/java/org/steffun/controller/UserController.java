package org.steffun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.steffun.service.UserService;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String indexPage(ModelMap model) {
        model.addAttribute("listUsers", userService.listUsers());
        return "index";
    }

    @GetMapping(value = "/saveUser")
    public String saveUser(@RequestParam(value = "name") String name,
                           @RequestParam(value = "lastName") String lastName,
                           @RequestParam(value = "age") int age,
                           Model model) {
        userService.saveUser(name, lastName, age);
        model.addAttribute("user", "User create successful, name: " + name + ", lastname: " + lastName + ", age: " + age);
        return "usersaved";
    }

    @GetMapping(value = "/removeUser")
    public String removeUserById(@RequestParam(value = "id") long id, Model model) {
        userService.removeUserById(id);
        model.addAttribute("user", "User remove successful, id: " + id);
        return "userremoved";
    }

    @GetMapping(value = "setUserName")
    public String setUserName(@RequestParam(value = "id") long id,
                              @RequestParam(value = "name") String name,
                              Model model) {
        userService.setUserName(id, name);
        model.addAttribute("userName", "User name changed successful, new name: " + name);
        return "usersetname";
    }

    @GetMapping(value = "setUserLastName")
    public String setUserLastName(@RequestParam(value = "id") long id,
                                  @RequestParam(value = "lastName") String lastName,
                                  Model model) {
        userService.setUserLastName(id, lastName);
        model.addAttribute("userLastName", "User last name changed successful, new last name: " + lastName);
        return "usersetlastname";
    }

    @GetMapping(value = "setUserAge")
    public String setUserAge(@RequestParam(value = "id") long id,
                             @RequestParam(value = "age") int age,
                             Model model) {
        userService.setUserAge(id, age);
        model.addAttribute("userAge", "User age changed successful, new last name: " + age);
        return "usersetage";
    }
}
