package it.uniroma3.diadia.comand;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.*;

public class ComandoVaiTest {
	
	//preparo un ambiente per i test che voglio eseguire:
    private ComandoVai comandoVai;
    private Partita partita;
    private Labirinto lab;

    @BeforeEach
    public void setUp() {
    	
    	lab = new LabirintoBuilder()
    			.addStanzaIniziale("atrio")
    			.addStanzaVincente("biblioteca")
    			.addAdiacenza("atrio", "biblioteca", "nord")
    			.getLabirinto();
    	
        comandoVai = new ComandoVai();
        partita = new Partita(lab);
       
        this.comandoVai.setIo(new IOConsole()); 
    }

    
    /* Con questo test voglio accertarmi che in caso di direzione nulla non
     * venga effettuata nessun cambio di direzione     * 
     * */
    @Test
    public void testVaiDirezioneNull() {
        comandoVai.setParametro(null);
        comandoVai.esegui(partita);
        assertEquals("atrio", partita.getStanzaCorrente().getNome());
    }
    
    /* Con questo test voglio assicurarmi che in caso di inserimento di una
     * direzione inesistente nel gioco non avvenga nessuno spostamento     * 
     * */
    @Test
    public void testVaiDirezioneInesistente() {
        comandoVai.setParametro("su"); 
        comandoVai.esegui(partita);
        assertEquals("atrio", partita.getStanzaCorrente().getNome());
    }
    
    /* Con questo test controllo, nel caso in cui viene inserito un comando valido,
     * che venga eseguito correttamente     * 
     * */
    @Test
    public void testVaiDirezioneValida() {
        comandoVai.setParametro("nord");
        comandoVai.esegui(partita);
        assertEquals("biblioteca", partita.getStanzaCorrente().getNome());
    }

    
    /* Faccio un test per controllare che i CFU vengano decrementati a ogni spostamento,
     * visto che il precedente test si trovava in borsa, dopo le modifiche avvenute alla classe 
     * il controllo lo imposto in ComandoVai
     * */
    @Test
    public void testRiduzioneCFU() {
        int cfuIniziali = partita.getGiocatore().getCfu();
        comandoVai.setParametro("nord");
        comandoVai.esegui(partita);
        assertEquals(cfuIniziali - 1, partita.getGiocatore().getCfu());
    }
}
