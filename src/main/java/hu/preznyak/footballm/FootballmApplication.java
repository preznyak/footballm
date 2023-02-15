package hu.preznyak.footballm;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Football Manager API",
				description = "API Definitions of the Football Manager Microservice",
				version = "1.0.0"))
@SpringBootApplication
public class FootballmApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballmApplication.class, args);
	}

}
