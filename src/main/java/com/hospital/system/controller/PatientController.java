package com.hospital.system.controller;

import com.hospital.system.model.Patient;
import com.hospital.system.service.PatientService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    private boolean checkSession(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    @GetMapping
    public String listPatients(Model model, HttpSession session) {
        if (!checkSession(session)) return "redirect:/";
        model.addAttribute("patients", patientService.getAllPatients());
        return "patients/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model, HttpSession session) {
        if (!checkSession(session)) return "redirect:/";
        model.addAttribute("patient", new Patient());
        return "patients/form";
    }

    @PostMapping("/save")
    public String savePatient(@ModelAttribute Patient patient, HttpSession session) {
        if (!checkSession(session)) return "redirect:/";
        patientService.savePatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        if (!checkSession(session)) return "redirect:/";
        model.addAttribute("patient", patientService.getPatientById(id));
        return "patients/form";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id, HttpSession session) {
        if (!checkSession(session)) return "redirect:/";
        patientService.deletePatientById(id);
        return "redirect:/patients";
    }
}
