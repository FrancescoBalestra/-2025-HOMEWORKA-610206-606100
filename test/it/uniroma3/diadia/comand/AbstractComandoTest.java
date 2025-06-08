package it.uniroma3.diadia.comand;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comand.ComandoPrendi;

//Classe concreta per testare AbstractComando
class ComandoDiTest extends AbstractComando {
	
	@Override
	public void esegui(Partita partita) {}
}

public class AbstractComandoTest {

	private ComandoDiTest comando;

	@BeforeEach
	void setUp() {
		comando = new ComandoDiTest();
	}

	@Test
	void testSetEGetParametro() {
		comando.setParametro("testParametro");
		assertEquals("testParametro", comando.getParametro());
	}

	


	
}
