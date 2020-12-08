package sdut.jk1717.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sdut.jk1717.hospital.po.Doctor;
import sdut.jk1717.hospital.po.Drug;
import sdut.jk1717.hospital.po.Examination;
import sdut.jk1717.hospital.po.Patient;
import sdut.jk1717.hospital.service.DoctorService;
import sdut.jk1717.hospital.service.PatientService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/12/6
 **/

@Controller
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @Autowired
    PatientService patientService;
    @GetMapping("/index")
    public String doctorpage(Model model, HttpServletRequest httpServletRequest){
        System.out.println(httpServletRequest.getSession().toString());
        String name = httpServletRequest.getSession().getAttribute("username").toString();
        Doctor doctor = doctorService.findByName(name);
        System.out.println(doctor.toString());
        model.addAttribute("patients",doctor.getPatients());
        return "/doctorindex.html";
    }
}
