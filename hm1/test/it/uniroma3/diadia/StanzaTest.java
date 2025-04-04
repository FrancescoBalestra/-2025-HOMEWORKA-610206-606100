package it.uniroma3.diadia;

import it.uniroma3.diadia.attrezzi.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


public class StanzaTest {
	
	private Stanza atrio;
	private Stanza n10;
	private Attrezzo lanterna;
	
	@BeforeEach
	public void Setup() {
		atrio = new Stanza("atrio");
		n10 = new Stanza("n10");
		lanterna = new Attrezzo("lanterna", 3);
	}

	@Test
	public void testImpostaStanzaAdiacenteNull() {
		atrio.impostaStanzaAdiacente("est", null);
		assertNull(atrio.getStanzaAdiacente("est"));
	}
	
	@Test 
	public void testAddAttrezzoNull() {
		n10.addAttrezzo(null);
		assertTrue(n10.addAttrezzo(null));
	}
	
	@Test 
	public void testAddAttrezzoSingolo() {
		n10.addAttrezzo(lanterna);
		assertTrue(n10.addAttrezzo(lanterna));
	}
	
	@Test 
	public void testHasAttrezzoNull() {
		assertFalse(n10.hasAttrezzo(null));;		
	}
	
	
	
	
}
