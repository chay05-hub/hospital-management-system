package com.hospital.system.controller;

import com.hospital.system.model.Department;
import com.hospital.system.service.DepartmentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    private boolean checkSession(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    @GetMapping
    public String listDepartments(Model model, HttpSession session) {
        if (!checkSession(session)) return "redirect:/";
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "departments/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model, HttpSession session) {
        if (!checkSession(session)) return "redirect:/";
        model.addAttribute("department", new Department());
        return "departments/form";
    }

    @PostMapping("/save")
    public String saveDepartment(@ModelAttribute Department department, HttpSession session) {
        if (!checkSession(session)) return "redirect:/";
        departmentService.saveDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        if (!checkSession(session)) return "redirect:/";
        model.addAttribute("department", departmentService.getDepartmentById(id));
        return "departments/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Long id, HttpSession session) {
        if (!checkSession(session)) return "redirect:/";
        departmentService.deleteDepartmentById(id);
        return "redirect:/departments";
    }
}
