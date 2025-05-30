package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comand.*;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.*;

class StanzaBuiaTest {
		
	private ComandoVai vai;
	private Partita partita;
	private Attrezzo lanterna;
	private Labirinto lab;
	private StanzaBuia sb;
	
	@BeforeEach
	public void setUp() {
		
		lab = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaBuia("laboratorio", "lanterna")
				.addStanzaVincente("Uscita")
				.addAdiacenza("laboratorio", "Uscita", "nord")
				.addAdiacenza("atrio", "laboratorio", "est")
				.getLabirinto();
		
		partita = new Partita(lab);
		vai = new ComandoVai();
		lanterna = new Attrezzo("lanterna", 3);
		sb = new StanzaBuia("laboratorio","lanterna");
		
		this.vai.setIo(new IOConsole()); 
			
	}
	
	@Test
	void testNoPass() {
		vai.setParametro("est");
		vai.esegui(partita);
		assertEquals("qui c'è un buio pesto", partita.getStanzaCorrente().getDescrizione());
	}
	
	
	@Test
	void testPass() {
		vai.setParametro("est");
		vai.esegui(partita);
		assertEquals(sb.getDescrizione(), this.partita.getStanzaCorrente().getDescrizione());
	}
	
	
}
