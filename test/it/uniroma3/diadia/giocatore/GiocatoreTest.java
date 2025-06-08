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
		assertEquals(giocatore.getCfu(), 20);
	}

}