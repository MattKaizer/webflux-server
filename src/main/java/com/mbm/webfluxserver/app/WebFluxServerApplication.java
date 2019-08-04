package com.mbm.webfluxserver.app;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import com.mbm.webfluxserver.models.Cliente;
import com.mbm.webfluxserver.service.ClienteService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages= {"com.mbm.webfluxserver.models", "com.mbm.webfluxserver.controllers",
"com.mbm.webfluxserver.service"})
@EntityScan("com.mbm.webfluxserver.models")
@EnableReactiveMongoRepositories("com.mbm.webfluxserver.repository")
public class WebFluxServerApplication implements CommandLineRunner{

	@Autowired
	private ClienteService repo;
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(WebFluxServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		mongoTemplate.dropCollection("clientes").subscribe();
		
		Flux.just(new Cliente("Carlos", "Gomez", "carlos@mbm.net"),
				new Cliente("Pegro", "Lopez", "pedro@mbm.net"),
				new Cliente("Pepe", "Gonzalez", "pepe@mbm.net"),
				new Cliente("Maria", "Sierra", "maria@mbm.net"),
				new Cliente("Cristina", "Soarez", "cristina@mbm.net")
				)
		.flatMap(cli -> {
			cli.setCreatedAt(new Date());
			return repo.save(cli);
			})
		.subscribe(cli -> log.info("Insert: " + cli.getId() + " " + cli.getNombre() + " " + cli.getApellido()));
	}

}
