package java.org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.io.File;
import java.org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class Clientes implements IClientes {

	private List<Cliente> coleccionClientes;
	private static final File FICHERO_CLIENTES = new File(
			String.format("%s%s%s", "datos", File.separator, "clientes.xml"));
	private static final String RAIZ = "clientes";
	private static final String CLIENTE = "cliente";
	private static final String NOMBRE = "nombre";
	private static final String DNI = "dni";
	private static final String TELEFONO = "telefono";

	private static Clientes instancia;

	private Clientes() {

		coleccionClientes = new ArrayList<>();

	}

	static Clientes getInstancia() { // PATRON SINGLENTON
		if (instancia == null) {
			instancia = new Clientes();
		}
		return instancia;

	}

	public void comenzar() {
		leerDom(UtilidadesXml.leerXmlDeFichero(FICHERO_CLIENTES));
	}

	private void leerDom(Document documentoXml) {
		NodeList clientes = documentoXml.getElementsByTagName(CLIENTE);
		for (int i = 0; i < clientes.getLength(); i++) {
			Node cliente = clientes.item(i);
			if (cliente.getNodeType() == Node.ELEMENT_NODE) {
				try {

					insertar(getCliente((Element) cliente));
				} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
					System.out.printf("%s", e.getMessage());
				}
			}
		}
		System.out.println("Fichero XML de Clientes leído correctamente.");
	}

	private Cliente getCliente(Element elemento) {
		String nombre = elemento.getAttribute(NOMBRE);
		String dni = elemento.getAttribute(DNI);
		String telefono = elemento.getAttribute(TELEFONO);

		return new Cliente(nombre, dni, telefono);

	}

	public void terminar() {
		UtilidadesXml.escribirXmlAFichero(crearDom(), FICHERO_CLIENTES);
	}

	private Document crearDom() {
		DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
		Document documentoXml = null;
		if (constructor != null) {
			documentoXml = constructor.newDocument();
			documentoXml.appendChild(documentoXml.createElement(RAIZ));
			for (Cliente cliente : coleccionClientes) {
				Element elementoPersona = getElemento(documentoXml, cliente);
				documentoXml.getDocumentElement().appendChild(elementoPersona);
			}
		}
		return documentoXml;
	}

	private Element getElemento(Document documentoXml, Cliente cliente) {
		Element elementoCliente = documentoXml.createElement(CLIENTE);
		elementoCliente.setAttribute(NOMBRE, cliente.getNombre());
		elementoCliente.setAttribute(DNI, cliente.getDni());
		elementoCliente.setAttribute(TELEFONO, cliente.getTelefono());
		return elementoCliente;
	}

	@Override
	public List<Cliente> get() {
		return new ArrayList<>(coleccionClientes);
	}

	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		}
		int indice = coleccionClientes.indexOf(cliente);

		if (indice != -1) {
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI.");

		}

		coleccionClientes.add(cliente);
	}

	@Override
	public Cliente buscar(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");

		}
		int indice = coleccionClientes.indexOf(cliente);
		Cliente aux = null;
		if (indice != -1) {
			aux = coleccionClientes.get(indice);
		}
		return aux;
	}

	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		}
		int indice = coleccionClientes.indexOf(cliente);
		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}

		coleccionClientes.remove(indice);
	}

	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {

		int indice = coleccionClientes.indexOf(cliente);

		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede modificar un cliente nulo.");
		}

		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}

		if ((nombre != null) && (!nombre.isBlank())) {
			buscar(cliente).setNombre(nombre);
		}
		if ((telefono != null) && (!telefono.isBlank())) {
			buscar(cliente).setTelefono(telefono);
		}
	}
}