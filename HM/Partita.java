package HM;




/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	public Labirinto lab; //modifica 1
	public Giocatore gio;

	private boolean finita;
	
	public Partita(){		
		lab = new Labirinto(); //modifica 2
		lab.creaStanze();  //modifica 3*/
		gio = new Giocatore();
		this.finita = false;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.lab.getStanzaCorrente() == this.lab.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.gio.cfu == 0);
	}

	/**
	 * Imposta la partita come finita
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	public String toString() {
		return this.lab.getStanzaCorrente() + "\nCfu = " + this.gio.getCfu();
	}
}