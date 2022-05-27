package br.com.saks.administradorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AdministradorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministradorServiceApplication.class, args);
	}

}
