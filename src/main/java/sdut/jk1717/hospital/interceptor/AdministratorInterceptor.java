package sdut.jk1717.hospital.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther:chaoe
 * @date:2020/12/6
 **/


public class AdministratorInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("username")==null||!request.getSession().getAttribute("usertype").equals("admin")){
            response.sendRedirect("/login");
        }
        return true;
    }
}
