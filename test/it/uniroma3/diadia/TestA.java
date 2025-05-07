package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.comand.*;
import it.uniroma3.diadia.giocatore.*;


class TestA {
	
	private Partita partita;
	private IOSimulator ioSim;
	private Stanza biblioteca;
	private StanzaBloccata atrio;
	private StanzaBuia n10;
	private Attrezzo passpartou;
	private Attrezzo lanterna;
	private Borsa borsa;
	private ComandoVai vai;
	private ComandoPrendi prendi;
	
	public void setUp() {
		this.partita = new Partita();
		
		String[] istruzioni = {"prendi lanterna", "vai sud", "posa lanterna", "prendi passpartou", "vai nord", "posa passpartou", "vai nord"};
		this.ioSim = new IOSimulator(istruzioni);
		
		this.biblioteca = new Stanza("biblioteca");
		this.atrio = new StanzaBloccata("atrio", "nord", "passpartou");
		this.n10 = new StanzaBuia("n10", "lanterna");
		
		this.lanterna = new Attrezzo("lanterna", 3);
		this.passpartou = new Attrezzo("passpartou", 1);
		
		this.borsa = new Borsa();
		
		this.vai = new ComandoVai();
		this.prendi = new ComandoPrendi();
		
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("sud", n10);
		partita.setStanzaCorrente(atrio);
		partita.getGiocatore().setBorsa(this.borsa);
		
		atrio.addAttrezzo(lanterna);
		n10.addAttrezzo(passpartou);
		
		vai.setIo(ioSim);
		prendi.setIo(ioSim);		

	}
	
	
	@Test
	void test() {
		
		
	}

}
