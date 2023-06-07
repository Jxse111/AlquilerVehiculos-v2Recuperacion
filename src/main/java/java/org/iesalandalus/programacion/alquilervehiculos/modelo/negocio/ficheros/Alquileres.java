package java.org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.io.File;
import java.org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import java.org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class Alquileres implements IAlquileres {

	private static final File FICHERO_ALQUILERES = new File(
			String.format("%s%s%s", "datos", File.separator, "alquileres.xml"));
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/mm/yy");
	private static final String RAIZ = "alquileres";
	private static final String ALQUILER = "alquiler";
	private static final String CLIENTE = "clientes";
	private static final String VEHICULO = "vehiculo";
	private static final String FECHA_ALQUILER = "fechaAlquiler";
	private static final String FECHA_DEVOLUCION = "fechaDevolucion";

	private static Alquileres instancia;
	private List<Alquiler> coleccionAlquileres;

	private Alquileres() {

		coleccionAlquileres = new ArrayList<>();
	}

	static Alquileres getInstancia() {
		if (instancia == null) {
			instancia = new Alquileres();
		}
		return instancia;
	}

	public void comenzar() {
		leerDom(UtilidadesXml.leerXmlDeFichero(FICHERO_ALQUILERES));

	}

	private void leerDom(Document documentoXml) {
		NodeList alquileres = documentoXml.getElementsByTagName(ALQUILER);
		for (int i = 0; i < alquileres.getLength(); i++) {
			Node alquiler = alquileres.item(i);
			try {

				if (alquiler.getNodeType() == Node.ELEMENT_NODE) {
					insertar(getAlquiler((Element) alquiler));
				}

			} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
				System.out.printf("%s", e.getMessage());
			}
		}
		System.out.println("Fichero XML de Alquileres leído correctamente.");
	}

	private Alquiler getAlquiler(Element elemento) throws OperationNotSupportedException {
		Cliente cliente = Cliente.getClienteConDni(elemento.getAttribute(CLIENTE));
		Vehiculo vehiculo = Vehiculo.getVehiculoConMatricula(elemento.getAttribute(VEHICULO));
		LocalDate fechaAlquiler = LocalDate.parse(FECHA_ALQUILER, FORMATO_FECHA);
		LocalDate fechaDevolucion = LocalDate.parse(FECHA_DEVOLUCION, FORMATO_FECHA);

		Alquiler auxAlquiler;

		if (Clientes.getInstancia().buscar(cliente) == null) {
			throw new NullPointerException("ERROR: No se encuentra un Cliente.");
		}
		if (Vehiculos.getInstancia().buscar(vehiculo) == null) {
			throw new NullPointerException("ERROR: No se encuentra un Vehículo.");
		}
		auxAlquiler = new Alquiler(cliente, vehiculo, fechaAlquiler);

		auxAlquiler.devolver(fechaDevolucion);
		return auxAlquiler;
	}

	public void terminar() {
		UtilidadesXml.escribirXmlAFichero(crearDom(), FICHERO_ALQUILERES);
	}

	private Document crearDom() {
		DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
		Document documentoXml = null;
		if (constructor != null) {
			documentoXml = constructor.newDocument();
			documentoXml.appendChild(documentoXml.createElement(RAIZ));
			for (Alquiler alquiler : coleccionAlquileres) {
				Element elementoAlquiler = getElemento(documentoXml, alquiler);
				documentoXml.getDocumentElement().appendChild(elementoAlquiler);
			}
		}
		return documentoXml;

	}

	private Element getElemento(Document documentoXml, Alquiler alquiler) {
		Element elementoAlquiler = documentoXml.createElement(ALQUILER);
		elementoAlquiler.setAttribute(CLIENTE, alquiler.getCliente().getDni());
		alquiler.getVehiculo();
		elementoAlquiler.setAttribute(VEHICULO, Vehiculo.getMatricula());
		elementoAlquiler.setAttribute(FECHA_ALQUILER, alquiler.getFechaAlquiler().format(FORMATO_FECHA));
		return elementoAlquiler;
	}

	@Override
	public List<Alquiler> get() {
		return new ArrayList<>(coleccionAlquileres);
	}

	public List<Alquiler> get(Cliente cliente) {
		List<Alquiler> listaTemporal = new ArrayList<>();

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getCliente().equals(cliente)) {
				listaTemporal.add(alquiler);
			}
		}
		return listaTemporal;

	}

	public List<Alquiler> get(Vehiculo turismo) {
		List<Alquiler> listaTemporal = new ArrayList<>();

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getVehiculo().equals(turismo)) {
				listaTemporal.add(alquiler);
			}
		}
		return listaTemporal;
	}

	private void comprobarAlquiler(Cliente cliente, Vehiculo turismo, LocalDate fechaAlquiler)
			throws OperationNotSupportedException {
		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getFechaDevolucion() == null) {

				if (alquiler.getCliente().equals(cliente)) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
				}
				if (alquiler.getVehiculo().equals(turismo)) {
					throw new OperationNotSupportedException("ERROR: El turismo está actualmente alquilado.");

				}
			} else {
				if (alquiler.getCliente().equals(cliente) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
						|| alquiler.getFechaDevolucion() == fechaAlquiler)) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
				}
				if (alquiler.getVehiculo().equals(turismo) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
						|| alquiler.getFechaDevolucion() == fechaAlquiler)) {
					throw new OperationNotSupportedException("ERROR: El turismo tiene un alquiler posterior.");
				}
			}
		}
	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}

		comprobarAlquiler(alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaAlquiler());
		coleccionAlquileres.add(alquiler);
	}

	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {

		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler de un cliente nulo.");
		}
		if (getAlquilerAbierto(cliente) == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler abierto para ese cliente.");
		}
	}

	private Alquiler getAlquilerAbierto(Cliente cliente) {
		Alquiler alquilerEncontrado = null;
		Iterator<Alquiler> iterador = coleccionAlquileres.iterator();

		while (iterador.hasNext() && alquilerEncontrado == null) {
			Alquiler alquilerRecorrido = iterador.next();

			if (alquilerRecorrido.getCliente().equals(cliente) && alquilerRecorrido.getFechaDevolucion() == null) {
				alquilerEncontrado = alquilerRecorrido;
			}
		}
		return alquilerEncontrado;
	}

	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler de un vehículo nulo.");
		}

		if (getAlquilerAbierto(vehiculo) == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler abierto para ese vehículo.");
		}
	}

	private Alquiler getAlquilerAbierto(Vehiculo vehiculo) {
		Alquiler alquilerEncontrado = null;
		Iterator<Alquiler> iterador = coleccionAlquileres.iterator();

		while (iterador.hasNext() && alquilerEncontrado == null) {
			Alquiler alquilerRecorrido = iterador.next();

			if (alquilerRecorrido.getVehiculo().equals(vehiculo) && alquilerRecorrido.getFechaDevolucion() == null) {
				alquilerEncontrado = alquilerRecorrido;
			}
		}
		return alquilerEncontrado;
	}

	public void borrar(Alquiler alquileres) throws OperationNotSupportedException {
		if (alquileres == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}
		int indice = coleccionAlquileres.indexOf(alquileres);
		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
		coleccionAlquileres.remove(indice);
	}

	public Alquiler buscar(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}
		int indice = coleccionAlquileres.indexOf(alquiler);
		Alquiler aux = null;
		if (indice != -1) {
			aux = coleccionAlquileres.get(indice);
		}
		return aux;
	}

}