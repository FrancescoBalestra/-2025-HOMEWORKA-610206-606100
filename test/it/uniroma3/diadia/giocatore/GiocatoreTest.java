package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;




public class GiocatoreTest {
	
	private Giocatore giocatore;

	@BeforeEach
	public void Setup() {
		giocatore = new Giocatore();
	}
	
	
	@Test
	public void testSetCfu() {
		giocatore.setCfu(20);
		assertEquals(giocatore.getCfu(), 20);
	}

	@Test
	public void testDecrementaCfu() {
		giocatore.setCfu(20);
		giocatore.decrementaCfu();
		assertEquals(giocatore.getCfu(), 19);
	}
}