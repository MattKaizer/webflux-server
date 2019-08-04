package com.mbm.webfluxserver.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.mbm.webfluxserver.models.Cliente;

@Repository
public interface I_ClienteRepository extends ReactiveMongoRepository<Cliente, String> {



}
