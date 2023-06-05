package org.iesalandalus.programacion.alquilervehiculos.vista;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;

public class Vista {
	private Controlador controlador;

	public void setControlador(Controlador controlador) {
		if (controlador == null) {
			throw new NullPointerException("No puedes asignar un controlador nulo");
		}
		this.controlador = controlador;
	}

	public void comenzar() {
		Opcion elegirOpcion = null;
		do {
			Consola.mostrarMenu();
			elegirOpcion = Consola.elegirOpcion();
			ejecutar(elegirOpcion);
		} while (elegirOpcion != Opcion.SALIR);
	}

	public void terminar() {
		String mensaje = "Hasta luego Lucas!";
		System.out.printf("%n%s", mensaje);
	}

	private void ejecutar(Opcion opcion) {
		switch (opcion.ordinal()) {
		case 0:
			terminar();
			break;
		case 1:
			insertarCliente();
			break;
		case 2:
			insertarTurismo();
			break;
		case 3:
			insertarAlquiler();
			break;
		case 4:
			buscarCliente();
			break;
		case 5:
			buscarTurismo();
			break;
		case 6:
			buscarAlquiler();
			break;
		case 7:
			modificarCliente();
			break;
		case 8:
			devolverAlquiler();
			break;
		case 9:
			borrarCliente();
			break;
		case 10:
			borrarTurismo();
			break;
		case 11:
			borrarAlquiler();
			break;
		case 12:
			listarClientes();
			break;
		case 13:
			listarTurismos();
			break;
		case 14:
			listarAlquileres();
			break;
		case 15:
			listarAlquileresCliente();
			break;
		case 16:
			listarAlquileresTurismo();
			break;
		}

	}

	private void insertarCliente() {
		try {
			Consola.mostrarCabecera("Insertar cliente");
			controlador.insertar(Consola.leerCliente());
			String mensaje = "Cliente insertado correctamente";
			System.out.printf("%n%s%n%n", mensaje);
		} catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	private void insertarTurismo() {
		try {
			Consola.mostrarCabecera("Insertar turismo");
			controlador.insertar(Consola.leerTurismo());
			String mensaje = "Turismo insertado correctamente";
			System.out.printf("%n%s%n%n", mensaje);
		} catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	private void insertarAlquiler() {
		try {
			Consola.mostrarCabecera("Insertar alquiler");
			controlador.insertar(Consola.leerAlquiler());
			String mensaje = "Alquiler insertado correctamente";
			System.out.printf("%n%s%n%n", mensaje);
		} catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	private void buscarCliente() {
		try {
			Consola.mostrarCabecera("Buscar cliente");
			System.out.println(controlador.buscar(Consola.leerClienteDni()));
			String mensaje = "Cliente buscado correctamente";
			System.out.printf("%n%s%n%n", mensaje);
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	private void buscarTurismo() {
		try {
			Consola.mostrarCabecera("Buscar turismo");
			System.out.println(controlador.buscar(Consola.leerTurismoMatricula()));
			String mensaje = "Turismo buscado correctamente";
			System.out.printf("%n%s%n%n", mensaje);
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	private void buscarAlquiler() {

		try {
			Consola.mostrarCabecera("Buscar alquiler");
			System.out.println(controlador.buscar(Consola.leerAlquiler()));
			String mensaje = "Alquiler buscado correctamente";
			System.out.printf("%n%s%n%n", mensaje);
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	private void modificarCliente() {
		try {
			Consola.mostrarCabecera("Modificar cliente");
			controlador.modificar(Consola.leerCliente(), Consola.leerNombre(), Consola.leerTelefono());
			String mensaje = "Cliente modificado correctamente";
			System.out.printf("%n%s%n%n", mensaje);
		} catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	private void devolverAlquiler() {
		try {
			Consola.mostrarCabecera("Devolver alquiler");
			controlador.devolver(Consola.leerAlquiler(), Consola.leerFechaDevolucion());
			String mensaje = "Alquiler devuelto correctamente";
			System.out.printf("%n%s%n%n", mensaje);
		} catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	private void borrarCliente() {
		try {
			Consola.mostrarCabecera("Borrar cliente");
			controlador.borrar(Consola.leerClienteDni());
			String mensaje = "Cliente borrado correctamente";
			System.out.printf("%n%s%n%n", mensaje);
		} catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	private void borrarTurismo() {
		try {
			Consola.mostrarCabecera("Borrar turismo");
			controlador.borrar(Consola.leerTurismoMatricula());
			String mensaje = "Turismo borrado correctamente";
			System.out.printf("%n%s%n%n", mensaje);
		} catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}

	}

	private void borrarAlquiler() {
		try {
			Consola.mostrarCabecera("Borrar alquiler");
			controlador.borrar(Consola.leerAlquiler());
			String mensaje = "Alquiler borrado correctamente";
			System.out.printf("%n%s%n%n", mensaje);
		} catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	private void listarClientes() {
		try {
			Consola.mostrarCabecera("Listar clientes");
			for (Cliente clienteLista : controlador.getClientes()) {
				System.out.println(clienteLista);
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("");
	}

	private void listarTurismos() {
		try {
			Consola.mostrarCabecera("Listar turismos");
			for (Turismo turismoLista : controlador.getTurismos()) {
				System.out.println(turismoLista);
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("");

	}

	private void listarAlquileres() {
		try {
			Consola.mostrarCabecera("Listar alquileres");
			for (Alquiler alquilerLista : controlador.getAlquileres()) {
				System.out.println(alquilerLista);
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("");

	}

	private void listarAlquileresCliente() {
		try {
			Consola.mostrarCabecera("Listar alquileres por clientes");
			for (Alquiler alquilerListaCliente : controlador.getAlquileres(Consola.leerClienteDni())) {
				System.out.println(alquilerListaCliente);
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}

	}

	private void listarAlquileresTurismo() {
		try {
			Consola.mostrarCabecera("Listar alquileres por turismos");
			for (Alquiler alquilerListaTurismo : controlador.getAlquileres(Consola.leerTurismoMatricula())) {
				System.out.println(alquilerListaTurismo);
				System.out.println("");
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("");

	}
}