package com.multi.bootAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.multi.book.APIController;

@SpringBootApplication
@ComponentScan(basePackageClasses=APIController.class)
public class BootApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootApiApplication.class, args);
	}

}
