package com.example.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DesafioReceitaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioReceitaApplication.class, args);
	}

}
