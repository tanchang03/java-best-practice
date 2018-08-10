package com.sds.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ModuleDomainServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModuleDomainServiceApplication.class, args);
	}
}
