package org.steffun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.steffun.model.Role;
import org.steffun.model.User;
import org.steffun.service.RoleService;
import org.steffun.service.UserService;

import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping(value = "")
    public String indexPage(ModelMap model) {
        model.addAttribute("listUsers", userService.getAllUsers());
        return "index";
    }

    @GetMapping(value = "/new")
    public String newUser(@ModelAttribute("user") User user, ModelMap model) {
        model.addAttribute("listRoles", roleService.getListRole());
        return "new";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(value = "username") String username,
                         @RequestParam(value = "password") String password,
                         @RequestParam(value = "listRoles") String[] roles) {
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(roleService.getRoleSet(roles));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/{id}/edit")
    public String edit(Model model, @PathVariable(value = "id") long id, ModelMap modelMap) {
        modelMap.addAttribute("listRoles", roleService.getListRole());
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping(value = "/{id}")
    public String update(@ModelAttribute User user,
                         @PathVariable(value = "id") long id,
                         @RequestParam(value = "password") String password,
                         @RequestParam(value = "listRoles") String[] roles) {
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roleService.getRoleSet(roles));
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/{id}")
    public String removeUserById(@PathVariable(value = "id") long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }

}
