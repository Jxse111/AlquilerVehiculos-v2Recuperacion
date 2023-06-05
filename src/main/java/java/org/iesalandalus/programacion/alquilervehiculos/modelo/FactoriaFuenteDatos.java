package java.org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.org.iesalandalus.programacion.alquilervehiculos.modelo.ficheros.FuenteDatosMemoria;
import java.org.iesalandalus.programacion.alquilervehiculos.modelo.ficheros.IFuenteDatos;

public enum FactoriaFuenteDatos {

	MEMORIA {
		
		@Override
		public IFuenteDatos crear() {
			// TODO Auto-generated method stub
			return new FuenteDatosMemoria();
		}
	};
	
	public abstract IFuenteDatos crear();
}