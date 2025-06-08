package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comand.*;

import java.io.IOException;
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

	static final private String MESSAGGIO_BENVENUTO = Proprietà.getMessaggioBenvenuto();
	static final private String MESSAGGIO_VITTORIA = Proprietà.getMessaggioVittoria();
	static final private String MESSAGGIO_SCONFITTA = Proprietà.getMessaggioSconfitta();
	

	private Partita partita;
	private IO io;
	
	public DiaDia(IO io) throws FormatoFileNonValidoException, IOException {
		this(new Partita("Labirinto1"), io);
	}
	
	public DiaDia(Partita partita, IO io) {
		this.partita = partita;
		this.io = io;
	}
	
	public DiaDia(IO io, Labirinto labirinto) {
	    this(new Partita(labirinto), io);
	}


	public void gioca() {
		String istruzione; 

		this.io.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do		
			istruzione = this.io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 * @throws Exception 
	 */
	public boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.setIo(this.io);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())

		io.mostraMessaggio(MESSAGGIO_VITTORIA);
		if (!this.partita.giocatoreIsVivo())

		io.mostraMessaggio(MESSAGGIO_SCONFITTA);

		return this.partita.isFinita();
		}
	
	public static void main(String[] argc) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);
			IO io = new IOConsole(scanner);
			DiaDia gioco = new DiaDia(io);

			gioco.gioca();
		} catch (Exception e) { e.printStackTrace();
		} finally { scanner.close(); }
	}
}
