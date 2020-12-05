package sdut.jk1717.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sdut.jk1717.hospital.po.Administrator;
import sdut.jk1717.hospital.po.Doctor;
import sdut.jk1717.hospital.service.AdministratorService;
import sdut.jk1717.hospital.service.DoctorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;


/**
 * @auther:chaoe
 * @date:2020/12/5
 **/

@Controller
public class LoginController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AdministratorService administratorService;
    @GetMapping("/login")
    public String loginPage(){
        return "/login.html";
    }
    @PostMapping("/login")
    public String login(String username, String password, String usertype, Model model, HttpServletRequest httpServletRequest){
        if(usertype==null){
            return "redirect:/login";
        }
        else if(usertype.equals("admin")){
            Administrator administrator = administratorService.check(username,password);
            if(administrator!=null){
                        httpServletRequest.getSession().setAttribute("username",administrator.getName());
                return "redirect:/temp";
            }
            return "redirect:/login";

        }
        else if(usertype.equals("doctor")){
            Doctor doctor = doctorService.check(username,password);
            if(doctor!=null){
                httpServletRequest.getSession().setAttribute("username",doctor.getName());
                return "redirect:/temp";
            }
            return "redirect:/login";

        }
        return "redirect:/login";

    }
}
