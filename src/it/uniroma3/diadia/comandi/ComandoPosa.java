package it.uniroma3.diadia.comandi;

//import it.uniroma3.diadia.IO;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa extends AbstractComando {
	
	private String nomeAttrezzo;
	private final static String NOME = "posa";


	@Override
	public void esegui(Partita partita) {
		Borsa borsa = partita.getGiocatore().getBorsa();
		
		// Primo caso: se la borsa è vuota
		if(borsa.isEmpty()) {
			this.getIo().mostraMessaggio("La borsa è vuota non puoi posare nulla!");
			return;
		}
		// Secondo caso: se non passo nessun parametro
		if (nomeAttrezzo == null) {
			this.getIo().mostraMessaggio("Che attrezzo vuoi posare? Specifica un nome.");
			return;
		}
		// Mi assicuro che esista nomeattrezzo
		Attrezzo a = borsa.getAttrezzo(nomeAttrezzo);
		
		// Terzo caso: non esiste nella borsa
		if (a == null) {
			this.getIo().mostraMessaggio("Non hai '" + nomeAttrezzo + "' nella borsa.");
			return;
		}

		// SUCCESSO!!!
		if (partita.getStanzaCorrente().getNumeroAttrezziPossibili() > 0) {
			partita.getStanzaCorrente().addAttrezzo(a);
			borsa.removeAttrezzo(nomeAttrezzo);
			this.getIo().mostraMessaggio("Hai posato " + nomeAttrezzo + "!");
		}
		// Quarto caso: stanza piena
		else {
			this.getIo().mostraMessaggio("La stanza è piena, non puoi posare altri attrezzi.");
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo  = parametro;

	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}


	@Override
	public String getNome() {
		return NOME;
	}

}