package com.gn.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// 스프링부트의 환경 설정-직접적으로 환경설정
@Configuration
public class SpringWebConfig implements WebMvcConfigurer{
	
	// 이미지 가져오려고...
	private String mapping = "/boardUploadImg/**";
	private String location = "file:///C:/board/upload/";
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(mapping)
		.addResourceLocations(location);
		
	}
	
	
	
	
	
}
