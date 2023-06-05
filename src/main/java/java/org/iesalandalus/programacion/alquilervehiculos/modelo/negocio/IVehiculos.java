package java.org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import java.util.List;

import javax.naming.OperationNotSupportedException;

public interface IVehiculos {

	List<Vehiculo> get();

	int getCantidad();

	void insertar(Vehiculo vehiculo) throws OperationNotSupportedException;

	Vehiculo buscar(Vehiculo vehiculo);

	void borrar(Vehiculo vehiculo) throws OperationNotSupportedException;

}