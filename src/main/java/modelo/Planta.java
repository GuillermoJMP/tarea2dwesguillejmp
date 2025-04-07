package modelo;

import java.util.HashSet;
import java.util.Set;

public class Planta {
	private String codigo;
	private String nombrecomun;
	private String nombrecientifico;

	private Set<Ejemplar> ejemplares = new HashSet<Ejemplar>();

	public Planta() {

	}

	public Planta(String codigo, String nombrecomun, String nombrecientifico) {
		this.codigo = codigo;
		this.nombrecomun = nombrecomun;
		this.nombrecientifico = nombrecientifico;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombrecomun() {
		return nombrecomun;
	}

	public void setNombrecomun(String nombrecomun) {
		this.nombrecomun = nombrecomun;
	}

	public String getNombrecientifico() {
		return nombrecientifico;
	}

	public void setNombrecientifico(String nombrecientifico) {
		this.nombrecientifico = nombrecientifico;
	}

	public Set<Ejemplar> getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(Set<Ejemplar> ejemplares) {
		this.ejemplares = ejemplares;
	}

}
