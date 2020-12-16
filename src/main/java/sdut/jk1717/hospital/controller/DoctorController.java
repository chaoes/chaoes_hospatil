package sdut.jk1717.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sdut.jk1717.hospital.po.Doctor;
import sdut.jk1717.hospital.po.Drug;
import sdut.jk1717.hospital.po.Examination;
import sdut.jk1717.hospital.po.Patient;
import sdut.jk1717.hospital.service.DoctorService;
import sdut.jk1717.hospital.service.DrugService;
import sdut.jk1717.hospital.service.ExaminationService;
import sdut.jk1717.hospital.service.PatientService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/12/6
 **/

@Controller
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @Autowired
    PatientService patientService;
    @Autowired
    DrugService drugService;
    @Autowired
    ExaminationService examinationService;
    @GetMapping("/doctor/index")
    public String doctorpage(Model model, HttpServletRequest httpServletRequest){
        System.out.println(httpServletRequest.getSession().toString());
        String name = httpServletRequest.getSession().getAttribute("username").toString();
        Doctor doctor = doctorService.findByName(name);
        model.addAttribute("patients",patientService.findPatientsByDoctorId(doctor.getId()));
        return "/doctorindex.html";
    }
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
    @GetMapping("/doctor/patient/adddrug/{id}")
    public String patientAddDrug(Long id){
        Drug drug =new Drug();
        drug.setDrugname("qoq");
        Patient patient = new Patient();
        patient.setId(1L);
//        drug.setPatient(patient);
        drugService.save(drug,patient);
        return drug.toString();
    }
    @GetMapping("/doctor/adddrug/{id}")
    public String drugAddPage(@PathVariable("id") Long id, Model model){
        Patient patient = patientService.findById(id);
        model.addAttribute("patient",patient);
        return "/doctor_patient_drug.html";
    }
    @PostMapping("/doctor/adddrug")
    public String drugAdd(Long patientid, String drugname, Integer number, Integer price, RedirectAttributes redirectAttributes){
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
