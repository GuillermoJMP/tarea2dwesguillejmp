package com.guillejmp.modelo;

import java.util.HashSet;
import java.util.Set;

public class Persona {
	private long id;
	private String nombre;
	private String email;

	private Credenciales credenciales;

	private Set<Ejemplar> ejemplares = new HashSet<Ejemplar>();

	public Persona() {
		super();
	}

	public Persona(long id) {
		super();
		this.id = id;
	}

	public Persona(long id, String nombre, String email, Credenciales credenciales, Set<Ejemplar> ejemplares) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.credenciales = credenciales;
		this.ejemplares = ejemplares;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Credenciales getCredenciales() {
		return credenciales;
	}

	public void setCredenciales(Credenciales credenciales) {
		this.credenciales = credenciales;
	}

	public Set<Ejemplar> getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(Set<Ejemplar> ejemplares) {
		this.ejemplares = ejemplares;
	}
}
