package gal.udc.fic.vvs.email.correo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.Test;

import gal.udc.fic.vvs.email.archivo.Texto;

public class CarpetaTest {

	private static String nombreCarpeta = "nombre de la Carpeta";

	@Test
	public void testCrearCarpeta() {
		Carpeta carpeta = new Carpeta(nombreCarpeta);
		assertEquals(carpeta.obtenerNoLeidos(), 0);
		assertEquals(carpeta.obtenerTamaño(), 0);
		assertEquals(carpeta.obtenerIcono(), Correo.ICONO_CARPETA);
		assertEquals(carpeta.obtenerPreVisualizacion(), nombreCarpeta);
		assertEquals(carpeta.obtenerPreVisualizacion(), carpeta.obtenerVisualizacion());
	}

	@Test
	public void testExplorar() {
		Carpeta carpeta = new Carpeta(nombreCarpeta);
		try {
			assertTrue(carpeta.explorar().isEmpty());
		} catch (OperacionInvalida e) {
			e.printStackTrace();
		}
	}

	@Test(expected = OperacionInvalida.class)
	public void testObtenerHijosSinHijos() throws OperacionInvalida {
		Carpeta carpeta = new Carpeta(nombreCarpeta);
		carpeta.obtenerHijo(1);
	}

	@Test
	public void testAnadirCorreoCarpetaSinLeerLeerlo() {
		Carpeta carpeta = new Carpeta(nombreCarpeta);
		Mensaje mensaje = new Mensaje(new Texto("nombre mensaje", "contenido Mensaje")); // Contenid: String count:17
		try {
			carpeta.añadir(mensaje);
		} catch (OperacionInvalida e) {
			e.printStackTrace();
		}
		assertEquals(carpeta.obtenerTamaño(), 17); // EL contenido
		// Esta sin leer
		assertEquals(carpeta.obtenerNoLeidos(), 1);
		carpeta.establecerLeido(true);
		assertEquals(carpeta.obtenerNoLeidos(), 0);
	}

	@Test
	public void testAnadirCorreoCarpetaEliminarlo() {
		Carpeta carpeta = new Carpeta(nombreCarpeta);
		Mensaje mensaje = new Mensaje(new Texto("nombre mensaje", "contenido Mensaje")); // Contenid: String count:17
		try {
			carpeta.añadir(mensaje);
		} catch (OperacionInvalida e) {
			e.printStackTrace();
		}
		assertEquals(carpeta.obtenerTamaño(), 17); // EL contenido
		// Esta sin leer
		assertEquals(carpeta.obtenerNoLeidos(), 1);
		try {
			carpeta.eliminar(mensaje);
		} catch (OperacionInvalida e) {
			e.printStackTrace();
		}
		assertEquals(carpeta.obtenerTamaño(), 0);
		assertEquals(carpeta.obtenerNoLeidos(), 0);
	}

	@Test
	public void testObtenerVisualizacionCarpetaConMensaje() {
		Carpeta carpeta = new Carpeta(nombreCarpeta);
		Mensaje mensaje = new Mensaje(new Texto("nombre mensaje", "contenido Mensaje")); // Contenid: String count:17
		try {
			carpeta.añadir(mensaje);
		} catch (OperacionInvalida e) {
			e.printStackTrace();
		}
		assertEquals(carpeta.obtenerVisualizacion(), "nombre de la Carpeta (1)");
	}

	@Test
	public void testBuscarEnCarpetaConMensaje() {
		Carpeta carpeta = new Carpeta(nombreCarpeta);
		Mensaje mensaje = new Mensaje(new Texto("nombre mensaje", "contenido Mensaje")); // Contenid: String count:17
		Mensaje segundoMensaje = new Mensaje(new Texto("nombre segundo mensaje", "contenido segundo Mensaje"));
		try {
			carpeta.añadir(mensaje);
			carpeta.añadir(segundoMensaje);
		} catch (OperacionInvalida e) {
			e.printStackTrace();
		}
		Vector<?> resultadoPrimeraBusqueda = (Vector<Mensaje>) carpeta.buscar("segundo");
		Vector<?> resultadoSegundaBusqueda = (Vector<Mensaje>) carpeta.buscar("mensaje");

		assertEquals(resultadoPrimeraBusqueda.get(0), segundoMensaje);
		assertEquals(resultadoPrimeraBusqueda.size(), 1);

		assertEquals(resultadoSegundaBusqueda.get(0), mensaje);
		assertEquals(resultadoSegundaBusqueda.get(1), segundoMensaje);
	}

	@Test
	public void testEliminarMensajeDeCarpetaMensajeNoExistente() {
		Carpeta carpeta = new Carpeta(nombreCarpeta);
		Mensaje mensaje = new Mensaje(new Texto("nombre mensaje", "contenido Mensaje")); // Contenid: String count:17
		try {
			assertEquals(carpeta.obtenerTamaño(), 0);
			carpeta.eliminar(mensaje);
			assertEquals(carpeta.obtenerTamaño(), 0);
		} catch (OperacionInvalida e) {
			e.printStackTrace();
		}
	}

}
