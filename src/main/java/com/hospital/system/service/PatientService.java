package com.hospital.system.service;

import com.hospital.system.model.Patient;
import com.hospital.system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public void savePatient(Patient patient) {
        patientRepository.save(patient);
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public void deletePatientById(Long id) {
        patientRepository.deleteById(id);
    }
}
