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
import sdut.jk1717.hospital.po.Doctor;
import sdut.jk1717.hospital.po.Patient;
import sdut.jk1717.hospital.service.DoctorService;
import sdut.jk1717.hospital.service.DrugService;
import sdut.jk1717.hospital.service.ExaminationService;
import sdut.jk1717.hospital.service.PatientService;
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
}
