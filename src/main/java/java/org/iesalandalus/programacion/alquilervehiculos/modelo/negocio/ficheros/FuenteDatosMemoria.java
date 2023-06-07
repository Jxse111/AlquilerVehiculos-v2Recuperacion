package java.org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import java.org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import java.org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import java.org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;

public class FuenteDatosMemoria implements IFuenteDatos {
 @Override
public IClientes CrearClientes() {
	 return new Clientes();
 }
 @Override
public IVehiculos CrearVehiculos() {
	 return  new Vehiculos();
 }
 @Override
public IAlquileres CrearAlquileres() {
	 return new Alquileres();
 }
}
