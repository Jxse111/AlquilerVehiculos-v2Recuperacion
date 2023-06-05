package java.org.iesalandalus.programacion.alquilervehiculos.modelo.ficheros;

import javax.naming.OperationNotSupportedException;

public interface IClientes {

	List<Cliente> get();

	int getCantidad();

	void insertar(Cliente cliente) throws OperationNotSupportedException;

	Cliente buscar(Cliente cliente);

	void borrar(Cliente cliente) throws OperationNotSupportedException;

	void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException;

}