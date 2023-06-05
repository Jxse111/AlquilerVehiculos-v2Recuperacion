package java.org.iesalandalus.programacion.alquilervehiculos.modelo.ficheros;

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
