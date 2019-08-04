package com.mbm.webfluxserver.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="clientes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cliente  {

	@Id
	private String id;
	private String nombre;
	private String apellido;
	private String email;
	private Date createdAt;
	
	public Cliente(String nombre, String apellido, String email) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
	}

	
	
}
