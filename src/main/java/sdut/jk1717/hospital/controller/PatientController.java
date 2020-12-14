package sdut.jk1717.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sdut.jk1717.hospital.po.Patient;
import sdut.jk1717.hospital.service.PatientService;

/**
 * @auther:chaoe
 * @date:2020/12/14
 **/

@Controller
public class PatientController {
    @Autowired
    PatientService patientService;
    @GetMapping("/doctor/patient/{id}")
    public String patient(@PathVariable("id") Long id, Model model){
        Patient patient = patientService.findById(id);
        model.addAttribute("patient",patient);
        return "/doctor_patient.html";
    }
}
