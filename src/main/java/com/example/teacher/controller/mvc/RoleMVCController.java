package com.example.teacher.controller.mvc;

import com.example.teacher.model.Role;
import com.example.teacher.service.IRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mvc/roles")
@Slf4j
@RequiredArgsConstructor
public class RoleMVCController {

    private final IRoleService roleService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "roles/list";
    }

    @GetMapping("/add")
    public String addRoleForm(Model model) {
        model.addAttribute("role", new Role());
        return "roles/add";
    }

    @PostMapping("/add")
    public String addRole(@ModelAttribute Role role) {
        roleService.create(role);
        return "redirect:/mvc/roles";
    }

    @GetMapping("/edit/{id}")
    public String editRoleForm(@PathVariable Integer id, Model model) {
        Role role = roleService.findById(id).orElse(null);
        model.addAttribute("actualRole", role);
        return "roles/edit";
    }

    @PostMapping("/edit/{id}")
    public String editRole(@PathVariable Integer id, @ModelAttribute Role role) {
        role.setId(id);
        roleService.update(id, role);
        return "redirect:/mvc/roles";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable Integer id) {
        roleService.deleteById(id);
        return "redirect:/mvc/roles";
    }
}
