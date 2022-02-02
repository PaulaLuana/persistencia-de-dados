package br.ufc.quixada.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication
@EntityScan("br.ufc.quixada.entity")
@EnableJpaRepositories("br.ufc.quixada.dao")
public class Principal {

	public static void main(String[] args) {
		SpringApplication.run(InsertDAO.class, args);
	}
	
}
