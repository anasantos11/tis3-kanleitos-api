package br.com.kanleitos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@RestController
@SpringBootApplication
@ComponentScan(basePackages = { "br.com.kanleitos.controllers" })
@EntityScan(basePackages = { "br.com.kanleitos.models" })
@EnableJpaRepositories(basePackages = { "br.com.kanleitos.repository" })
public class Config {

	public static void main(String[] args) {
		SpringApplication.run(Config.class);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}
	

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

}
