package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.ambienti.*;

class ComandoPrendiTest {
	
	
	//preparo l'ambiente per eseguire i test
	private ComandoPrendi prendi;
	private Partita partita;
	private Borsa borsa;
	private Labirinto lab;
	
	
	@BeforeEach
	public void setUp() {
	
		lab = new LabirintoBuilder()
    			.addStanzaIniziale("atrio")
    			.addAttrezzo("armadio", 20)
    			.addAttrezzo("lanterna", 3)
    			.addStanzaVincente("biblioteca")
    			.addAdiacenza("atrio", "biblioteca", "nord")
    			.getLabirinto();
		
		prendi = new ComandoPrendi();
		partita = new Partita(lab);
		borsa = new Borsa();
		
		this.partita.getGiocatore().setBorsa(this.borsa);
		this.prendi.setIo(new IOConsole());
	}
	
	/* controllo che prendi non modifichi la borsa in caso di parametro nullo
	 * */
	@Test
	public void testPrendiOggettoNull() {
		this.prendi.setParametro(null);
		this.prendi.esegui(partita);
		
		assertTrue(this.borsa.isEmpty());
	}
	
	
	/* controllo che prendi non modifichi la borsa in caso venga scelto 
	 * un oggetto troppo pesante per la borsa	 * 
	 * */
	@Test
	public void testPrendiOggettoTroppoPesante() {
		this.prendi.setParametro("armadio");
		this.prendi.esegui(partita);
		
		assertFalse(this.borsa.hasAttrezzo("armadio"));
	}
	
	/* testo che prendi funzioni in maniera adeguata in caso venga eseguita
	 * una scelta valida
	 * */
	@Test
	public void testPrendiOggettoValido() {
		this.prendi.setParametro("lanterna");
		this.prendi.esegui(partita);

		assertTrue(this.borsa.hasAttrezzo("lanterna"));
	}

}