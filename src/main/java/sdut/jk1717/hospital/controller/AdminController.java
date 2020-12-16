package sdut.jk1717.hospital.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sdut.jk1717.hospital.Util.DateUtil;
import sdut.jk1717.hospital.Util.PwdUtil;
import sdut.jk1717.hospital.po.Bed;
import sdut.jk1717.hospital.po.Doctor;
import sdut.jk1717.hospital.po.Patient;
import sdut.jk1717.hospital.service.*;

import java.text.SimpleDateFormat;
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
    @Autowired
    BedService bedService;
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
    @GetMapping("/admin/patient")
    public String patientList(Model model){
        List<Patient> patients = patientService.findAll();
        model.addAttribute("patients",patients);
        return "admin_patient.html";
    }
    @GetMapping("/admin/doctoradd")
    public String doctorAddPage(){
        return "admin_doctor_add.html";
    }
    @PostMapping("/admin/doctoradd")
    public String doctorAdd(String name, String password, String phone, String addr, RedirectAttributes redirectAttributes){
        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setPassword(password);
        doctor.setPhone(phone);
        doctor.setAddr(addr);
        if(doctorService.addOne(doctor)!=null){
            redirectAttributes.addFlashAttribute("message","添加成功");
        }else {
            redirectAttributes.addFlashAttribute("message","添加失败");
        }
        return "redirect:/admin/doctor";
    }
    @GetMapping("/admin/doctoredit/{id}")
    public String docotrEditPage(@PathVariable("id") Long id, Model model){
        Doctor doctor = doctorService.findById(id);
        model.addAttribute("doctor",doctor);
        return "admin_doctor_edit.html";
    }
    @PostMapping("/admin/doctoredit")
    public String doctorEdit(Long id,String name,String password,String phone,String addr,RedirectAttributes redirectAttributes){
        Doctor doctor = doctorService.findById(id);
        doctor.setName(name);
        doctor.setPhone(phone);
        doctor.setAddr(addr);
        if(!password.isEmpty()){
            doctor.setPassword(PwdUtil.md5(password));
        }
        if(doctorService.update(doctor)!=null){
            redirectAttributes.addFlashAttribute("message","修改成功");
        }else {
            redirectAttributes.addFlashAttribute("message","修改失败");
        }
        return "redirect:/admin/doctor";
    }
    @GetMapping("/admin/doctordel/{id}")
    public String doctorDel(@PathVariable Long id,RedirectAttributes redirectAttributes){
        if(doctorService.deleteById(id)){
            redirectAttributes.addFlashAttribute("message","删除成功");
        }else {
            redirectAttributes.addFlashAttribute("message","删除失败");
        }
        return "redirect:/admin/doctor";
    }
    @GetMapping("/admin/doctordetail/{id}")
    public String doctorDetail(@PathVariable("id") Long id , Model model){
        Doctor doctor = doctorService.findById(id);
        List<Patient> patients = patientService.findPatientsByDoctorId(id);
        model.addAttribute("doctor",doctor);
        model.addAttribute("patients",patients);
        return "admin_doctor_detail.html";
    }
    @GetMapping("/admin/patientadd")
    public String patientAddPage(){
        return "admin_patient_add.html";
    }
    @PostMapping("/admin/patientadd")
    public String patientAdd(String name,String phone,String addr,RedirectAttributes redirectAttributes){
        Patient patient = new Patient();
        patient.setName(name);
        patient.setPhone(phone);
        patient.setAddr(addr);
        if(patientService.addOne(patient)!=null){
            redirectAttributes.addFlashAttribute("message","添加成功");
        }else {
            redirectAttributes.addFlashAttribute("message","添加失败");
        }
        return "redirect:/admin/patient";
    }
    @GetMapping("/admin/patientedit/{id}")
    public String patientEditPage(@PathVariable("id") Long id,Model model){
        Patient patient = patientService.findById(id);
        model.addAttribute("patient",patient);
        return "admin_patient_edit.html";
    }
    @PostMapping("/admin/patientedit")
    public String patientEdit(Long id,String name,String phone,String addr,RedirectAttributes redirectAttributes){
        Patient patient = patientService.findById(id);
        patient.setName(name);
        patient.setPhone(phone);
        patient.setAddr(addr);
        if(patientService.update(patient)!=null){
            redirectAttributes.addFlashAttribute("message","修改成功");
        }else {
            redirectAttributes.addFlashAttribute("message","修改失败");
        }
        return "redirect:/admin/patient";
    }
    @GetMapping("/admin/patientdel/{id}")
    public String patientdelete(@PathVariable("id") Long id,RedirectAttributes redirectAttributes){
        if(patientService.deleteById(id)){
            redirectAttributes.addFlashAttribute("message","删除成功");
        }else {
            redirectAttributes.addFlashAttribute("message","删除失败");
        }
        return "redirect:/admin/patient";
    }
    @GetMapping("/admin/patientset/{id}")
    public String patientSetPage(@PathVariable("id") Long id,Model model){
        List<Doctor> doctors = doctorService.findAll();
        List<Bed> beds = bedService.findAllUnLive();
        Patient patient = patientService.findById(id);
        model.addAttribute("doctors",doctors);
        model.addAttribute("doctor",patient.getDoctor());
        model.addAttribute("patient",patient);
        model.addAttribute("beds",beds);
        model.addAttribute("bed",patient.getBed());
        return "admin_patient_set.html";
    }
    @PostMapping("/admin/patientset/{id}")
    public String patientSet(@PathVariable("id") Long id,Long doctorid,Long bedid,RedirectAttributes redirectAttributes){
        Patient patient = patientService.findById(id);
        if(null!=doctorid){
            if(doctorid!=0){
                Doctor doctor = doctorService.findById(doctorid);
                patient.setDoctor(doctor);
            }else {
                patient.setDoctor(null);
            }
        }else if(null!=bedid){
            if(bedid!=0){
                Bed bed = bedService.findById(bedid);
                patient.setBed(bed);
            }else {
                patient.setBed(null);
            }
        }else return "error";
        if(patientService.update(patient)!=null){
            redirectAttributes.addFlashAttribute("message","分配成功");
        }else {
            redirectAttributes.addFlashAttribute("message","分配失败");
        }
        return "redirect:/admin/patient";
    }
}
