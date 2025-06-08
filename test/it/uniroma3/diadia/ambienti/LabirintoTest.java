package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {

	private Labirinto labirinto;
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
	
	@BeforeEach
	public void Setup() {
		labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaVincente("uscita")
				.addAdiacenza("atrio", "uscita", "nord")
				.getLabirinto();
	
		stanzaCorrente = new Stanza("atrio");
		stanzaVincente = new Stanza("uscita");
	}
	
	
	@Test
	public void testGetStanzaVincente() {
		assertEquals(stanzaVincente, labirinto.getStanzaVincente());
	}
	
	@Test
	public void testGetStanzaIniziale() {
		assertEquals(stanzaCorrente, labirinto.getStanzaIniziale());
	}

}
