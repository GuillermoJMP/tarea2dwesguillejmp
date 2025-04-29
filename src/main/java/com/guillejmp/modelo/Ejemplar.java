package com.guillejmp.modelo;

import java.util.HashSet;
import java.util.Set;

public class Ejemplar {
	private long id;
	private String nombre;
	private String codigo;

	private Planta planta;
	
	private Set<Persona> personas = new HashSet<Persona>();

	private Set<Mensaje> mensajes = new HashSet<Mensaje>();

	public Ejemplar() {
		super();
	}

	public Ejemplar(long id, String nombre, String codigo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.codigo = codigo;
	}

	public Ejemplar(Long id, String nombre, Planta planta, Set<Persona> personas, Set<Mensaje> mensajes) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.planta = planta;
		this.personas = personas;
		this.mensajes = mensajes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Set<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(Set<Persona> personas) {
		this.personas = personas;
	}

	public Set<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(Set<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}
}

