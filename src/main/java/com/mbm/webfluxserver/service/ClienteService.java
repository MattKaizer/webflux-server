package com.mbm.webfluxserver.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbm.webfluxserver.models.Cliente;
import com.mbm.webfluxserver.repository.I_ClienteRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienteService implements I_ClienteService{
	
	@Autowired
	private I_ClienteRepository repositorio;

	
	public Mono<Cliente> save(Cliente c) {
		return repositorio.save(c);
	}

	
	public Flux<Cliente> findAll() {
		return repositorio.findAll();
//				.onBackpressureDrop();
//				.delayElements(Duration.ofSeconds(1));
	}


}
