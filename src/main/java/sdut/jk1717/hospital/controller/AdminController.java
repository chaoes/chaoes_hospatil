package sdut.jk1717.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sdut.jk1717.hospital.Util.DateUtil;
import sdut.jk1717.hospital.po.Doctor;
import sdut.jk1717.hospital.po.Drug;
import sdut.jk1717.hospital.po.Examination;
import sdut.jk1717.hospital.service.DoctorService;
import sdut.jk1717.hospital.service.DrugService;
import sdut.jk1717.hospital.service.ExaminationService;
import sdut.jk1717.hospital.service.PatientService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/12/15
 **/

@Controller
public class AdminController {
    @Autowired
    PatientService patientService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    DrugService drugService;
    @Autowired
    ExaminationService examinationService;
    SimpleDateFormat simpledateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @RequestMapping("/admin/index")
    public String adminIndex(Model model){
        long patientCount = patientService.count();
        long doctorCount = doctorService.count();
        int newDrugCount = drugService.countAllByDate(DateUtil.getTodayDate());
        int newExamCount = examinationService.countAllByDate(DateUtil.getTodayDate());
        model.addAttribute("patientCount",patientCount);
        model.addAttribute("doctorCount",doctorCount);
        model.addAttribute("drugCount",newDrugCount);
        model.addAttribute("examCount",newExamCount);
        return "/admin_index.html";
    }
    @GetMapping("/admin/doctor")
    public String doctorList(Model model){
        List<Doctor> doctors = doctorService.findAll();
        model.addAttribute("doctors",doctors);
        return "admin_doctor.html";

    }
}
