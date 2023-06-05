package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public class Cliente {
	private static final String ER_NOMBRE = "\"[A-ZÁÉÍÓÚÑ][a-záéíóúñ] \s [A-ZÁÉÍÓÚÑ][a-záéíóúñ]*\"";
	private static final String ER_DNI = "\\d{8}[A-Z]$";
	private static final String ER_TELEFONO = "^[6,7,9]\\d{8}$";

	String nombre;
	String dni;
	String telefono;

	public Cliente(String nombre, String dni, String telefono) {
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
	}

	public Cliente(Cliente cliente) {
		nombre = cliente.nombre;
		dni = cliente.dni;
		telefono = cliente.telefono;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		this.dni = dni;
	}

	private boolean comprobarLetraDNI(String dni) {
		String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
		int numeroDNI = Integer.parseInt(dni.substring(0, 8));
		char letraDNI = dni.charAt(8);
		int posicionLetra = numeroDNI % 23;
		if (letras.charAt(posicionLetra) == letraDNI) {
			return true;
		}
		return false;
	}

	public String getTelefono() {
		return telefono;
	}

	public static Cliente getClienteConDni(String dni) {
		return new Cliente("Bob Esponja", dni, "950112233");
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni, nombre, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Cliente))
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(telefono, other.telefono);
	}

	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", dni=" + dni + ", telefono=" + telefono + "]";
	}

}
