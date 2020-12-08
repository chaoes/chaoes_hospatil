package sdut.jk1717.hospital.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * @auther:chaoe
 * @date:2020/12/8
 **/

@Controller
public class RouterController {
    @RequestMapping("/")
    public String router(HttpServletRequest httpServletRequest){
        if(httpServletRequest.getSession().getAttribute("usertype")!=null&&httpServletRequest.getSession().getAttribute("usertype").equals("admin")){
            return "redirect:/admin/index";
        }
        else if(httpServletRequest.getSession().getAttribute("usertype")!=null&&httpServletRequest.getSession().getAttribute("usertype").equals("doctor")){
            return "redirect:/doctor/index";
        }
        return "redirect:/login";
    }
}
