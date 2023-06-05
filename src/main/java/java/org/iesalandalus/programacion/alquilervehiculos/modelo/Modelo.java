package java.org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import java.org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import java.org.iesalandalus.programacion.alquilervehiculos.modelo.ficheros.IAlquileres;
import java.org.iesalandalus.programacion.alquilervehiculos.modelo.ficheros.IClientes;
import java.org.iesalandalus.programacion.alquilervehiculos.modelo.ficheros.IFuenteDatos;
import java.org.iesalandalus.programacion.alquilervehiculos.modelo.ficheros.IVehiculos;
import java.time.LocalDate;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;

public abstract class Modelo {

	private IClientes clientes;
	private IAlquileres alquileres;
	private IVehiculos vehiculos;
	private IFuenteDatos fuenteDatos;


    public Modelo(IFuenteDatos fuenteDatos) {
        this.fuenteDatos = fuenteDatos;
    }
	protected IClientes getClientes() {
		return clientes;
	}

	protected IAlquileres getAlquileres() {
		return alquileres;
	}

	protected IVehiculos getVehiculos() {
		return vehiculos;
	}

	protected void setFuenteDatos(IFuenteDatos fuenteDatos) {
		if (fuenteDatos == null) {
			throw new NullPointerException("ERROR: ");
		}
		this.fuenteDatos = fuenteDatos;
	}

	public void comenzar() {
		clientes = fuenteDatos.CrearClientes();
		alquileres = fuenteDatos.CrearAlquileres();
		vehiculos = fuenteDatos.CrearVehiculos();

	}

	public void terminar() {
		System.out.println("Fin del modelo");

	}

	public abstract void insertar(Cliente cliente) throws OperationNotSupportedException;

	public abstract void insertar(Vehiculo vehiculo) throws OperationNotSupportedException;

	public abstract void insertar(Alquiler alquiler) throws OperationNotSupportedException;

	public abstract Cliente buscar(Cliente cliente);

	public abstract Vehiculo buscar(Vehiculo vehiculo);

	public abstract Alquiler buscar(Alquiler alquiler);

	public abstract void modificar(Cliente cliente, String nombre, String telefono)
			throws OperationNotSupportedException;

	public abstract void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException;

	public abstract void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException;

	public abstract void borrar(Cliente cliente) throws OperationNotSupportedException;

	public abstract void borrar(Vehiculo vehiculo) throws OperationNotSupportedException;

	public abstract void borrar(Alquiler alquiler) throws OperationNotSupportedException;

	public abstract List<Cliente> getListaClientes();

	public abstract List<Vehiculo> getListaVehiculos();

	public abstract List<Alquiler> getListaAlquileres();

	public abstract List<Alquiler> getListaAlquileres(Cliente cliente);

	public abstract List<Alquiler> getListaAlquileres(Vehiculo vehiculo);

	public void insertar(org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler alquiler)
			throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		
	}

}