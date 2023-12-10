package br.com.ricardo.eloCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EloCrudApplication implements CommandLineRunner {
	@Value("${info}")
	private String text;

	public static void main(String[] args) {
		SpringApplication.run(EloCrudApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println(text);
	}
}
