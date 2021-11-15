package org.steffun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.steffun.model.User;
import org.steffun.service.RoleService;
import org.steffun.service.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "")
    public String indexPage(ModelMap model) {
        model.addAttribute("listUsers", userService.getAllUsers());
        return "index";
    }

    @GetMapping(value = "/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(value = "username") String username,
                         @RequestParam(value = "password") String password,
                         @RequestParam(value = "roles") String[] roles) {
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(roleService.getRoleSet(roles));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/{id}/edit")
    public String edit(Model model, @PathVariable(value = "id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping(value = "/{id}")
    public String update(@ModelAttribute User user, @PathVariable(value = "id") long id) {
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/{id}")
    public String removeUserById(@PathVariable(value = "id") long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }

}
