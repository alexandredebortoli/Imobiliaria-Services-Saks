package br.com.saks.tipoimovelservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TipoImovelServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TipoImovelServiceApplication.class, args);
	}

}
