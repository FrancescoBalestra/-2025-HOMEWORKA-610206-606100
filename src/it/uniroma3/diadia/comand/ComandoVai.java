package it.uniroma3.diadia.comand;

import it.uniroma3.diadia.Partita;
//import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoVai extends AbstractComando {
	
	private String direzione;

	private final static String NOME = "vai";

	/**
	 * esecuzione del comando
	 */
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if (this.direzione == null) {
			super.getIo().mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
			return;
		}

		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if (prossimaStanza == null) {
			super.getIo().mostraMessaggio("Direzione inesistente");
			return;
		}

		partita.setStanzaCorrente(prossimaStanza);
		super.getIo().mostraMessaggio(partita.getStanzaCorrente().getNome());
		Giocatore giocatore = partita.getGiocatore();
		giocatore.setCfu(giocatore.getCfu() - 1);
	}
	
	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}
	
	@Override
	public String getParametro() {
		return this.direzione;
	}

	
	@Override
	public String getNome() {
		return NOME;
	}

}