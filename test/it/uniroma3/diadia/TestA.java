package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;


import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.*;

class TestA {

    private DiaDia gioca;
    private IOSimulator ioSim;
    private Labirinto lab;

    @BeforeEach
    public void setUp() {

    	lab = Labirinto.newBuilder()
    	        .addStanza("Aula N10")
    	        .addAttrezzo("lanterna", 2)
    	        .addAttrezzo("ratto", 1)
    	        .addStanzaMagica("Aula N11")
    	        .addAttrezzo("osso", 1)
    	        .addAttrezzo("pollo", 1)
    	        .addAttrezzo("pala", 1)
    	        .addStanzaBloccata("Atrio", "nord", "ssap")
    	        .setStanzaIniziale("Atrio")
    	        .addStanzaBuia("Laboratorio Campus", "lanterna")
    	        .addAttrezzo("pass", 1)
    	        .addStanzaVincente("Biblioteca")
    	        .addAdiacenza("Atrio", "Biblioteca", "nord")
    	        .addAdiacenza("Atrio", "Aula N10", "sud")
    	        .addAdiacenza("Atrio", "Aula N11", "est")
    	        .addAdiacenza("Atrio", "Laboratorio Campus", "ovest")
    	        .addAdiacenza("Aula N10", "Atrio", "nord")
    	        .addAdiacenza("Aula N10", "Aula N11", "est")
    	        .addAdiacenza("Aula N10", "Laboratorio Campus", "ovest")
    	        .addAdiacenza("Aula N11", "Laboratorio Campus", "est")
    	        .addAdiacenza("Aula N11", "Atrio", "ovest")
    	        .addAdiacenza("Laboratorio Campus", "Atrio", "est")
    	        .addAdiacenza("Laboratorio Campus", "Aula N11", "ovest")
    	        .addAdiacenza("Biblioteca", "Atrio", "sud")
    	        .getLabirinto();
    	
        List<String> istruzioni = List.of(
            "vai sud",
            "prendi lanterna",
            "vai ovest",
            "posa lanterna",
            "prendi pass",
            "vai est",
            "vai est",
            "posa pass",
            "prendi ssap",
            "vai ovest",
            "posa ssap",
            "vai nord"
        );
        this.ioSim = new IOSimulator(istruzioni);

        gioca = new DiaDia(ioSim, lab);
    }

    private boolean contieneMessaggio(List<String> messaggi, String testoCercato) {
        for (String msg : messaggi) {
            if (msg.contains(testoCercato)) {
                return true;
            }
        }
        return false;
    }

    @Test
    void testSpostamento() throws Exception {
        gioca.gioca();
        List<String> messaggi = ioSim.getMessaggiProdotti();
        assertTrue(contieneMessaggio(messaggi, "Aula N10"));
    }

    @Test
    void testPrendi() throws Exception {
        gioca.gioca();
        List<String> messaggi = ioSim.getMessaggiProdotti();
        assertTrue(contieneMessaggio(messaggi, "Hai ottenuto lanterna!"));
    }

    @Test
    void testPosa() throws Exception {
        gioca.gioca();
        List<String> messaggi = ioSim.getMessaggiProdotti();
        assertTrue(contieneMessaggio(messaggi, "Hai posato lanterna!"));
    }

    @Test
    void testVinci() throws Exception {
        gioca.gioca();
        List<String> messaggi = ioSim.getMessaggiProdotti();
        assertTrue(contieneMessaggio(messaggi, "Hai vinto!"));
    }
}
