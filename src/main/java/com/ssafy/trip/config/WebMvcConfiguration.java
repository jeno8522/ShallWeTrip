package com.ssafy.trip.config;

import java.util.Arrays;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration	//java로 bean configuration을 설정하기 위한 Annotation
@MapperScan(basePackages = {"com.ssafy.**.model.dao"})	//Mybatis를 위한 Mapper interface위치 설정
public class WebMvcConfiguration implements WebMvcConfigurer {
    
    private final List<String> patterns = Arrays.asList("/article/*", "/admin", "/user/list");
    
//    private ConfirmInterceptor confirmInterceptor;
    public WebMvcConfiguration() {
    	System.out.println("WebMvcConfiguration.............................................");
    }
    @Value("${file.imgPath}")
    private String uploadPath;
    
//    public WebMvcConfiguration(ConfirmInterceptor confirmInterceptor) {
//        this.confirmInterceptor = confirmInterceptor;
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	
    	System.out.println("uploadPath :"+uploadPath);
//        registry.addResourceHandler("/upload/image/**").addResourceLocations("file:///c://springboot//upload//");
        registry.addResourceHandler("/upload/image/**").addResourceLocations("file:///c://springboot//upload//");
    
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(confirmInterceptor).addPathPatterns(patterns);
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	System.out.println("CORS.............................................");
        registry.addMapping("/**")
            .allowedOrigins("*")
//            .allowedOrigins("http://www.ssafy.com", "http://localhost", "http://localhost:8080")
            .allowedMethods("GET", "POST", "UPDATE", "DELETE")
//            .allowedMethods(RequestMethod.GET.name(), RequestMethod.POST.name(), ..)
//            .allowedMethods("*")
            .maxAge(1800);
        System.out.println("CORS............................................."+registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index2");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

}
