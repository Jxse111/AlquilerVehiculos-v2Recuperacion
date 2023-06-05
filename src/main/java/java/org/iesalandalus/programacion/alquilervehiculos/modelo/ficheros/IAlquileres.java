package java.org.iesalandalus.programacion.alquilervehiculos.modelo.ficheros;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

public interface IAlquileres {

	List<Alquiler> get();

	List<Alquiler> get(Cliente cliente);

	List<Alquiler> get(Turismo turismo);

	void insertar(Alquiler alquiler) throws OperationNotSupportedException;

	void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException;

	Alquiler buscar(Alquiler alquiler);

	void borrar(Alquiler alquiler) throws OperationNotSupportedException;

}