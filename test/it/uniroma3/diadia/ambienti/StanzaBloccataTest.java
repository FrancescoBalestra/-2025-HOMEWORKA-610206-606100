package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.comand.*;

class StanzaBloccataTest {
	
	private Labirinto lab;	
	private ComandoVai vai;
	private Partita partita;
	private Attrezzo pass;
	
	@BeforeEach
	public void setUp() {
		
		lab = Labirinto.newBuilder()
				.addStanzaBloccata("Atrio", "nord", "ssap")
				.setStanzaIniziale("Atrio")
				.addStanzaVincente("Uscita")
				.addAdiacenza("Atrio", "Uscita", "nord")
				.getLabirinto();
		
		
		partita = new Partita(lab);
		
		this.vai = new ComandoVai();
		this.pass = new Attrezzo("ssap", 1);
		
		this.vai.setIo(new IOConsole(new Scanner(System.in)));

			
		
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
