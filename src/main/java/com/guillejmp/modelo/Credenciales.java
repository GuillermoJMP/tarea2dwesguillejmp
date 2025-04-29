package com.guillejmp.modelo;

public class Credenciales {
	private long id;
	private String usuario;
	private String password;
	private long id_persona;

	private Persona persona;

	public Credenciales() {
		super();
	}

	public Credenciales(String usuario, String password) {
		super();
		this.usuario = usuario;
		this.password = password;
	}

	public Credenciales(String usuario, String password, Persona persona) {
		this.usuario = usuario;
		this.password = password;
		this.persona = persona;
	}

	public Credenciales(long id, String usuario, String password, Persona persona) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.password = password;
		this.persona = persona;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public long getId_persona() {
		return id_persona;
	}

	public void setId_persona(long id_persona) {
		this.id_persona = id_persona;
	}

}
