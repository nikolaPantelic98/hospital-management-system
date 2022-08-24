package com.app.hospitalmanagementsystem.usercontroller;

import com.app.hospitalmanagementsystem.entity.MedicalServiceSystem;
import com.app.hospitalmanagementsystem.service.MedicalServiceSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hms/user/medical-services")
public class MedicalServiceSystemUserController {

    private final MedicalServiceSystemService medicalServiceSystemService;

    @Autowired
    public MedicalServiceSystemUserController(MedicalServiceSystemService medicalServiceSystemService) {
        this.medicalServiceSystemService = medicalServiceSystemService;
    }

    @GetMapping
    public List<MedicalServiceSystem> getAllMedicalServices() {
        return medicalServiceSystemService.getAllMedicalServices();
    }
}
