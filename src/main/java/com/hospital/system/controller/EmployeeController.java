package com.hospital.system.controller;

import com.hospital.system.model.Employee;
import com.hospital.system.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private boolean checkSession(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    @GetMapping
    public String listEmployees(Model model, HttpSession session) {
        if (!checkSession(session)) return "redirect:/";
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model, HttpSession session) {
        if (!checkSession(session)) return "redirect:/";
        model.addAttribute("employee", new Employee());
        return "employees/form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee, HttpSession session) {
        if (!checkSession(session)) return "redirect:/";
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        if (!checkSession(session)) return "redirect:/";
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "employees/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, HttpSession session) {
        if (!checkSession(session)) return "redirect:/";
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }
}
