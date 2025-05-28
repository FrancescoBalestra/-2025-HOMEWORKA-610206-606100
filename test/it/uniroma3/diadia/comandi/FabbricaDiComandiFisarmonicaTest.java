package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.IOConsole;



class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiFisarmonica fabbrica;
	
	@BeforeEach
	public void setUp() {
		this.fabbrica = new FabbricaDiComandiFisarmonica(new IOConsole());
	}
	
	@Test
	void testComandoNull() {
		Comando comando = fabbrica.costruisciComando("");
		assertEquals(comando.getNome(), "Comando sconosciuto");
	}
	
	@Test
	void testComandoVai() {
		Comando comando = fabbrica.costruisciComando("vai");
		assertTrue(comando instanceof ComandoVai);
	}

	@Test
	void testComandoPrendi() {
		Comando comando = fabbrica.costruisciComando("prendi");
		assertTrue(comando instanceof ComandoPrendi);
	}

	@Test
	void testComandoPosa() {
		Comando comando = fabbrica.costruisciComando("posa");
		assertTrue(comando instanceof ComandoPosa);
	}

	@Test
	void testComandoGuarda() {
		Comando comando = fabbrica.costruisciComando("guarda");
		assertTrue(comando instanceof ComandoGuarda);
	}

	@Test
	void testComandoAiuto() {
		Comando comando = fabbrica.costruisciComando("aiuto");
		assertTrue(comando instanceof ComandoAiuto);
	}

	@Test
	void testControlloParametro() {
		Comando comando = fabbrica.costruisciComando("vai nord");
		assertEquals(comando.getParametro(), "nord");
	}
	@Test
	void testControlloParametroNull() {
		Comando comando = fabbrica.costruisciComando("vai");
		assertEquals(comando.getParametro(), null);
	}
}
