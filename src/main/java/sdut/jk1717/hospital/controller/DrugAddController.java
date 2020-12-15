package sdut.jk1717.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sdut.jk1717.hospital.po.Drug;
import sdut.jk1717.hospital.po.Patient;
import sdut.jk1717.hospital.service.DrugService;
import sdut.jk1717.hospital.service.PatientService;

/**
 * @auther:chaoe
 * @date:2020/12/14
 **/

@Controller
public class DrugAddController {
    @Autowired
    PatientService patientService;
    @Autowired
    DrugService drugService;
    @GetMapping("/doctor/adddrug/{id}")
    public String drugAddPage(@PathVariable("id") Long id, Model model){
        Patient patient = patientService.findById(id);
        model.addAttribute("patient",patient);
        return "/doctor_patient_drug.html";
    }
    @PostMapping("/doctor/adddrug")
    public String drugAdd(Long patientid,String drugname,Integer number,Integer price,RedirectAttributes redirectAttributes){
        Patient patient = patientService.findById(patientid);
        Drug drug = new Drug();
        drug.setPatient(patient);
        drug.setDrugname(drugname);
        drug.setNumber(number);
        drug.setPrice(price);
        if(drugService.save(drug,patient)!=null){
            redirectAttributes.addFlashAttribute("message","成功！");
        }else {
            redirectAttributes.addFlashAttribute("message","失败！");
        }
        return "redirect:/doctor/patient/"+patient.getId();
    }

}
