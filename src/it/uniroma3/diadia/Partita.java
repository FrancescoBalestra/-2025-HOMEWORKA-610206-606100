package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	public Labirinto lab; 
	public Giocatore gio;

	private boolean finita;
	
	public Partita(){		
		lab = new Labirinto();		// inizializza il labirinto 
		lab.creaStanze(); 			// i inizializzo la mappa di gioco
		gio = new Giocatore();		// inizializzo il giocatore
		this.finita = false;		// inizializzo la partita come non ancora finita
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.lab.getStanzaCorrente() == this.lab.getStanzaVincente(); 		// se il giocatore si trova nella biblioteca finisce il gioco
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.gio.cfu == 0);			// la partita finisce se è vinta o si finiscono i cfu 
	}

	/**
	 * Imposta la partita come finita
	 */
	public void setFinita() {
		this.finita = true;				// aggiornamento dello stato della partita che diventa terminata
	}
	
	public String toString() {
		return this.lab.getStanzaCorrente() + "\nCfu = " + this.gio.getCfu();	// descrizione della stanza + quanti cfu mancano
	}
}