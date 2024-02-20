package br.com.ricardo.eloCRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EloCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(EloCrudApplication.class, args);
	}
}
