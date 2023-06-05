package java.org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

public interface IAlquileres {

	List<Alquiler> get();

	List<Alquiler> get(Cliente cliente);

	List<Alquiler> get(Turismo turismo);

	int getCantidad();

	void insertar(Alquiler alquiler) throws OperationNotSupportedException;

	void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException;

	Alquiler buscar(Alquiler alquiler);

	void borrar(Alquiler alquiler) throws OperationNotSupportedException;

}