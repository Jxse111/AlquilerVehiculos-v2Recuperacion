package org.iesalandalus.programacion.alquilervehiculos.vista;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private static final String PATRON_FECHA = "dd/MM/yyyy";
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern(PATRON_FECHA);

	public static void mostrarCabecera(String mensaje) {
		System.out.println(mensaje);
		System.out.println("-".repeat(mensaje.length()));

	}

	public static void mostrarMenu() {
		mostrarCabecera("     MENÚ DE OPCIONES     ");

		for (int i = 0; i < (Opcion.values().length); i++) {
			System.out.println(Opcion.values()[i]);
		}
		System.out.println("");
	}

	private static String leerCadena(String mensaje) {
		System.out.print(mensaje);
		return Entrada.cadena();

	}

	private static Integer leerEntero(String mensaje) {
		System.out.printf("%s", mensaje);
		return Entrada.entero();

	}

	private static LocalDate leerFecha(String mensaje) {
		LocalDate fecha = null;
		try {
			String cadena = leerCadena(mensaje);
			fecha = LocalDate.parse(cadena, FORMATO_FECHA);

		} catch (DateTimeParseException e) {
			System.out.print(e.getMessage());
			fecha = null;
		}
		return fecha;
	}

	public static Opcion elegirOpcion() {
		Opcion opcion = null;
		do {
			try {
				int num = leerEntero("Selecciona una opción: ");

				opcion = Opcion.get(num);
			} catch (NullPointerException e) {
				System.out.println(e.getMessage());
			}
		} while (opcion == null);
		return opcion;
	}

	public static Cliente leerCliente() {

		return new Cliente(leerCadena("Introduce el nombre del cliente: "),
				leerCadena("Introduce el DNI del cliente: "), leerCadena("Introduce el teléfono del cliente: "));

	}

	public static Cliente leerClienteDni() {
		return Cliente.getClienteConDni(leerCadena("Introduce el DNI del cliente: "));

	}

	public static String leerNombre() {
		return leerCadena("Introduce el nombre: ");

	}

	public static String leerTelefono() {
		return leerCadena("Introduce el teléfono: ");

	}

	public static Turismo leerTurismo() {
		return new Turismo(leerCadena("Introduce la marca del turismo: "),
				(leerCadena("Introduce el modelo del turismo: ")),
				(leerEntero("Introduce la cilindrada del turismo: ")),
				(leerCadena("Introduce la matrícula del turismo: ")));

	}

	public static Turismo leerTurismoMatricula() {
		return Turismo.getTurismoConMatricula(leerCadena("Introduce la matrícula del turismo: "));

	}

	public static Alquiler leerAlquiler() {
		return new Alquiler(leerClienteDni(), leerTurismoMatricula(), leerFecha("Introduce la fecha del alquiler: "));

	}

	public static LocalDate leerFechaDevolucion() {
		return leerFecha("Introduce la fecha de devolución: ");

	}
}