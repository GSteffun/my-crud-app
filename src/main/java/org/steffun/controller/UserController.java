package org.steffun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.steffun.model.User;
import org.steffun.service.UserService;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String indexPage(ModelMap model) {
        model.addAttribute("listUsers", userService.listUsers());
        return "users/index";
    }

    @GetMapping(value = "/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(value = "name") String name,
                         @RequestParam(value = "lastName") String lastName,
                         @RequestParam(value = "age") int age) {
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/{id}/edit")
    public String edit(Model model, @PathVariable(value = "id") long id) {
        model.addAttribute("user", userService.show(id));
        return "users/edit";
    }

    @PatchMapping(value = "/{id}")
    public String update(@ModelAttribute(value = "user") User user, @PathVariable(value = "id") long id) {
        userService.update(user, id);
        return "redirect:/users";
    }

    @DeleteMapping(value = "/{id}")
    public String removeUserById(@PathVariable(value = "id") long id) {
        userService.removeUserById(id);
        return "redirect:/users";
    }

}