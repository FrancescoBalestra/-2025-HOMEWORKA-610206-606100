package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import it.uniroma3.diadia.attrezzi.*;


public class BorsaTest {
	
	
	private Borsa borsa;
	private Attrezzo attrezzo, fratello, sasso, forbici;
	
	@BeforeEach
	public void Setup() {
		borsa = new Borsa();
		attrezzo = new Attrezzo("ascia", 1);
		fratello = new Attrezzo("martello", 1);
		sasso = new Attrezzo("sasso", 2);
		forbici = new Attrezzo("forbici", 3);
		
		
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
	
	/* Scrivere un test per verificare che due attrezzi di stesso peso ma 
	 * nome diverso rimangano distinti nel risultato
	 */
	@Test
	public void testGemelliDiPeso() {
		borsa.addAttrezzo(attrezzo);
		borsa.addAttrezzo(fratello);
		assertTrue(0 != attrezzo.compareTo(fratello));
	}
	
	
	@Test
	public void testOrdinePerNome() {

		borsa.addAttrezzo(attrezzo);
		borsa.addAttrezzo(sasso);
		borsa.addAttrezzo(forbici);
		assertEquals("[ascia (1kg), forbici (3kg), sasso (2kg)]", borsa.getContenutoOrdinatoPerNome().toString());
	}
	

	@Test
	public void testOrdinePerPeso() {

		borsa.addAttrezzo(attrezzo);
		borsa.addAttrezzo(sasso);
		borsa.addAttrezzo(forbici);
		assertEquals("[ascia (1kg), sasso (2kg), forbici (3kg)]", borsa.getContenutoOrdinatoPerPeso().toString());
	}
	
	
	@Test
	public void testGruppoPerPeso() {

		borsa.addAttrezzo(attrezzo);
		borsa.addAttrezzo(sasso);
		borsa.addAttrezzo(forbici);
		borsa.addAttrezzo(fratello);
		assertEquals("{1=[ascia (1kg), martello (1kg)], 2=[sasso (2kg)], 3=[forbici (3kg)]}", borsa.getContenutoRaggruppatoPerPeso().toString());
	}
}