package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;

public class Alquileres implements IAlquileres {

	List<Alquiler> coleccionAlquileres;

	public Alquileres() {

		coleccionAlquileres = new ArrayList<>();

	}

	@Override
	public List<Alquiler> get() {
		return coleccionAlquileres;
	}

	@Override
	public List<Alquiler> get(Cliente cliente) {

		List<Alquiler> listaNuevaCliente = new ArrayList<>();

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getCliente().equals(cliente)) {
				listaNuevaCliente.add(alquiler);
			}
		}
		return listaNuevaCliente;

	}

	@Override
	public List<Alquiler> get(Turismo turismo) {

		List<Alquiler> listaNuevaTurismo = new ArrayList<>();

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getTurismo().equals(turismo)) {

				listaNuevaTurismo.add(alquiler);

			}

		}
		return listaNuevaTurismo;

	}

	@Override
	public int getCantidad() {

		return coleccionAlquileres.size();

	}

	private void comprobarAlquiler(Cliente cliente, Turismo turismo, LocalDate fechaAlquiler)
			throws OperationNotSupportedException {
		for (Alquiler alquiler : coleccionAlquileres) {

			if (alquiler.getFechaDevolucion() == null) {

				if (alquiler.getCliente().equals(cliente)) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
				}
				if (alquiler.getTurismo().equals(turismo)) {

					throw new OperationNotSupportedException("ERROR: El turismo está actualmente alquilado.");
				}

			} else {

				if (alquiler.getCliente().equals(cliente) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
						|| alquiler.getFechaDevolucion().isEqual(fechaAlquiler))) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
				}
				if (alquiler.getTurismo().equals(turismo) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
						|| alquiler.getFechaDevolucion().isEqual(fechaAlquiler))) {
					throw new OperationNotSupportedException("ERROR: El turismo tiene un alquiler posterior.");
				}
			}
		}
	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}

		if (!coleccionAlquileres.contains(alquiler)) {
			comprobarAlquiler(alquiler.getCliente(), alquiler.getTurismo(), alquiler.getFechaAlquiler());
			coleccionAlquileres.add(alquiler);
		}

	}

	@Override
	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
		}

		if (coleccionAlquileres.contains(alquiler)) {

			alquiler.devolver(fechaDevolucion);

		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}

	}

	@Override
	public Alquiler buscar(Alquiler alquiler) {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}

		if (coleccionAlquileres.indexOf(alquiler) == -1) {
			// null
			alquiler = null;
		} else {
			coleccionAlquileres.get(coleccionAlquileres.indexOf(alquiler));
		}

		return alquiler;

	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}

		if (coleccionAlquileres.contains(alquiler)) {
			coleccionAlquileres.remove(alquiler);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}

	}

}