package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;

import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comand.Comando;
import it.uniroma3.diadia.comand.FabbricaDiComandiFisarmonica;

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
	
	private Partita partita;
	private IO io;

	public DiaDia(IO console, Labirinto labirinto) {
		this.io = console;
		this.partita = new Partita(labirinto);
	}

	public void gioca() throws Exception {
		String istruzione; 
		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do {
			istruzione = io.leggiRiga();
		}
		while (!processaIstruzione(istruzione) );

	}   

	/**System.in
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 * @throws Exception 
	 */
	private boolean processaIstruzione(String istruzione) throws Exception {
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
			
	public static void main(String[] argc) throws Exception {
		IO console = new IOConsole();
		Labirinto labirinto = new LabirintoBuilder()
							.addStanza("Aula N10")
							.addAttrezzo("lanterna", 2)
							.addAttrezzo("ratto", 1)
							.addStanzaMagica("Aula N11")
							.addAttrezzo("osso", 1)
							.addAttrezzo("pollo", 1)
							.addAttrezzo("pala", 1)
							.addStanzaBloccataIniziale("Atrio", "nord", "ssap")
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
		DiaDia gioco = new DiaDia(console, labirinto);
		gioco.gioca();
	}
}