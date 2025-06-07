package it.uniroma3.diadia.comand;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.comand.ComandoPosa;

class ComandoPosaTest {
    
    //preparo l'ambiente per svolgere i miei test:
    private ComandoPosa posa;
    private Partita partita;
    private Borsa borsa;
    private Attrezzo lanterna;
    private Labirinto lab;
    
    
    @BeforeEach
    public void setUp() {
    	
    	lab = new LabirintoBuilder()
    			.addStanzaIniziale("atrio")
    			.addStanzaVincente("biblioteca")
    			.addAdiacenza("atrio", "biblioteca", "nord")
    			.getLabirinto();
    	
        posa = new ComandoPosa();
        partita = new Partita(lab);
        borsa = new Borsa();
        lanterna = new Attrezzo("Lanterna",4);
        
        // Aggiungi l'attrezzo alla borsa
        this.borsa.addAttrezzo(this.lanterna);
        this.partita.getGiocatore().setBorsa(this.borsa);

        this.posa.setIo(new IOConsole());
    }
    
    
    /* test per accettarsi che il comando posa non modifichi la stanza
     * in casi di parametro null
     * */
    @Test
    void testPosaOggettoNull() {
        this.posa.setParametro(null);
        this.posa.esegui(this.partita);
        assertEquals(10, partita.getStanzaCorrente().getNumeroAttrezziPossibili());
    }
    
    /* test per accettarsi che il comando posa non modifichi la stanza
     * in casi di parametro non valido
     * */
    @Test
    void testPosaOggettoNonValido() {
        this.posa.setParametro("Torcia");
        this.posa.esegui(partita);
        assertEquals(10, partita.getStanzaCorrente().getNumeroAttrezziPossibili());
    }
    
    /* test per accettarsi che il comando posa svuoti la borsa
     * */
    @Test 
    void testPosaOggettoValido() {
        this.posa.setParametro("Lanterna");
        this.posa.esegui(partita);
        //assertTrue(this.atrio.hasAttrezzo("Lanterna"));
        assertTrue(this.borsa.isEmpty());
    }
}
