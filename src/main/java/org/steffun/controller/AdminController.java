package org.steffun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.steffun.model.Role;
import org.steffun.model.User;
import org.steffun.service.RoleService;
import org.steffun.service.UserService;

import java.util.HashMap;
import java.util.Map;


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
    public String newUser(@ModelAttribute("user") User user, ModelMap model) {
        model.addAttribute("listRoles", roleService.getListRole());
        return "new";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(value = "listRoles") String[] roles) {
        userService.saveUser(user, roleService.getRoleSet(roles));
        return "redirect:/admin";
    }

    @GetMapping(value = "/{id}/edit")
    public String edit(Model model, @PathVariable(value = "id") long id, ModelMap modelMap) {
        Map<String, Boolean> userRolesMap = new HashMap<>();
        for(Role role : roleService.getListRole()) userRolesMap.put(role.getRole(), false);
        for (Role role : userService.getUserRoles(userService.getUserById(id))) userRolesMap.put(role.getRole(), true);
        modelMap.addAttribute("listRoles", userRolesMap);
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping(value = "/{id}")
    public String update(@ModelAttribute User user,
                         @PathVariable(value = "id") long id,
                         @RequestParam(value = "listRoles") String[] roles) {
        userService.update(user, roleService.getRoleSet(roles));
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/{id}")
    public String removeUserById(@PathVariable(value = "id") long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }

}
