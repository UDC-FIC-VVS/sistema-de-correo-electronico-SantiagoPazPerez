package gal.udc.fic.vvs.email.correo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.Test;

import gal.udc.fic.vvs.email.archivo.Archivo;
import gal.udc.fic.vvs.email.archivo.Imagen;
import gal.udc.fic.vvs.email.archivo.Texto;

public class CorreoTest {
	private static String contenidoMensaje = "Contenido del mensaje";
	private static String nombreMensaje = "MensajeNombre";

	@Test
	public void testCreacionMensajeCreadoCorrectamente() {
		Mensaje msg = new Mensaje(new Texto(nombreMensaje, contenidoMensaje));
		assertEquals("El mensaje aun no se ha leido ", msg.obtenerNoLeidos(), 1);
		assertEquals(msg.obtenerTamaño(), 21);
	}

	@Test
	public void testCreacionMensajeLeidoCambia() {
		Mensaje msg = new Mensaje(new Texto(nombreMensaje, contenidoMensaje));
		assertEquals("El mensaje aun no se ha leido ", msg.obtenerNoLeidos(), 1);
		msg.establecerLeido(true);
		assertEquals("El mensaje ya se ha leido ", msg.obtenerNoLeidos(), 0);
	}

	@Test
	public void testMensajeNoLeidoIconoNoLeido() {
		Mensaje msg = new Mensaje(new Texto(nombreMensaje, contenidoMensaje));
		assertEquals("El mensaje aun no se ha leido ", msg.obtenerNoLeidos(), 1);
		assertEquals(msg.obtenerIcono(), Correo.ICONO_NUEVO_MENSAJE);
	}

	@Test
	public void testMensajeLeidoIconoLeido() {
		Mensaje msg = new Mensaje(new Texto(nombreMensaje, contenidoMensaje));
		assertEquals("El mensaje aun no se ha leido ", msg.obtenerNoLeidos(), 1);
		msg.establecerLeido(true);
		assertEquals(msg.obtenerIcono(), Correo.ICONO_MENSAJE);
	}

	@Test
	public void testCrearCabeceraComprobarValores() {
		Mensaje mensaje = new Mensaje(new Texto(nombreMensaje, contenidoMensaje));
		Cabecera cabecera = new Cabecera(mensaje, "nombre cabecera", "valor de la cabecera");
		assertEquals(cabecera.obtenerTamaño(), 15 + 20 + 21);
		assertEquals(cabecera.obtenerVisualizacion(),
				"nombre cabecera: valor de la cabecera" + "\n" + "Contenido del mensaje");
	}

	@Test
	public void testCrearAdjuntoComprobarValores() {
		Mensaje mensaje = new Mensaje(new Texto(nombreMensaje, contenidoMensaje));
		Archivo archivoImagen = new Imagen("nombreImagen", "contenidoImagen");
		Adjunto adjunto = new Adjunto(mensaje, archivoImagen);

		assertEquals("numero de caracteres de mensaje y archivo", adjunto.obtenerTamaño(), 36);
		assertEquals("Visualizacion string contenido", adjunto.obtenerVisualizacion(),
				"Contenido del mensaje\n\nAdxunto: nombreImagen(15 bytes, image/png)");
	}

	@Test
	public void testCrearReenvioComprobarValores() {
		Mensaje mensaje = new Mensaje(new Texto(nombreMensaje, contenidoMensaje));
		Reenvio reenvio = new Reenvio(mensaje, mensaje);
		assertEquals("Tamano de los dos mensajes", reenvio.obtenerTamaño(), 42);
		assertEquals("Resultado a string de los mensajes", reenvio.obtenerVisualizacion(),
				"Contenido del mensaje\n\n---- Correo reenviado ----\n\nContenido del mensaje\n---- Fin correo reenviado ----");

	}

	@Test
	public void testMensajePrevisualizarContenidoMenor32() {
		Mensaje mensaje = new Mensaje(new Texto(nombreMensaje, contenidoMensaje));
		assertEquals(mensaje.obtenerPreVisualizacion(), "Contenido del mensaje...");
	}

	@Test
	public void testMensajePrevisualizarContenidoMayor32() {
		String contenidoGrande = "este contenido es mayor a trenta y dos caracteres";
		Mensaje mensaje = new Mensaje(new Texto(nombreMensaje, contenidoGrande));
		assertEquals(mensaje.obtenerPreVisualizacion(), "este contenido es mayor a trenta...");
	}

	@Test
	public void testBuscarEnMensajeEncontrado() {
		String contenidoGrande = "este contenido es mayor a trenta y dos caracteres";
		Mensaje mensaje = new Mensaje(new Texto(nombreMensaje, contenidoGrande));
		Vector<?> resultado = (Vector<?>) mensaje.buscar("trenta");
		Mensaje busqueda = (Mensaje) resultado.get(0);
		assertEquals(busqueda, mensaje);
		assertEquals(resultado.size(), 1);
	}

	@Test
	public void testBuscarEnMensajeNoEncontrado() {
		String contenidoGrande = "este contenido es mayor a trenta y dos caracteres";
		Mensaje mensaje = new Mensaje(new Texto(nombreMensaje, contenidoGrande));
		Vector<?> resultado = (Vector<?>) mensaje.buscar("treinta");
		assertTrue(resultado.isEmpty());
	}

}
