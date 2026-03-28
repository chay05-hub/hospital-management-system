package com.hospital.system.controller;

import com.hospital.system.model.Doctor;
import com.hospital.system.service.DoctorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    private boolean checkSession(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    @GetMapping
    public String listDoctors(Model model, HttpSession session) {
        if (!checkSession(session)) return "redirect:/";
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "doctors/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model, HttpSession session) {
        if (!checkSession(session)) return "redirect:/";
        model.addAttribute("doctor", new Doctor());
        return "doctors/form";
    }

    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute Doctor doctor, HttpSession session) {
        if (!checkSession(session)) return "redirect:/";
        doctorService.saveDoctor(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        if (!checkSession(session)) return "redirect:/";
        model.addAttribute("doctor", doctorService.getDoctorById(id));
        return "doctors/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id, HttpSession session) {
        if (!checkSession(session)) return "redirect:/";
        doctorService.deleteDoctorById(id);
        return "redirect:/doctors";
    }
}
