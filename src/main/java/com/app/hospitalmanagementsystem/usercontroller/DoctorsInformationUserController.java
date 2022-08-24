package com.app.hospitalmanagementsystem.usercontroller;

import com.app.hospitalmanagementsystem.entity.DoctorsInformation;
import com.app.hospitalmanagementsystem.service.DoctorsInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hms/user/doctors-information")
public class DoctorsInformationUserController {

    private final DoctorsInformationService doctorsInformationService;

    @Autowired
    public DoctorsInformationUserController(DoctorsInformationService doctorsInformationService) {
        this.doctorsInformationService = doctorsInformationService;
    }

    @GetMapping
    public List<DoctorsInformation> getAllDoctorsInformation() {
        return doctorsInformationService.getAllDoctorsInformation();
    }
}
