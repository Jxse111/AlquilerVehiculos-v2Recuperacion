package java.org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import java.org.iesalandalus.programacion.alquilervehiculos.modelo.ficheros.IFuenteDatos;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

public class ModeloCascada extends Modelo {

	public ModeloCascada(IFuenteDatos fuenteDatos) {
		super();
		setFuenteDatos(fuenteDatos);
	}

	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		getClientes().insertar(new Cliente(cliente));
	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}

		Cliente auxCliente = getClientes().buscar(alquiler.getCliente());
		if (auxCliente == null) {
			throw new OperationNotSupportedException("ERROR: No existe el cliente del alquiler.");
		}

		Vehiculo auxVehiculo = getVehiculos().buscar(alquiler.getVehiculo());
		if (auxVehiculo == null) {
			throw new OperationNotSupportedException("ERROR: No existe el vehículo del alquiler.");
		}

		Alquiler auxAlquiler = new Alquiler(auxCliente, auxVehiculo, alquiler.getFechaAlquiler());
		getAlquileres().insertar(auxAlquiler);
	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		getVehiculos().insertar(vehiculo);
	}

	@Override
	public Cliente buscar(Cliente cliente) {
		Cliente aux = null;
		Cliente buscarCliente = getClientes().buscar(cliente);

		if (buscarCliente != null) {
			aux = new Cliente(buscarCliente);
		}
		return aux;
	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {
		Vehiculo aux = null;
		Vehiculo buscarVehiculo = getVehiculos().buscar(vehiculo);

		if (buscarVehiculo != null) {
			aux = Vehiculo.copiar(buscarVehiculo);
		}
		return aux;
	}

	@Override
	public Alquiler buscar(Alquiler alquiler) {
		return getAlquileres().buscar(alquiler);
	}

	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		getClientes().modificar(cliente, nombre, telefono);
	}

	@Override
	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new OperationNotSupportedException("ERROR: No existe un cliente a devolver.");
		}

		getAlquileres().devolver(cliente, fechaDevolucion);
	}

	@Override
	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new OperationNotSupportedException("ERROR: No existe un vehiculo a devolver.");
		}

		getAlquileres().devolver(vehiculo, fechaDevolucion);
	}

	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		List<Alquiler> alquileresCliente = getAlquileres().get(cliente);
		for (Alquiler aux : alquileresCliente) {
			borrar(aux);
		}

		getClientes().borrar(cliente);
	}

	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		List<Alquiler> alquileresVehiculo = getAlquileres().get(vehiculo);
		for (Alquiler aux : alquileresVehiculo) {
			borrar(aux);
		}

		getVehiculos().borrar(vehiculo);
	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		getAlquileres().borrar(alquiler);
	}

	@Override
	public List<Cliente> getListaClientes() {
		List<Cliente> listaTemporal = new ArrayList<>();
		for (Cliente cliente : getClientes().get()) {
			listaTemporal.add(new Cliente(cliente));
		}

		return listaTemporal;

	}

	@Override
	public List<Vehiculo> getListaVehiculos() {
		List<Vehiculo> listaTemporal = new ArrayList<>();
		for (Vehiculo vehiculo : getVehiculos().get()) {
			listaTemporal.add(Vehiculo.copiar(vehiculo));
		}

		return listaTemporal;

	}

	@Override
	public List<Alquiler> getListaAlquileres() {
		List<Alquiler> listaTemporal = new ArrayList<>();
		for (Alquiler alquiler : getAlquileres().get()) {
			listaTemporal.add(new Alquiler(alquiler));
		}

		return listaTemporal;

	}

	@Override
	public List<Alquiler> getListaAlquileres(Cliente cliente) {
		List<Alquiler> listaTemporal = new ArrayList<>();
		for (Alquiler alquilerCliente : getAlquileres().get(cliente)) {
			listaTemporal.add(new Alquiler(alquilerCliente));
		}
		return listaTemporal;

	}

	@Override
	public List<Alquiler> getListaAlquileres(Vehiculo vehiculo) {
		List<Alquiler> listaTemporal = new ArrayList<>();
		for (Alquiler alquiler : getAlquileres().get(vehiculo)) {
			listaTemporal.add(new Alquiler(alquiler));
		}
		return listaTemporal;
	}

}