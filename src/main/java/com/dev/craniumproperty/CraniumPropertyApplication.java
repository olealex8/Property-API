package com.dev.craniumproperty;

import com.dev.craniumproperty.Filter.AuthFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@MapperScan("com.dev.craniumproperty.repository")
public class CraniumPropertyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CraniumPropertyApplication.class, args);
	}

//	@Bean
//	public FilterRegistrationBean<AuthFilter> FilterRegistrationBean(){
//		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
//		AuthFilter authFilter  = new AuthFilter();
//		registrationBean.setFilter(authFilter);
//		return registrationBean;
//	}
}
