package java.org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.FuenteDatosMemoria;
import java.org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;

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