package java.org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

	public class Furgoneta extends Vehiculo {

		private static final int FACTOR_PMA = 100;
		private static final int FACTOR_PLAZAS = 1;
		private int pma;
		private int plazas;

		public Furgoneta(String marca, String modelo, int pma, int plazas, String matricula) {
			super();
			if (plazas < 2 || plazas > 9) {
				throw new IllegalArgumentException("ERROR: Las plazas no son correctas.");
			}
			setPlazas(plazas);
			if (pma < 1000 || pma > 10000) {
				throw new IllegalArgumentException("ERROR: El PMA no es correcto.");
			}
			setPma(pma);

		}

		public Furgoneta(Furgoneta furgoneta) {
			super();
			setPlazas(furgoneta.getPlazas());
			setPma(furgoneta.getPma());
		}

		public int getPma() {
			return pma;
		}

		public int getPlazas() {
			return plazas;
		}

		private void setPma(int pma) {
			this.pma = pma;
		}

		private void setPlazas(int plazas) {
			this.plazas = plazas;
		}

		@Override
		public int getFactorPrecio() {
			return pma / FACTOR_PMA + plazas * FACTOR_PLAZAS;
		}

		@Override
		public String toString() {
			return String.format("%s %s (%d kg, %d plazas) - %s", marca, modelo, pma, plazas, matricula);
		}

	}
