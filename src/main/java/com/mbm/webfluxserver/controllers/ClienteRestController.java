package com.mbm.webfluxserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbm.webfluxserver.models.Cliente;
import com.mbm.webfluxserver.service.ClienteService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	public ClienteService clienteService;
	
	/*
	 * List 
	 */
	
	@GetMapping(path="/clientes", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Cliente> index() {
		return clienteService.findAll().doOnNext(cli -> log.info(cli.toString()));
	}
	
	
}
