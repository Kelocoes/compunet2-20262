package com.example.teacher.controller.mvc;

import java.sql.Timestamp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.teacher.model.User;
import com.example.teacher.service.IRoleService;
import com.example.teacher.service.IUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequestMapping("/mvc/users")
@Slf4j
@RequiredArgsConstructor
public class UserMVCController {

    private final IUserService userService;
    private final IRoleService roleService;


    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAll());
        return "users/add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user, @RequestParam(required = false) Integer roleId) {
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userService.create(user, roleId);
        return "redirect:/mvc/users";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Integer id, Model model) {
        User user = userService.findById(id).orElse(null);
        model.addAttribute("actualUser", user);
        model.addAttribute("roles", roleService.findAll());
        return "users/edit";
    }

    @PutMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable Integer id, @ModelAttribute User user,
            @RequestParam(required = false) Integer roleId) {
        user.setId(id);
        userService.update(id, user, roleId);
        return "redirect:/mvc/users";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteById(id);
        return "redirect:/mvc/users";
    }

}
