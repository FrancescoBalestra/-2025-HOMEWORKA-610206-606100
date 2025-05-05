package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa implements Comando {
	private IO io;
	private String nomeAttrezzo;
	private static final String NOME = "posa";
	
	@Override
	public void esegui(Partita partita) {
		
		Borsa borsa = partita.getGiocatore().getBorsa();
		
		// Primo caso: se la borsa è vuota
		if(borsa.isEmpty()) {
			io.mostraMessaggio("La borsa è vuota non puoi posare nulla!");
			return;
		}
		// Secondo caso: se non passo nessun parametro
		if (nomeAttrezzo == null) {
			io.mostraMessaggio("Che attrezzo vuoi posare? Specifica un nome.");
			return;
		}
		// Mi assicuro che esista nomeattrezzo
		Attrezzo a = borsa.getAttrezzo(nomeAttrezzo);
		
		// Terzo caso: non esiste nella borsa
		if (a == null) {
			io.mostraMessaggio("Non hai '" + nomeAttrezzo + "' nella borsa.");
			return;
		}

		// SUCCESSO!!!
		if (partita.getStanzaCorrente().getNumeroAttrezziPossibili() > 0) {
			partita.getStanzaCorrente().addAttrezzo(a);
			borsa.removeAttrezzo(nomeAttrezzo);
			io.mostraMessaggio("Hai posato " + nomeAttrezzo + "!");
		}
		// Quarto caso: stanza piena
		else {
			io.mostraMessaggio("La stanza è piena, non puoi posare altri attrezzi.");
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
	public void setIo(IO io) {
		this.io = io;
	}

	@Override
	public String getNome() {
		return NOME;
	}

}
