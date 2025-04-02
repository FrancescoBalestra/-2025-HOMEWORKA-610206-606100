package src;
import java.util.Scanner;

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
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "rimuovi", "inventario"};

 	private Partita partita;
 	
	public DiaDia() {
		this.partita = new Partita();
			}

	public void gioca() {
		String istruzione; 
		Scanner scannerDiLinee;

		System.out.println(MESSAGGIO_BENVENUTO);
		scannerDiLinee = new Scanner(System.in);		
		do		
			istruzione = scannerDiLinee.nextLine();
		while (processaIstruzione(istruzione));				// Tolto il ! perché rendeva impossibile il commento del
	   														// docente in merito al return del commento sottostante
		scannerDiLinee.close();								// implemento la chiusura dello scanner per la memoria
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
			    if(this.partita.isFinita())  { 
		        	if (this.partita.vinta()) {
			        	System.out.println("Hai vinto!");
		            	return false;
		        	}
		        	else {
		        		System.out.println("Hai perso! Hai finito i CFU!");
		        		return false;
		        	}
			    }
		        return true;
	       
	            
	        case "aiuto":
	            this.aiuto();
	            return true;
	        
	        case "prendi":
	        	this.prendi(comandoDaEseguire.getParametro());
	        	return true;
	        
	        case "inventario":
	        	this.inventario();
	        	return true;
	        	
	        case "rimuovi":
	        	this.rimuovi(comandoDaEseguire.getParametro());
	        	return true;
	        default:
	            System.out.println("Comando sconosciuto");
	            return true;
	    }
}
			
		
			
		
	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			System.out.print(elencoComandi[i]+" ");
		System.out.println();
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
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
	
	/*
	 *  Comando "prendi"
	 */
	private void prendi(String nomeAttrezzo) {
	    if (nomeAttrezzo == null) {
	        System.out.println("Che cosa vuoi prendere?");
	        return;
	    }

	    Stanza stanzaCorrente = this.partita.lab.getStanzaCorrente();
	    
	    if (!stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
	        System.out.println("L'attrezzo '" + nomeAttrezzo + "' non è presente nella stanza.");
	        return;
	    }

	    Attrezzo attrezzoDaPrendere = stanzaCorrente.getAttrezzo(nomeAttrezzo);
	    
	    if (this.partita.gio.borsa.addAttrezzo(attrezzoDaPrendere)) { // Se l'attrezzo è stato preso
	        stanzaCorrente.removeAttrezzo(nomeAttrezzo);
	        System.out.println("Hai preso: " + nomeAttrezzo);
	    } else {
	        System.out.println("Non puoi prendere '" + nomeAttrezzo + "', la borsa è piena o troppo pesante.");
	    }
	}
	
	/*
	 * Comando inventario
	 */
	public void inventario() {
		System.out.println(partita.gio.borsa.toString());
	}
	
	/**
	 * comando per rimuovere un oggetto dalla borsa
	 */
	private void rimuovi(String nomeAttrezzo) {
		
		if(this.partita.gio.borsa.isEmpty()) {
			System.out.println("Borsa gia vuota");
		        return;
		}
		if (nomeAttrezzo == null) {
	        System.out.println("Che cosa vuoi rimuovere dalla borsa?");
	        return;
	    }
		
		Borsa borsaCorrente = this.partita.gio.borsa;
		Attrezzo attrezzoDaRimuovere = borsaCorrente.getAttrezzo(nomeAttrezzo);
		
		if(borsaCorrente.hasAttrezzo(nomeAttrezzo)) {
			borsaCorrente.removeAttrezzo(nomeAttrezzo); 

		    Stanza stanzaCorrente = this.partita.lab.getStanzaCorrente();
		    stanzaCorrente.addAttrezzo(attrezzoDaRimuovere);
		    System.out.println("hai posato "+nomeAttrezzo+" in "+stanzaCorrente);
			
		}else {
			System.out.println("L'attrezzo che vuoi eliminare non è nella tua borsa");
	        return;
		}
	    
	}
	
	/**
	 * Comando "Fine".
	 */
	private void fine() {
		System.out.println("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
	
}