package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.giocatore.Giocatore;

public class PartitaTest {

    private Partita partita;
    private Labirinto labirinto;

    @BeforeEach
    public void setup() {
        labirinto = Labirinto.newBuilder()
            .addStanzaIniziale("Atrio")
            .addStanzaVincente("Biblioteca")
            .getLabirinto();

        partita = new Partita(labirinto);
    }
    

    @Test
    public void testVintaInizio() {
        // All'inizio la stanza corrente non è la vincente
        assertFalse(partita.vinta());
    }

    @Test
    public void testVinta() {
        // Imposta la stanza corrente come stanza vincente
        partita.setStanzaCorrente(labirinto.getStanzaVincente());
        assertTrue(partita.vinta());
    }


    @Test
    public void testIsFinita() {
        partita.setFinita();
        assertTrue(partita.isFinita());
    }


    @Test
    public void testIsFinitaCfuZero() {
        Giocatore giocatore = partita.getGiocatore();
        giocatore.setCfu(0);
        assertTrue(partita.isFinita());
    }

    @Test
    public void testGiocatoreIsVivo() {
        Giocatore giocatore = partita.getGiocatore();
        giocatore.setCfu(10);
        assertTrue(partita.giocatoreIsVivo());
    }


}
