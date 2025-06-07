package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comandi.*;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.*;

class StanzaBuiaTest {
		
	private ComandoVai vai;
	private ComandoPosa posa;
	private ComandoPrendi prendi;
	private Partita partita;
	private Attrezzo lanterna;
	private Labirinto lab;
	private StanzaBuia sb;
	
	@BeforeEach
	public void setUp() {
		
		lab = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addAttrezzo("lanterna", 1)
				.addStanzaBuia("laboratorio", "lanterna")
				.addStanzaVincente("Uscita")
				.addAdiacenza("atrio", "laboratorio", "est")
				.getLabirinto();
		
		partita = new Partita(lab);
		vai = new ComandoVai();
		posa = new ComandoPosa();
		prendi = new ComandoPrendi();
		lanterna = new Attrezzo("lanterna", 1);
		sb = new StanzaBuia("laboratorio","lanterna");
		
		this.vai.setIo(new IOConsole());
		this.prendi.setIo(new IOConsole());
		this.posa.setIo(new IOConsole());
	}
	
	@Test
	void testNoPass() {
		vai.setParametro("est");
		vai.esegui(partita);
		assertEquals("qui c'è un buio pesto", partita.getStanzaCorrente().getDescrizione());
	}
	
	
	@Test
	void testPass() {
		prendi.setParametro("lanterna");
		prendi.esegui(partita);
		vai.setParametro("est");
		vai.esegui(partita);
		posa.setParametro("lanterna");		
		posa.esegui(partita);
		sb.addAttrezzo(lanterna);
		assertEquals(sb.getDescrizione(), this.partita.getStanzaCorrente().getDescrizione());
	}
	
	
}