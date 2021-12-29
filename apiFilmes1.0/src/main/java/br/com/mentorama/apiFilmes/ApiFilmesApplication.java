package br.com.mentorama.apiFilmes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.mentorama.apiFilmes"})//mais simplificado ja englobando tudo
@EnableAsync
public class ApiFilmesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiFilmesApplication.class, args);
	}

}
