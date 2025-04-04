package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import it.uniroma3.diadia.attrezzi.*;


public class BorsaTest {
	
	private Borsa borsa;
	private Attrezzo attrezzo;
	
	@BeforeEach
	public void Setup() {
		borsa = new Borsa();
		attrezzo = new Attrezzo("ascia", 1);
	}
	
	@Test
	public void testAddAttrezzoTrue() {
		assertTrue(borsa.addAttrezzo(attrezzo));
	}
	

	@Test
	public void testRemoveAttrezzoNull() {
		assertNull(borsa.removeAttrezzo(""));
	}
	
	@Test
	public void testRemoveAttrezzoNotNull() {
		borsa.addAttrezzo(attrezzo);
		assertNotNull(borsa.removeAttrezzo(attrezzo.getNome()));
	}
	

}
