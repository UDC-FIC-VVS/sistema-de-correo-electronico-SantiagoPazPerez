package gal.udc.fic.vvs.email.correo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Vector;

import org.junit.Test;

import gal.udc.fic.vvs.email.archivo.Texto;

public class CarpetaLimitadaTest {

	@Test
	public void testCrearCarpetaLimitada() {
		int tamano = 10;
		String nombreCarpeta = "nombre Carpeta";
		Carpeta carpeta = new Carpeta(nombreCarpeta);
		CarpetaLimitada carpetaLimitada = new CarpetaLimitada(carpeta, tamano);
		assertEquals(carpetaLimitada.obtenerTamaño(), 0);
		assertEquals(carpetaLimitada.obtenerIcono(), Correo.ICONO_CARPETA);
	}

	@Test
	public void testCarpetaLimitadaAnadirCorreoEliminar() {
		int tamano = 10;
		String nombreCarpeta = "nombre Carpeta";
		Carpeta carpeta = new Carpeta(nombreCarpeta);
		CarpetaLimitada carpetaLimitada = new CarpetaLimitada(carpeta, tamano);

		Mensaje mensaje = new Mensaje(new Texto("nombre mensaje", "contenido Mensaje")); // Contenid: String count:17
		try {
			carpetaLimitada.añadir(mensaje);
		} catch (OperacionInvalida e) {
			e.printStackTrace();
		}
		assertEquals(carpetaLimitada.obtenerTamaño(), 17); // EL contenido
		// Esta sin leer
		assertEquals(carpetaLimitada.obtenerNoLeidos(), 1);
		try {
			carpetaLimitada.eliminar(mensaje);
		} catch (OperacionInvalida e) {
			e.printStackTrace();
		}
		assertEquals(carpetaLimitada.obtenerTamaño(), 0);
		assertEquals(carpetaLimitada.obtenerNoLeidos(), 0);
	}

	@Test
	public void testCarpetaLimitadaAnadirCorreoLeer() {
		int tamano = 10;
		String nombreCarpeta = "nombre Carpeta";
		Carpeta carpeta = new Carpeta(nombreCarpeta);
		CarpetaLimitada carpetaLimitada = new CarpetaLimitada(carpeta, tamano);

		Mensaje mensaje = new Mensaje(new Texto("nombre mensaje", "contenido Mensaje")); // Contenid: String count:17
		try {
			carpetaLimitada.añadir(mensaje);
		} catch (OperacionInvalida e) {
			e.printStackTrace();
		}
		assertEquals(carpetaLimitada.obtenerTamaño(), 17); // EL contenido
		// Esta sin leer
		assertEquals(carpetaLimitada.obtenerNoLeidos(), 1);
		carpetaLimitada.establecerLeido(true);

		assertEquals(carpetaLimitada.obtenerNoLeidos(), 0);
		assertEquals(carpetaLimitada.obtenerTamaño(), 17);
	}

	@Test
	public void testBuscarEnCarpetaConMensaje() {
		int tamano = 10;
		String nombreCarpeta = "nombre Carpeta";
		Carpeta carpeta = new Carpeta(nombreCarpeta);
		CarpetaLimitada carpetaLimitada = new CarpetaLimitada(carpeta, tamano);

		Mensaje mensaje = new Mensaje(new Texto("nombre mensaje", "contenido Mensaje")); // Contenid: String count:17
		Mensaje segundoMensaje = new Mensaje(new Texto("nombre segundo mensaje", "contenido segundo Mensaje"));
		try {
			carpetaLimitada.añadir(mensaje);
			carpetaLimitada.añadir(segundoMensaje);
		} catch (OperacionInvalida e) {
			e.printStackTrace();
		}
		Vector<?> resultadoPrimeraBusqueda = (Vector<Mensaje>) carpetaLimitada.buscar("segundo");
		Vector<?> resultadoSegundaBusqueda = (Vector<Mensaje>) carpetaLimitada.buscar("mensaje");

		assertEquals(resultadoPrimeraBusqueda.get(0), segundoMensaje);
		assertEquals(resultadoPrimeraBusqueda.size(), 1);

		assertEquals(resultadoSegundaBusqueda.get(0), mensaje);
		assertEquals(resultadoSegundaBusqueda.get(1), segundoMensaje);
	}

