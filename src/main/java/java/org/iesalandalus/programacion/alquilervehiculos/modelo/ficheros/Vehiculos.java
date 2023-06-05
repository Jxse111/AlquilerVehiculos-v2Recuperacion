package java.org.iesalandalus.programacion.alquilervehiculos.modelo.ficheros;
	import java.org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import java.util.ArrayList;
	import java.util.List;

	import javax.naming.OperationNotSupportedException;

	public class Vehiculos implements IVehiculos {

		List<Vehiculo> coleccionTurismo;

		public Vehiculos() {

			coleccionTurismo = new ArrayList<>();

		}

		@Override
		public List<Vehiculo> get() {

			return coleccionTurismo;
		}
		@Override
		public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {

			if (vehiculo == null) {
				throw new NullPointerException("ERROR: No se puede insertar un turismo nulo.");
			}

			if (!coleccionTurismo.contains(vehiculo)) {
				coleccionTurismo.add(vehiculo);
			} else {
				throw new OperationNotSupportedException("ERROR: Ya existe un turismo con esa matrícula.");
			}
		}

		@Override
		public Vehiculo buscar(Vehiculo vehiculo) {

			if (vehiculo == null) {
				throw new NullPointerException("ERROR: No se puede buscar un turismo nulo.");
			}

			if (!coleccionTurismo.contains(vehiculo)) {
				vehiculo = null;
			}
			return vehiculo;

		}

		@Override
		public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {

			if (vehiculo == null) {
				throw new NullPointerException("ERROR: No se puede borrar un turismo nulo.");
			}

			if (coleccionTurismo.contains(vehiculo)) {

				coleccionTurismo.remove(vehiculo);

			} else {
				throw new OperationNotSupportedException("ERROR: No existe ningún turismo con esa matrícula.");
			}

		}

	}