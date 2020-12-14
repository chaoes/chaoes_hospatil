package sdut.jk1717.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sdut.jk1717.hospital.po.Drug;
import sdut.jk1717.hospital.po.Examination;
import sdut.jk1717.hospital.po.Patient;
import sdut.jk1717.hospital.service.DrugService;
import sdut.jk1717.hospital.service.ExaminationService;
import sdut.jk1717.hospital.service.PatientService;

import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/12/14
 **/

@Controller
public class PatientController {
    @Autowired
    PatientService patientService;
    @Autowired
    DrugService drugService;
    @Autowired
    ExaminationService examinationService;
    @GetMapping("/doctor/patient/{id}")
    public String patient(@PathVariable("id") Long id, Model model){
        Patient patient = patientService.findById(id);
        List<Drug> drugs = drugService.findAllByPatient_Id(patient.getId());
        List<Examination> examinations = examinationService.findAllByPatient_Id(patient.getId());
        model.addAttribute("drugs",drugs);
        model.addAttribute("exams",examinations);
        model.addAttribute("patient",patient);
        return "/doctor_patient.html";
    }
}
