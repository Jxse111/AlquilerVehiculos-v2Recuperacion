package java.org.iesalandalus.programacion.alquilervehiculos.modelo.ficheros;
-
import java.io.File;
import java.org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import java.org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import java.org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Vehiculos implements IVehiculos {

	private static final File FICHERO_VEHICULOS = new File(
			String.format("%s%s%s", "datos", File.separator, "vehiculos.xml"));
	private static final String RAIZ = "vehiculos";
	private static final String VEHICULO = "vehiculo";
	private static final String MARCA = "marca";
	private static final String MODELO = "modelo";
	private static final String MATRICULA = "matricula";
	private static final String CILINDRADA = "cilindrada";
	private static final String PLAZAS = "plazas";
	private static final String PMA = "pma";
	private static final String TIPO = "tipo";
	private static final String TURISMO = "turismo";
	private static final String AUTOBUS = "autobus";
	private static final String FURGONETA = "furgoneta";

	private static Vehiculos instancia;

	private List<Vehiculo> coleccionVehiculos;

	private Vehiculos() {

		coleccionVehiculos = new ArrayList<>();
	}

	static Vehiculos getInstancia() {
		if (instancia == null) {
			instancia = new Vehiculos();
		}
		return instancia;
	}

	public void comenzar() {
		leerDom(UtilidadesXml.leerXmlDeFichero(FICHERO_VEHICULOS));

	}

	private void leerDom(Document documentoXml) {
		NodeList vehiculos = documentoXml.getElementsByTagName(VEHICULO);
		for (int i = 0; i < vehiculos.getLength(); i++) {
			Node vehiculo = vehiculos.item(i);
			if (vehiculo.getNodeType() == Node.ELEMENT_NODE) {
				try {
					insertar(getVehiculo((Element) vehiculo));

				} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
					System.out.printf("%s", e.getMessage());
				}
			}
		}
		System.out.println("Fichero XML de Vehíclos leído correctamente.");
	}

	private Vehiculo getVehiculo(Element elemento) {

		String marca = elemento.getAttribute(MARCA);
		String matricula = elemento.getAttribute(MATRICULA);
		String modelo = elemento.getAttribute(MODELO);
		int plazas = Integer.parseInt(elemento.getAttribute(PLAZAS));
		int pma = Integer.parseInt(elemento.getAttribute(PMA));
		String tipo = elemento.getAttribute(TIPO);

		Vehiculo auxVehiculo = null;

		if (tipo.equals(AUTOBUS)) {
			auxVehiculo = new Autobus(marca, modelo, plazas, matricula);
		}

		if (tipo.equals(TURISMO)) {
			int cilindrada = Integer.parseInt(elemento.getAttribute(CILINDRADA));
			auxVehiculo = new Turismo(marca, modelo, cilindrada, matricula);
		}

		if (tipo.equals(FURGONETA)) {
			auxVehiculo = new Furgoneta(marca, modelo, pma, plazas, matricula);

		}
		return auxVehiculo;
	}

	public void terminar() {
		UtilidadesXml.escribirXmlAFichero(crearDom(), FICHERO_VEHICULOS);

	}

	private Document crearDom() {
		DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
		Document documentoXml = null;
		if (constructor != null) {
			documentoXml = constructor.newDocument();
			documentoXml.appendChild(documentoXml.createElement(RAIZ));
			for (Vehiculo vehiculo : coleccionVehiculos) {
				Element elementoVehiculo = getElemento(documentoXml, vehiculo);
				documentoXml.getDocumentElement().appendChild(elementoVehiculo);
			}
		}
		return documentoXml;

	}

	private Element getElemento(Document documentoXml, Vehiculo vehiculo) {
		Element elementoVehiculo = documentoXml.createElement(VEHICULO);
		elementoVehiculo.setAttribute(MARCA, vehiculo.getMarca());
		elementoVehiculo.setAttribute(MODELO, vehiculo.getModelo());
		elementoVehiculo.setAttribute(MATRICULA, vehiculo.getMatricula());
		return elementoVehiculo;
	}

	@Override
	public List<Vehiculo> get() {
		return new ArrayList<>(coleccionVehiculos);
	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede insertar un vehículo nulo.");
		}
		int indice = coleccionVehiculos.indexOf(vehiculo);
		if (indice != -1) {
			throw new OperationNotSupportedException("ERROR: Ya existe un vehículo con esa matrícula.");
		}
		coleccionVehiculos.add(vehiculo);
	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede buscar un vehículo nulo.");
		}
		int indice = coleccionVehiculos.indexOf(vehiculo);
		Vehiculo aux = null;
		if (indice != -1) {
			aux = coleccionVehiculos.get(indice);
		}
		return aux;
	}

	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede borrar un vehículo nulo.");
		}
		int indice = coleccionVehiculos.indexOf(vehiculo);
		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún vehículo con esa matrícula.");
		}
		coleccionVehiculos.remove(indice);
	}
}