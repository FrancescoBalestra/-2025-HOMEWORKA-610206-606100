package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.comand.*;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.*;

class StanzaMagicaTest {
	
	private Labirinto lab;
	private Partita partita;
	private ComandoVai vai;
	private StanzaMagica sm;
	
	
	@BeforeEach
	public void setUp() {
		lab = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaMagica("n10")
				.addAttrezzo("pala", 1)
				.addAttrezzo("pollo", 3)
				.addAttrezzo("koala", 3)
				.addStanzaVincente("uscita")
				.addAdiacenza("atrio", "n10", "ovest")
				.addAdiacenza("atrio", "uscita", "nord")
				.getLabirinto();
		
		partita = new Partita(lab);
		vai = new ComandoVai();

		
		this.vai.setIo(new IOConsole());
		
	}
	
	
	@Test
	void testNomeMagico() {
		vai.setParametro("ovest");
		vai.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("alaok"));
		
	}
	
	@Test
	void testPesoMagico() {
		vai.setParametro("ovest");
		vai.esegui(partita);
		assertEquals(6, partita.getStanzaCorrente().getAttrezzo("alaok").getPeso());
	}
	
	
	
}
