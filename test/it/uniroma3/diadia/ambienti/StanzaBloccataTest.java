package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comand.*;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.*;

class StanzaBloccataTest {
		
	private StanzaBloccata stanzaB;
	private Stanza stanzaA;
	private ComandoVai vai;
	private Partita partita;
	private Attrezzo passpartou;
	
	@BeforeEach
	public void setUp() {
		
		this.partita = new Partita();
		this.vai = new ComandoVai();
		this.passpartou = new Attrezzo("passpartou", 2);
		
		this.stanzaB = new StanzaBloccata("atrio","nord","passpartou");
		this.stanzaA = new Stanza("uscita");
		
		this.stanzaB.impostaStanzaAdiacente("nord", stanzaA);
		this.partita.setStanzaCorrente(stanzaB);
		this.vai.setIo(new IOConsole()); 
			
		
	}
	
	
	@Test
	void testNoPass() {
		this.vai.setParametro("nord");
		this.vai.esegui(partita);
		assertEquals("atrio", this.partita.getStanzaCorrente().getNome());
	}
	
	
	@Test
	void testPass() {
		this.stanzaB.addAttrezzo(passpartou);
		this.vai.setParametro("nord");
		this.vai.esegui(partita);
		assertEquals("uscita", this.partita.getStanzaCorrente().getNome());
	}
}
