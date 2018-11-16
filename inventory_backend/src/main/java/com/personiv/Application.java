package com.personiv;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.personiv.service.UploadService;


@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {
	
	@Resource
	UploadService storageService;
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
      return builder.sources(Application.class);
	}
	
	public static void main(String[] args) {
		System.out.println();
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		//storageService.deleteAll();
		//storageService.init();
	}
}
