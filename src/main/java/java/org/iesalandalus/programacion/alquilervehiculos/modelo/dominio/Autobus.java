package java.org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

public class Autobus extends Vehiculo {

	private static final int FACTOR_PLAZAS=2;
	private int plazas;
	
	public Autobus(String marca, String modelo, int plazas,String matricula) {
		super();
		if (plazas<7||plazas>100) {
			throw new IllegalArgumentException("ERROR: Las plazas no son correctas.");
		}
		setPlazas(plazas);
	}
	public Autobus(Autobus autobus) {
		super();
		setPlazas(autobus.getPlazas());
	}

	public int getPlazas() {
		return plazas;
	}

	private void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	@Override
	public int getFactorPrecio() {
		return plazas*FACTOR_PLAZAS;
	}
	@Override
	public String toString() {
		return String.format("%s %s (%d plazas) - %s", marca, modelo, plazas, matricula);
	}
	

}