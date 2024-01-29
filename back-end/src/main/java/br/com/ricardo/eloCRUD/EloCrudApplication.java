package br.com.ricardo.eloCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EloCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(EloCrudApplication.class, args);
	}
}
