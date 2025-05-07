package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comand.*;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.*;

class StanzaBuiaTest {
		
	private StanzaBuia stanzaB;
	private Stanza stanzaA;
	private ComandoVai vai;
	private Partita partita;
	private Attrezzo lanterna;
	
	@BeforeEach
	public void setUp() {
		
		this.partita = new Partita();
		this.vai = new ComandoVai();
		this.lanterna = new Attrezzo("lanterna", 3);
		
		this.stanzaB = new StanzaBuia("laboratorio","lanterna");
		this.stanzaA = new Stanza("uscita");
		
		this.stanzaB.impostaStanzaAdiacente("nord", stanzaA);
		this.partita.setStanzaCorrente(stanzaB);
		this.vai.setIo(new IOConsole()); 
			
		
	}
	
	@Test
	void testNoPass() {
		assertEquals("qui c'è buio pesto", this.stanzaB.getDescrizione());
	}
	
	
	@Test
	void testPass() {
		this.stanzaB.addAttrezzo(lanterna);
		assertEquals(stanzaB.getDescrizione(), this.partita.getStanzaCorrente().getDescrizione());
	}
	
	
}
