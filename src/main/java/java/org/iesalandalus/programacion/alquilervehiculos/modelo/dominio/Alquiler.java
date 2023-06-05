package java.org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.naming.OperationNotSupportedException;

public class Alquiler {
	protected static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd\\MM\\yyyy");
	private static final int PRECIO_DIA = 20;

	private LocalDate fechaAlquiler;
	private LocalDate fechaDevolucion;
	private Cliente cliente;
	private Turismo turismo;

	public Alquiler(Cliente cliente, Turismo turismo, LocalDate fechaAlquiler) {
		setCliente(cliente);
		setTurismo(turismo);
		setFechaAlquiler(fechaAlquiler);
	}

	public Alquiler(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No es posible copiar un alquiler nulo.");
		}
		this.cliente = new Cliente(alquiler.getCliente());
		this.turismo = new Turismo(alquiler.getTurismo());
		this.fechaAlquiler = alquiler.getFechaAlquiler();
		this.fechaDevolucion = alquiler.getFechaDevolucion();
	}

	public static DateTimeFormatter getFormatoFecha() {
		return FORMATO_FECHA;
	}

	public static int getprecioDia() {
		return PRECIO_DIA;
	}

	public LocalDate getFechaAlquiler() {
		return fechaAlquiler;
	}

	public void setFechaAlquiler(LocalDate fechaAlquiler) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: El cliente no puede ser nulo.");
		}
		this.fechaAlquiler = fechaAlquiler;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Turismo getTurismo() {
		return turismo;
	}

	public void setTurismo(Turismo turismo) {
		this.turismo = turismo;
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public void devolver(LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (this.fechaDevolucion != null) {
			throw new OperationNotSupportedException("ERROR: La devolución ya estaba registrada.");
		}
		setFechaDevolucion(fechaDevolucion);

	}

	@Override
	public String toString() {
		return "Alquiler [fechaAlquiler=" + fechaAlquiler + ", fechaDevolucion=" + fechaDevolucion + ", cliente="
				+ cliente + ", turismo=" + turismo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, fechaAlquiler, fechaDevolucion, turismo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Alquiler))
			return false;
		Alquiler other = (Alquiler) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(fechaAlquiler, other.fechaAlquiler)
				&& Objects.equals(fechaDevolucion, other.fechaDevolucion) && Objects.equals(turismo, other.turismo);
	}
}
