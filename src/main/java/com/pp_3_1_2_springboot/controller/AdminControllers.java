package com.pp_3_1_2_springboot.controller;

import com.pp_3_1_2_springboot.model.User;
import com.pp_3_1_2_springboot.service.user.UserService;
import com.pp_3_1_2_springboot.service.role.RoleService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminControllers {

    private final UserService userService;
    private final RoleService roleService;


    public AdminControllers(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAllUser(Model model, @AuthenticationPrincipal User userAuth) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("login", userAuth);
        return "index";
    }

    @GetMapping("userCreate")
    public String userCreate(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "user-create";
    }

    @PostMapping("addUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("deleteUser")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("updateUserForm")
    public String updateUserForm(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "user-update";
    }

    @PostMapping("updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }
}
