package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.comand.*;

class StanzaBuiaTest {
		
	private ComandoVai vai;
	private Partita partita;
	private Attrezzo lanterna;
	private Labirinto lab;
	private StanzaBuia sb;
	
	@BeforeEach
	public void setUp() {
		
		lab = Labirinto.newBuilder()
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
		
		this.vai.setIo(new IOConsole(new Scanner(System.in))); 
			
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
