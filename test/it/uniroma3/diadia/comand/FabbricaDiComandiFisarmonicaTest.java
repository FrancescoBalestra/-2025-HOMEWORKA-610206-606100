package it.uniroma3.diadia.comand;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.comand.Comando;
import it.uniroma3.diadia.comand.ComandoPosa;
import it.uniroma3.diadia.comand.FabbricaDiComandiFisarmonica;

class FabbricaDiComandiFisarmonicaTest {
	
	
	private FabbricaDiComandiFisarmonica fabbrica;
	
	@BeforeEach
	public void setUp() {
		this.fabbrica = new FabbricaDiComandiFisarmonica();
		
	}
	
	/* controllo che la fabbrica chiami ComandoNonValido in caso venga
	 * inserito un comando sconosciuto al gioco
	 * */
	@Test
	void testComandoNonValido() throws Exception {
		Comando comando = fabbrica.costruisciComando("vola");
		assertEquals("Comando sconosciuto", comando.getNome());
	}
	
	
	/* controllo che la fabbrica invochi ComandoPosa se il giocatore
	 * scrive di voler posare un oggetto
	 * */
	@Test
	void testComandoPosa() throws Exception {
		Comando comando = fabbrica.costruisciComando("posa scarpa");
		assertTrue(comando instanceof ComandoPosa);
	}
	
	
	/* controllo che la fabbrica mandi il parametro corretto alla classe
	 * invocata
	 * */
	@Test
	void testControlloParametro() throws Exception {
		Comando comando = fabbrica.costruisciComando("vai nord");
		assertEquals("nord", comando.getParametro());
	}
}
