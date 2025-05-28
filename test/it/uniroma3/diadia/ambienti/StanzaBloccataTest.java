package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoVai;

class StanzaBloccataTest {
	
	private Partita partita;
	private ComandoVai comandoVai;
	private Attrezzo passpartou;
	
	@BeforeEach
	public void setUp() {
		this.comandoVai = new ComandoVai();
		this.partita = new Partita();
		
		this.passpartou = new Attrezzo("passpartou", 2);

		Stanza atrio = new StanzaBloccata("atrio", "nord", "chiave");
		Stanza uscita = new Stanza("uscita");
		atrio.impostaStanzaAdiacente("nord", uscita);
		uscita.impostaStanzaAdiacente("sud", atrio);
		uscita.addAttrezzo(passpartou);
		this.partita.setStanzaCorrente(atrio);
		this.comandoVai.setIo(new IOConsole());
	}

	@Test
	void testNoPass() {
		this.comandoVai.setParametro("nord");
		this.comandoVai.esegui(partita);
		assertEquals("atrio", this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	void testSiPass() {
		this.partita.getStanzaCorrente().addAttrezzo(passpartou); 
		this.comandoVai.setParametro("nord");
		this.comandoVai.esegui(partita);
		assertEquals("uscita", this.partita.getStanzaCorrente().getNome());
	}

}
