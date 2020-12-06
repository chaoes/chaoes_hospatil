package sdut.jk1717.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sdut.jk1717.hospital.po.Administrator;
import sdut.jk1717.hospital.po.Doctor;
import sdut.jk1717.hospital.service.AdministratorService;
import sdut.jk1717.hospital.service.DoctorService;
import javax.servlet.http.HttpServletRequest;


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
    public String login(String username, String password, String usertype, Model model, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes){
        if(usertype==null){
            redirectAttributes.addFlashAttribute("message", "登录异常");
            return "redirect:/login";
        }
        else if(usertype.equals("admin")){
            Administrator administrator = administratorService.check(username,password);
            if(administrator!=null){
                httpServletRequest.getSession().setAttribute("username",administrator.getName());
                httpServletRequest.getSession().setAttribute("usertype","admin");
                return "redirect:/temp";
            }
            redirectAttributes.addFlashAttribute("message", "登录失败");
            return "redirect:/login";

        }
        else if(usertype.equals("doctor")){
            Doctor doctor = doctorService.check(username,password);
            if(doctor!=null){
                httpServletRequest.getSession().setAttribute("username",doctor.getName());
                httpServletRequest.getSession().setAttribute("usertype","doctor");
                return "redirect:/doctor/index";
            }
            redirectAttributes.addFlashAttribute("message", "登录失败");
            return "redirect:/login";

        }
        redirectAttributes.addFlashAttribute("message", "登录失败");
        return "redirect:/login";

    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().removeAttribute("username");
        return "redirect://login";
    }
}
