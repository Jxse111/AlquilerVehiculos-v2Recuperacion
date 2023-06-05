package java.org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public abstract class Vehiculo {
	private static final String ER_MARCA = "(Seat|Land Rover|KIA|Rolls-Royce|SsangYong)";
	private static final String ER_MATRICULA = "[0-9]{4}[BCDFGHJKLMNÑPQRSTVWXYZ]{3}";
	public String marca;
	public String modelo;
	public String matricula;

	protected Vehiculo(String marca, String modelo, String matricula) throws IllegalArgumentException {
		setMarca(marca);
		setModelo(modelo);
		setMatricula(matricula);
	}
		protected Vehiculo(Vehiculo vehiculo) {
			if (vehiculo == null) {
				throw new NullPointerException("ERROR: No es posible copiar un turismo nulo.");
			}
		}
			public static Vehiculo copiar (Vehiculo Vehiculo) {
				return Vehiculo;
			}
			
			public static Vehiculo getVehiculoConMatricula(String matricula) {
				return new Vehiculo();
			}
			public abstract int getFactorPrecio();

		public String getMarca() {
			return marca;
		}
		public void setMarca(String marca) {
			this.marca = marca;
		}
		public String getModelo() {
			return modelo;
		}
		public void setModelo(String modelo) {
			this.modelo = modelo;
		}
		public static String getMatricula() {
			return matricula;
		}
		public void setMatricula(String matricula) {
			this.matricula = matricula;
		}
		@Override
		public int hashCode() {
			return Objects.hash(marca, matricula, modelo);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Vehiculo other = (Vehiculo) obj;
			return Objects.equals(marca, other.marca) && Objects.equals(matricula, other.matricula)
					&& Objects.equals(modelo, other.modelo);
		}
		
}