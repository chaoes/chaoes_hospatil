package sdut.jk1717.hospital.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import sdut.jk1717.hospital.interceptor.AdministratorInterceptor;
import sdut.jk1717.hospital.interceptor.DoctorInterceptor;

/**
 * @auther:chaoe
 * @date:2020/12/6
 **/

@Configuration
public class IInterceptorCongig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DoctorInterceptor()).addPathPatterns("/doctor/**");
        registry.addInterceptor(new AdministratorInterceptor()).addPathPatterns("/admin/**");
        super.addInterceptors(registry);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
