package gal.udc.fic.vvs.email.archivo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ArchivoTest {
	private static String audioMimeType = "audio/ogg";
	private static String imagenMimeType = "image/png";
	private static String textoMimeType = "text/plain";

	@Test
	public void testCreateArchivoAudioReturnMimeType() {
		Audio archivoAudio = new Audio("nombreAudio", "ContenidoAudio");
		assertEquals(archivoAudio.obtenerMimeType(), audioMimeType);
	}

	@Test
	public void testCreateArchivoAudioReturnNombre() {
		Audio archivoAudio = new Audio("nombreAudio", "ContenidoAudio");
		assertEquals(archivoAudio.obtenerNombre(), "nombreAudio");
	}

	@Test
	public void testCreateArchivoAudioReturnContenido() {
		Audio archivoAudio = new Audio("nombreAudio", "ContenidoAudio");
		assertEquals(archivoAudio.obtenerContenido(), "ContenidoAudio");
	}

	@Test
	public void testCreateArchivoAudioReturnSizeContenido() {
		Audio archivoAudio = new Audio("nombreAudio", "ContenidoAudio");
		assertEquals(archivoAudio.obtenerTamaño(), 14);
	}

	@Test
	public void testCreateArchivoAudioReturnPreVisualizacion() {
		Audio archivoAudio = new Audio("nombreAudio", "ContenidoAudio");
		assertEquals(archivoAudio.obtenerPreVisualizacion(), "nombreAudio(14 bytes, audio/ogg)");
	}

	@Test
	public void testCreateArchivoImagenReturnMimeType() {
		Imagen archivoImagen = new Imagen("nombreImagen", "ContenidoImagen");
		assertEquals(archivoImagen.obtenerMimeType(), imagenMimeType);
	}

	@Test
	public void testCreateArchivoTextoReturnMimeType() {
		Texto archivoTexto = new Texto("nombreTexto", "ContenidoTexto");
		assertEquals(archivoTexto.obtenerMimeType(), textoMimeType);
	}

}
