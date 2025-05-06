package it.uniroma3.diadia.comand;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class ComandoPrendi implements Comando {

	private IO io;
	private String nomeAttrezzo;
	private static final String NOME = "prendi";

	@Override
	public void esegui(Partita partita) {

		Attrezzo a = partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo);

		// Primo caso: nessun parametro
		if(nomeAttrezzo == null) {
			io.mostraMessaggio("Specifica un attrezzo da prendere!");
			return;
		}

		// Secondo caso: non esiste l'oggetto nella stanza
		if(!(partita.getLabirinto().getStanzaCorrente().hasAttrezzo(nomeAttrezzo))) {
			io.mostraMessaggio("In " + partita.getLabirinto().getStanzaCorrente().getNome() + " non c'è " + nomeAttrezzo + "!");
			return;
		}

		// Terzo caso: SUCCESSO!!
		if(partita.getGiocatore().getBorsa().getPesoRimanente(a)) {
			partita.getGiocatore().getBorsa().addAttrezzo(a);
			partita.getLabirinto().getStanzaCorrente().removeAttrezzo(nomeAttrezzo);
			io.mostraMessaggio("Hai ottenuto "+ nomeAttrezzo +"!");
		}

		// Quarto caso: la borsa è troppo piena
		else {
			io.mostraMessaggio("Attrezzo troppo pesante per entrare nella borsa!");
		}
	}


	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
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