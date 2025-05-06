package it.uniroma3.diadia.comand;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVaiTest {
	
	//preparo un ambiente per i test che voglio eseguire:
    private ComandoVai comandoVai;
    private Partita partita;
    private Stanza stanzaIniziale;
    private Stanza stanzaAdiacente;

    @BeforeEach
    public void setUp() {
    	
        this.comandoVai = new ComandoVai();
        this.partita = new Partita();
        
        //il gioco parte nell'atrio
        this.stanzaIniziale = new Stanza("Atrio");
        this.stanzaAdiacente = new Stanza("Segreteria");
        
        //decidio che a nord dell'atrio si trova la segreteria
        this.stanzaIniziale.impostaStanzaAdiacente("nord", stanzaAdiacente);
        this.partita.setStanzaCorrente(this.stanzaIniziale);
        this.comandoVai.setIo(new IOConsole()); 
    }

    
    /* Con questo test voglio accertarmi che in caso di direzione nulla non
     * venga effettuata nessun cambio di direzione     * 
     * */
    @Test
    public void testVaiDirezioneNull() {
        this.comandoVai.setParametro(null);
        this.comandoVai.esegui(this.partita);
        assertEquals("Atrio", this.partita.getStanzaCorrente().getNome());
    }
    
    /* Con questo test voglio assicurarmi che in caso di inserimento di una
     * direzione inesistente nel gioco non avvenga nessuno spostamento     * 
     * */
    @Test
    public void testVaiDirezioneInesistente() {
        this.comandoVai.setParametro("sud"); 
        this.comandoVai.esegui(this.partita);
        assertEquals("Atrio", this.partita.getStanzaCorrente().getNome());
    }
    
    /* Con questo test controllo, nel caso in cui viene inserito un comando valido,
     * che venga eseguito correttamente     * 
     * */
    @Test
    public void testVaiDirezioneValida() {
        this.comandoVai.setParametro("nord");
        this.comandoVai.esegui(this.partita);
        assertEquals("Segreteria", this.partita.getStanzaCorrente().getNome());
    }

    
    /* Faccio un test per controllare che i CFU vengano decrementati a ogni spostamento,
     * visto che il precedente test si trovava in borsa, dopo le modifiche avvenute alla classe 
     * il controllo lo imposto in ComandoVai
     * */
    @Test
    public void testRiduzioneCFU() {
        int cfuIniziali = this.partita.getGiocatore().getCfu();
        this.comandoVai.setParametro("nord");
        this.comandoVai.esegui(this.partita);
        assertEquals(cfuIniziali - 1, this.partita.getGiocatore().getCfu());
    }
}
