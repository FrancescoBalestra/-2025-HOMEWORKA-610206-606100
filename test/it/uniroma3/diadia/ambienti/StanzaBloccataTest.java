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
	
	private Labirinto lab;	
	private ComandoVai vai;
	private Partita partita;
	private Attrezzo pass;
	
	@BeforeEach
	public void setUp() {
		lab = new LabirintoBuilder()
				.addStanzaBloccataIniziale("Atrio", "nord", "ssap")
				.addStanzaVincente("Uscita")
				.addAdiacenza("Atrio", "Uscita", "nord")
				.getLabirinto();
		
		
		partita = new Partita(lab);
		
		this.vai = new ComandoVai();
		this.pass = new Attrezzo("ssap", 1);
		this.vai.setIo(new IOConsole()); 
	}

	@Test
	void testNoPass() {
		this.vai.setParametro("nord");
		this.vai.esegui(partita);
		assertEquals("Atrio", this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	void testPass() {
		partita.getStanzaCorrente().addAttrezzo(pass);
		this.vai.setParametro("nord");
		this.vai.esegui(partita);
		assertEquals("Uscita", this.partita.getStanzaCorrente().getNome());
	}
	

}
