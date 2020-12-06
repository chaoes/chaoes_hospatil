package sdut.jk1717.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther:chaoe
 * @date:2020/12/6
 **/

@Controller
@RequestMapping("/doctor")
public class DoctorController {
    @GetMapping("/index")
    public String doctorpage(){
        return "/doctorindex.html";
    }
}
