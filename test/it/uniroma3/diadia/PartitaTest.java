package it.uniroma3.diadia;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class PartitaTest {
	private Partita partita;
	
	@BeforeEach
	public void Setup() {
		partita = new Partita();
	}
	
	
	@Test
	public void testVintaFalse() {
		assertFalse(partita.vinta());	
	}
	
	@Test
	public void testIsFinita() {
		partita.setFinita();
		assertTrue(partita.isFinita());		
	}
	
	@Test
	void testNovaPartitaNON_FINITA_E_POI_FINITA() {
		assertFalse(this.partita.isFinita());
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());

		}
	

}

