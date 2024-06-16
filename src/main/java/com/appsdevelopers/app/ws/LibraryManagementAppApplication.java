package com.appsdevelopers.app.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@SpringBootApplication
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages={"com.appsdevelopers.app.ws"})
public class LibraryManagementAppApplication {
	 private static final Logger logger = LogManager.getLogger(LibraryManagementAppApplication.class);
	public static void main(String[] args) {
		
	SpringApplication.run(LibraryManagementAppApplication.class, args);
		logger.info("Application started");
		if(logger.isDebugEnabled()) {
			logger.debug("Application started in debug mode.");
		}
	}

}
