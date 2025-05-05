package it.uniroma3.diadia;
//import java.util.Scanner;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {
	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
//	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "borsa"};
	
	private Partita partita;
	private IO io;

	public DiaDia(IO console) {
		this.io = console;
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 
//		Scanner scannerDiLinee;
		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do {
			istruzione = io.leggiRiga();

		}while (!processaIstruzione(istruzione) );

	}   

	/**System.in
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica(this.io);
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
		io.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			io.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
		}
			
		
	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	/*private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			System.out.print(elencoComandi[i]+" ");
		System.out.println();
	}
	*/
	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	/*private void vai(String direzione) {
		if(direzione==null)
			System.out.println("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.lab.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			System.out.println("Direzione inesistente");
		else {
			this.partita.lab.setStanzaCorrente(prossimaStanza);
			this.partita.gio.decrementaCfu();													// utilizzo di decrementaCfu per isolare 
		}																						// i cfu dalla classe iniziale
		System.out.println(partita);
	}
	*/
	/*
	 *  Comando "prendi"
	 */
	/*
	private void prendi(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {								// se l'utente ha solo scritto prendi
	        System.out.println("Che cosa vuoi prendere?");
	        return;
	    }
	    Stanza stanzaCorrente = this.partita.lab.getStanzaCorrente();	   	// inizializzo la stanza in cui mi trovo
	    if (!stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {					// se nella stanza non c'� l'attrezzo che vuole l'utente 
	        System.out.println("L'attrezzo '" + nomeAttrezzo + "' non � presente nella stanza.");
	        return;
	    }
	    Attrezzo attrezzoDaPrendere = stanzaCorrente.getAttrezzo(nomeAttrezzo);		// prendo l'attrezzo dalla stanza
	    if (this.partita.gio.borsa.addAttrezzo(attrezzoDaPrendere)) { 				// metto l'attrezzo nella borsa
	        stanzaCorrente.removeAttrezzo(nomeAttrezzo);							// rimuovo l'attrezzo dalla stanza
	        System.out.println("Hai preso: " + nomeAttrezzo);						// messaggio di conferma
	    } 
	    else {
	        System.out.println("Non puoi prendere '" + nomeAttrezzo + "', la borsa � piena o troppo pesante.");		// messaggio di errore generico
	    }
	}
	*/
	/*
	 * Comando rimuovi
	 */
	/*
	private void posa(String nomeAttrezzo) {
		
		if(this.partita.gio.borsa.isEmpty()) {											// se la borsa � piena
			System.out.println("Non puoi rimuovere nulla perch� la borsa � vuota!");	// messaggio di errore
			return;
		}
		if (nomeAttrezzo == null) {														// se l'utente ha solo scritto rimuovi
	        System.out.println("Che cosa vuoi rimuovere?");
	        return;
	    }
		Borsa borsaCorrente = this.partita.gio.borsa;									// inizializza la borsa 
		Attrezzo attrezzoDaRimuovere = borsaCorrente.getAttrezzo(nomeAttrezzo);			// prendo l'attrezzo dalla borsa
		if(borsaCorrente.hasAttrezzo(nomeAttrezzo)) {									// controllo che la borsa contenga l' attrezzo che voglio rimuovere
			borsaCorrente.removeAttrezzo(nomeAttrezzo);									// rimuovo l'attrezzo dalla borsa
			Stanza stanzaCorrente = this.partita.lab.getStanzaCorrente();				// inizializzo la stanza in cui mi trovo
			stanzaCorrente.addAttrezzo(attrezzoDaRimuovere);							// poso l'attrezzo nella sanza
			System.out.println("Hai rimosso dall'inventario "+ nomeAttrezzo + " e lo hai posato in "+ stanzaCorrente );		// messaggio di conferma
		}
		else {
			System.out.println("L'attrezzo che vuoi eliminare non � nella tua borsa!");		// messaggio di errore attrezzo non trovato nella borsa
		}
	}
	
	
	/*
	 * Comando inventario
	 */
	/*
	public void inventario() {
		System.out.println(partita.gio.borsa.toString());			// visualizza con una stampa cosa � presente nella borsa
	}
	
	/**
	 * Comando "Fine".
	 */
	/*
	private void fine() {
		System.out.println("Grazie di aver giocato!");  // si desidera smettere
	}
	*/
	public static void main(String[] argc) {
		IO console = new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
	}

/*	public class IOConsole {

        public void mostraMessaggio(String msg) {
            System.out.println(msg);
        }

        public String leggiRiga() {
            Scanner scannerDiLinee = new Scanner(System.in);
            String riga = scannerDiLinee.nextLine();
            //scannerDiLinee.close();
            return riga;
        }
    }
*/
}