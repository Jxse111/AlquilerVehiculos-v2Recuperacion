package java.org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public class Turismo extends Vehiculo {
	int cilindrada;
	int FACTOR_CILINDRADA;
	public String marca;
	public String modelo;
	public String matricula;
	public Turismo(String marca, String modelo, int cilindrada, String matricula) throws IllegalArgumentException {
		 
	}

	public Turismo(Turismo turismo) {
		if (turismo == null) {
			throw new NullPointerException("ERROR: No es posible copiar un turismo nulo.");
		}
		{
			this.marca = turismo.marca;
			this.modelo = turismo.modelo;
			this.cilindrada = turismo.cilindrada;
			this.matricula = turismo.matricula;
		}
	}

	public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) throws IllegalArgumentException {
		if (cilindrada >= 0 && cilindrada <= 5000) {
			this.cilindrada = cilindrada;
		}
		{
			throw new IllegalArgumentException("Cilindrada no es correcta");
		}
	}
	public int getFactorPrecio() {
		return FACTOR_CILINDRADA;
	}

	@Override
	public String toString() {
		return "Turismo [cilindrada=" + cilindrada + ", FACTOR_CILINDRADA=" + FACTOR_CILINDRADA + ", marca=" + marca
				+ ", modelo=" + modelo + ", matricula=" + matricula + "]";
	}

	}


