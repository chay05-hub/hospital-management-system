package com.hospital.system.controller;

import com.hospital.system.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired private PatientService patientService;
    @Autowired private DoctorService doctorService;
    @Autowired private EmployeeService employeeService;
    @Autowired private DepartmentService departmentService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) return "redirect:/";

        model.addAttribute("patientCount", patientService.getAllPatients().size());
        model.addAttribute("doctorCount", doctorService.getAllDoctors().size());
        model.addAttribute("employeeCount", employeeService.getAllEmployees().size());
        model.addAttribute("departmentCount", departmentService.getAllDepartments().size());

        return "dashboard";
    }
}
