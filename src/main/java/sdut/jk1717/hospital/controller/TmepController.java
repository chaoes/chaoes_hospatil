package sdut.jk1717.hospital.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

/**
 * @auther:chaoe
 * @date:2020/12/5
 **/

@Controller
public class TmepController {
    @RequestMapping("/temp")
    public String hello(HttpSession session){
        System.out.println(session.getAttribute("username"));
        return "temp.html";
    }
}
