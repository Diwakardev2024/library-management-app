package com.appsdevelopers.app.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.appsdevelopers.app.ws"})
public class LibraryManagementAppApplication {

	public static void main(String[] args) {
		
	SpringApplication.run(LibraryManagementAppApplication.class, args);
		
	
	}

}