	@Test
	public void testPonerPadreDevuelvePadre() {
		int tamano = 10;
		String nombreCarpeta = "nombre Carpeta";
		Carpeta carpeta = new Carpeta(nombreCarpeta);
		Carpeta otraCarpeta = new Carpeta(nombreCarpeta);
		CarpetaLimitada carpetaLimitada = new CarpetaLimitada(carpeta, tamano);

		carpetaLimitada.establecerPadre(otraCarpeta);
		assertEquals(carpetaLimitada.obtenerPadre(), otraCarpeta);

	}

	@Test
	public void testExplorarCarpetaLimitada() {
		Collection explorado = null;
		int tamano = 10;
		String nombreCarpeta = "nombre Carpeta";
		Carpeta carpeta = new Carpeta(nombreCarpeta);
		CarpetaLimitada carpetaLimitada = new CarpetaLimitada(carpeta, tamano);

		Mensaje mensaje = new Mensaje(new Texto("nombre mensaje", "contenido Mensaje")); // Contenid: String count:17
		Mensaje segundoMensaje = new Mensaje(new Texto("nombre segundo mensaje", "contenido segundo Mensaje"));
		try {
			carpetaLimitada.añadir(mensaje);
			carpetaLimitada.añadir(segundoMensaje);
		} catch (OperacionInvalida e) {
			e.printStackTrace();
		}
		try {
			explorado = carpetaLimitada.explorar();
		} catch (OperacionInvalida e) {
			e.printStackTrace();
		}
		assertTrue("Deberia contener el primer mensaje", explorado.contains(mensaje));
		assertTrue("Tambien deberia contener el segundo", explorado.contains(segundoMensaje));
	}

	@Test
	public void testObtenerRuta() {
		int tamano = 10;
		String nombreCarpeta = "nombre Carpetaaa";
		Carpeta carpeta = new Carpeta(nombreCarpeta);
		CarpetaLimitada carpetaLimitada = new CarpetaLimitada(carpeta, tamano);

		Mensaje mensaje = new Mensaje(new Texto("nombre mensaje", "contenido Mensaje")); // Contenid: String count:17
		Mensaje segundoMensaje = new Mensaje(new Texto("nombre segundo mensaje", "contenido segundo Mensaje"));
		try {
			carpetaLimitada.añadir(mensaje);
			carpetaLimitada.añadir(segundoMensaje);
		} catch (OperacionInvalida e) {
			e.printStackTrace();
		}

		String resultado = carpetaLimitada.obtenerRuta();
		assertEquals(resultado, "nombre Carpetaaa (2)");
	}

	@Test
	public void testVisualizacion() {
		int tamano = 10;
		String nombreCarpeta = "nombre Carpetaaa";
		Carpeta carpeta = new Carpeta(nombreCarpeta);
		CarpetaLimitada carpetaLimitada = new CarpetaLimitada(carpeta, tamano);

		Mensaje mensaje = new Mensaje(new Texto("nombre mensaje", "contenido Mensaje")); // Contenid: String count:17
		Mensaje segundoMensaje = new Mensaje(new Texto("nombre segundo mensaje", "contenido segundo Mensaje"));
		try {
			carpetaLimitada.añadir(mensaje);
			carpetaLimitada.añadir(segundoMensaje);
		} catch (OperacionInvalida e) {
			e.printStackTrace();
		}

		String previsualizacion = carpetaLimitada.obtenerPreVisualizacion();
		String visualizacion = carpetaLimitada.obtenerVisualizacion();
		assertEquals(previsualizacion, visualizacion);
	}

}
