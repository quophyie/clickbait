package com.quantal.clickbait;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@EnableWebMvc
@ComponentScan("com.quantal.clickbait")
public class ClickBaitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClickBaitApplication.class, args);
	}
}
