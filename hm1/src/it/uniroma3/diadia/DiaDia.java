package it.uniroma3.diadia;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

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
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "borsa"};

 	private Partita partita;
 	private IOConsole io;
 	
	public DiaDia() {
		this.partita = new Partita();
		this.io = new IOConsole();
			}

	public void gioca() {
		String istruzione; 
		io.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do		
			istruzione = io.leggiRiga();
		while (processaIstruzione(istruzione));				// Tolto il ! perché rendeva impossibile il commento del
	   														// docente in merito al return del commento sottostante
	}

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		switch (comandoDaEseguire.getNome()) {						// Implementazione dello switch per una preferenza 
	        case "fine":											// personale e per farmi rendere più chiara la
	            this.fine();										// distinzione dei casi e dell'if nel case "vai"
	            return false;
	
	        case "vai":
		        this.vai(comandoDaEseguire.getParametro());
			    if(this.partita.isFinita())  { 						// se è finita la partita
		        	if (this.partita.vinta()) {						// e se la partita è vinta
			        	io.mostraMessaggio("Hai vinto!");			// messaggio di vittoria
		            	return false;								// interruzione del gioco
		        	}
		        	else {
		        		io.mostraMessaggio("Hai perso! Hai finito i CFU!"); // è finita la partita ma non si hanno più cfu
		        		return false;										// interruzione del gioco
		        	}
			    }
		        return true;												// continuazione del gioco
	       
	            
	        case "aiuto":
	            this.aiuto();			// stampa il messaggio di aiuto
	            return true;			// continuazione del gioco
	        
	        case "prendi":
	        	this.prendi(comandoDaEseguire.getParametro());	// richiama prendi
	        	return true;									// continuazione del gioco
	        
	        case "borsa":
	        	this.inventario();								// richiama inventario
	        	return true;									// continuazione del gioco
	        
	        case "posa":	
	        	this.posa(comandoDaEseguire.getParametro());	// richiama posa
	        	return true;									// continuazione del gioco
	        	
	        default:
	        	io.mostraMessaggio("Comando sconosciuto");		// messaggio di errore
	            return true;									// continuazione del gioco
	    }
}
			
		
			
		
	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		io.mostraMessaggio("-------------------");
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio("       "+elencoComandi[i]);
		io.mostraMessaggio("-------------------");
	}
	
	
	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			io.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.lab.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			io.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.lab.setStanzaCorrente(prossimaStanza);
			this.partita.gio.decrementaCfu();													// utilizzo di decrementaCfu per isolare 
		}																						// i cfu dalla classe iniziale
		io.mostraMessaggio(partita.toString());
	}
	
	/*
	 *  Comando "prendi"
	 */
	private void prendi(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {								// se l'utente ha solo scritto prendi
			io.mostraMessaggio("Che cosa vuoi prendere?");
	        return;
	    }
	    Stanza stanzaCorrente = this.partita.lab.getStanzaCorrente();	   	// inizializzo la stanza in cui mi trovo
	    if (!stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {					// se nella stanza non c'è l'attrezzo che vuole l'utente 
	    	io.mostraMessaggio("L'attrezzo '" + nomeAttrezzo + "' non è presente nella stanza.");
	        return;
	    }
	    Attrezzo attrezzoDaPrendere = stanzaCorrente.getAttrezzo(nomeAttrezzo);		// prendo l'attrezzo dalla stanza
	    if (this.partita.gio.borsa.addAttrezzo(attrezzoDaPrendere)) { 				// metto l'attrezzo nella borsa
	        stanzaCorrente.removeAttrezzo(nomeAttrezzo);							// rimuovo l'attrezzo dalla stanza
	        io.mostraMessaggio("Hai preso: " + nomeAttrezzo);						// messaggio di conferma
	    } 
	    else {
	    	io.mostraMessaggio("Non puoi prendere '" + nomeAttrezzo + "', la borsa è piena o troppo pesante.");		// messaggio di errore generico
	    }
	}
	
	/*
	 * Comando rimuovi
	 */
	private void posa(String nomeAttrezzo) {
		
		if(this.partita.gio.borsa.isEmpty()) {											// se la borsa è piena
			io.mostraMessaggio("Non puoi rimuovere nulla perché la borsa è vuota!");	// messaggio di errore
			return;
		}
		if (nomeAttrezzo == null) {														// se l'utente ha solo scritto rimuovi
			io.mostraMessaggio("Che cosa vuoi rimuovere?");
	        return;
	    }
		Borsa borsaCorrente = this.partita.gio.borsa;									// inizializza la borsa 
		Attrezzo attrezzoDaRimuovere = borsaCorrente.getAttrezzo(nomeAttrezzo);			// prendo l'attrezzo dalla borsa
		if(borsaCorrente.hasAttrezzo(nomeAttrezzo)) {									// controllo che la borsa contenga l' attrezzo che voglio rimuovere
			borsaCorrente.removeAttrezzo(nomeAttrezzo);									// rimuovo l'attrezzo dalla borsa
			Stanza stanzaCorrente = this.partita.lab.getStanzaCorrente();				// inizializzo la stanza in cui mi trovo
			stanzaCorrente.addAttrezzo(attrezzoDaRimuovere);							// poso l'attrezzo nella sanza
			io.mostraMessaggio("Hai rimosso dall'inventario "+ nomeAttrezzo + " e lo hai posato in "+ stanzaCorrente );		// messaggio di conferma
		}
		else {
			io.mostraMessaggio("L'attrezzo che vuoi eliminare non è nella tua borsa!");		// messaggio di errore attrezzo non trovato nella borsa
		}
	}
	
	
	/*
	 * Comando inventario
	 */
	public void inventario() {
		io.mostraMessaggio(partita.gio.borsa.toString());			// visualizza con una stampa cosa è presente nella borsa
	}
	
	/**
	 * Comando "Fine".
	 */
	private void fine() {
		io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
	
	
	
	
	public class IOConsole {

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


}