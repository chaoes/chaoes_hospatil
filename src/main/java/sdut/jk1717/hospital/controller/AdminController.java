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
import sdut.jk1717.hospital.po.*;
import sdut.jk1717.hospital.service.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

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
        long bedCount = bedService.count();
        model.addAttribute("patientCount",patientCount);
        model.addAttribute("doctorCount",doctorCount);
        model.addAttribute("drugCount",newDrugCount);
        model.addAttribute("examCount",newExamCount);
        model.addAttribute("bedCount",bedCount);
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
        }else return "/error";
        if(patientService.update(patient)!=null){
            redirectAttributes.addFlashAttribute("message","分配成功");
        }else {
            redirectAttributes.addFlashAttribute("message","分配失败");
        }
        return "redirect:/admin/patient";
    }
    @GetMapping("/admin/drug")
    public String drug(Model model){
        List<Drug> drugs = drugService.findAll();
        model.addAttribute("drugs",drugs);
        return "admin_drug.html";
    }
    @GetMapping("/admin/exam")
    public String exam(Model model){
        List<Examination> examinations = examinationService.findAll();
        model.addAttribute("exams",examinations);
        return "admin_exam.html";
    }
    @GetMapping("/admin/bed")
    public String bed(Model model){
        List<Bed> beds = bedService.findAll();
        model.addAttribute("beds",beds);
        return "admin_bed.html";
    }
    @GetMapping("/admin/drugedit/{id}")
    public String drugEditPage(@PathVariable("id") Long id,Model model){
        Drug drug = drugService.findById(id);
        model.addAttribute(drug);
        return "admin_drug_edit.html";
    }
    @PostMapping("/admin/drugedit")
    public String drugEdit(Long id,String drugname,Integer number,Float price,RedirectAttributes redirectAttributes){
        Drug drug = drugService.findById(id);
        drug.setDrugname(drugname);
        drug.setNumber(number);
        drug.setPrice(price);
        if(drugService.update(drug)!=null){
            redirectAttributes.addFlashAttribute("message","修改成功");
        }else {
            redirectAttributes.addFlashAttribute("message","修改失败");
        }
        return "redirect:/admin/drug";
    }
    @GetMapping("/admin/examedit/{id}")
    public String examEditPage(@PathVariable("id") Long id,Model model){
        Examination examination = examinationService.findById(id);
        model.addAttribute("exam",examination);
        return "admin_exam_edit.html";
    }
    @PostMapping("/admin/examedit")
    public String examEdit(Long id, String content, Date checkdate, Float price,RedirectAttributes redirectAttributes){
        Examination examination = examinationService.findById(id);
        examination.setContent(content);
        examination.setCheckTime(checkdate);
        examination.setPrice(price);
        if(examinationService.update(examination)!=null){
            redirectAttributes.addFlashAttribute("message","修改成功");
        }else {
            redirectAttributes.addFlashAttribute("message","修改失败");
        }
        return "redirect:/admin/exam";
    }
    @GetMapping("/admin/drugdel/{id}")
    public String drugDel(@PathVariable("id") Long id,RedirectAttributes redirectAttributes){
        if(drugService.deleteById(id)){
            redirectAttributes.addFlashAttribute("message","删除成功");
        }else {
            redirectAttributes.addFlashAttribute("message","删除失败");
        }
        return "redirect:/admin/drug";
    }
    @GetMapping("/admin/examdel/{id}")
    public String StringexamDel(@PathVariable("id") Long id,RedirectAttributes redirectAttributes){
        if(examinationService.deleteById(id)){
            redirectAttributes.addFlashAttribute("message","删除成功");
        }else {
            redirectAttributes.addFlashAttribute("message","删除失败");
        }
        return "redirect:/admin/exam";
    }
    @GetMapping("admin/bedadd")
    public String bedAddPage(){
        return "admin_bed_add.html";
    }
    @PostMapping("admin/bedadd")
    public String bedAdd(Integer number,RedirectAttributes redirectAttributes){
        Bed bed = new Bed();
        bed.setNumber(number);
        if(bedService.addOne(bed)!=null){
            redirectAttributes.addFlashAttribute("message","添加成功");
        }else {
            redirectAttributes.addFlashAttribute("message","添加成功");
        }
        return "redirect:/admin/bed";
    }
    @GetMapping("/admin/bededit/{id}")
    public String bedEditPage(@PathVariable("id") Long id,Model model){
        Bed bed = bedService.findById(id);
        model.addAttribute("bed",bed);
        return "admin_bed_edit.html";
    }
    @PostMapping("/admin/bededit")
    public String bedEdit(Long id,Integer number,RedirectAttributes redirectAttributes){
        Bed bed = bedService.findById(id);
        bed.setNumber(number);
        if(bedService.update(bed)!=null){
            redirectAttributes.addFlashAttribute("message","修改成功");
        }else {
            redirectAttributes.addFlashAttribute("message","修改失败");
        }
        return "redirect:/admin/bed";
    }
    @GetMapping("/admin/beddel/{id}")
    public String bedDel(@PathVariable("id")Long id,RedirectAttributes redirectAttributes){
        if(bedService.deleteById(id)){
            redirectAttributes.addFlashAttribute("message","删除成功");
        }else {
            redirectAttributes.addFlashAttribute("message","删除失败");
        }
        return "redirect:/admin/bed";
    }
    @GetMapping("/admin/patientdetail/{id}")
    public  String patientDetail(@PathVariable("id") Long id,Model model){
        Patient patient = patientService.findById(id);
        List<Drug> drugs = drugService.findAllByPatient_Id(id);
        List<Examination> examinations = examinationService.findAllByPatient_Id(id);
        model.addAttribute("patient",patient);
        model.addAttribute("drugs",drugs);
        model.addAttribute("exams",examinations);
        return "admin_patient_detail.html";
    }
    @GetMapping("/admin/patientreport/{id}")
    public String patientReportPage(@PathVariable("id") Long id,Model model){
        Patient patient = patientService.findById(id);
        List<Date> dates1 = drugService.findDistinctDate(id);
        List<Date> dates2 = examinationService.findDistinctDate(id);
        dates1.addAll(dates2);
        model.addAttribute("dates",dates1);
        model.addAttribute("patient",patient);
        return "admin_patient_report.html";
    }
    @PostMapping("/admin/patientreport/{id}")
    public String patientReport(@PathVariable("id") Long id,Date date,Model model){
        Patient patient = patientService.findById(id);
        Double pricedrug;
        Double priceExam;
        List<Drug> drugs = drugService.findAllByDateAndPatient_Id(date,id);
        List<Examination> examinations = examinationService.findAllByDateAndPatient_Id(date,id);
        if(drugs.size()>0){
            pricedrug=drugs.stream().collect(Collectors.summingDouble(Drug::getPrice));
            model.addAttribute("drugs",drugs);
            model.addAttribute("pricedrug",pricedrug);
        }
        if(examinations.size()>0){
            priceExam = examinations.stream().collect(Collectors.summingDouble(Examination::getPrice));
            model.addAttribute("exams",examinations);
            model.addAttribute("priceexam",priceExam);
        }
        model.addAttribute("patient",patient);
        return "report.html";
    }

}
