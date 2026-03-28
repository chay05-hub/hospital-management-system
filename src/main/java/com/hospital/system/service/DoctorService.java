package com.hospital.system.service;

import com.hospital.system.model.Doctor;
import com.hospital.system.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public void saveDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public void deleteDoctorById(Long id) {
        doctorRepository.deleteById(id);
    }
}
