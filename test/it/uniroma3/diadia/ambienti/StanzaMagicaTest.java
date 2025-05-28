package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comandi.*;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.*;

class StanzaMagicaTest {
	
	private StanzaMagica SM;
	private Stanza SI;
	
	private Partita partita;
	private Attrezzo a1;
	private Attrezzo a2;
	private Attrezzo a3;
	private Attrezzo a4;

	@BeforeEach
	public void setUp() {
		this.partita = new Partita();
		this.SM  = new StanzaMagica("Stanza Magica");
		this.partita.setStanzaCorrente(SM);
		this.a1 = new Attrezzo("a1", 1);
		this.a2 = new Attrezzo("a2", 1);
		this.a3 = new Attrezzo("a3", 1);
		this.a4 = new Attrezzo("a4", 1);
		
	}
	
	@Test
	void testNoAttivazione() {
		assertEquals(SM.getDescrizione(), this.partita.getStanzaCorrente().getDescrizione());
	}
	
	@Test
	void testAttivazioneControlloNoNull() {
		this.SM.addAttrezzo(a1);
		this.SM.addAttrezzo(a2);
		this.SM.addAttrezzo(a3);
		this.SM.addAttrezzo(a4);
	    Attrezzo modificato = this.SM.getAttrezzo("4a"); 
	    assertNotNull(modificato);
	}
	
	@Test
	void testAttivazioneControlloStringa() {
		this.SM.addAttrezzo(a1);
		this.SM.addAttrezzo(a2);
		this.SM.addAttrezzo(a3);
		this.SM.addAttrezzo(a4);
		
	    Attrezzo modificato = this.SM.getAttrezzo("4a"); 
	    assertEquals("4a", modificato.getNome()); 
	}
	
	@Test
	void testAttivazioneControlloPeso() {
		this.SM.addAttrezzo(a1);
		this.SM.addAttrezzo(a2);
		this.SM.addAttrezzo(a3);
		this.SM.addAttrezzo(a4);
		
	    Attrezzo modificato = this.SM.getAttrezzo("4a"); 
	    assertEquals(2, modificato.getPeso()); 
	}
}
