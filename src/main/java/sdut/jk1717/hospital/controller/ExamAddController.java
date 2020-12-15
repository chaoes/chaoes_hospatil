package sdut.jk1717.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sdut.jk1717.hospital.po.Examination;
import sdut.jk1717.hospital.po.Patient;
import sdut.jk1717.hospital.service.ExaminationService;
import sdut.jk1717.hospital.service.PatientService;

import java.util.Date;

/**
 * @auther:chaoe
 * @date:2020/12/15
 **/

@Controller
public class ExamAddController {
    @Autowired
    PatientService patientService;
    @Autowired
    ExaminationService examinationService;
    @GetMapping("/doctor/addexam/{id}")
    public String examAddPage(@PathVariable("id") Long id, Model model){
        Patient patient = patientService.findById(id);
        model.addAttribute("patient",patient);
        return "doctor_patient_exam";
    }
    @PostMapping("/doctor/addexam")
    public String examAdd(Long patientid, String content, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkdate, Integer price, RedirectAttributes redirectAttributes){
        Patient patient = patientService.findById(patientid);
        Examination examination = new Examination();
        examination.setPatient(patient);
        examination.setCheckTime(checkdate);
        examination.setPrice(price);
        examination.setContent(content);
        if(examinationService.save(examination,patient)!=null){
            redirectAttributes.addFlashAttribute("message","成功！");
        }else {
            redirectAttributes.addFlashAttribute("message","失败！");
        }
        return "redirect:/doctor/patient/"+patient.getId();
    }
}
