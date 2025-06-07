package it.uniroma3.diadia.comand;

//import it.uniroma3.diadia.IO;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class ComandoPrendi extends AbstractComando {

	private String nomeAttrezzo;
	private static final String NOME = "prendi";
	
	@Override
	public void esegui(Partita partita) {
		
		Attrezzo a = partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		
		// Primo caso: nessun parametro
		if(nomeAttrezzo == null) {
			this.getIo().mostraMessaggio("Specifica un attrezzo da prendere!");
			return;
		}
		// Secondo caso: non esiste l'oggetto nella stanza
		if(!(partita.getLabirinto().getStanzaCorrente().hasAttrezzo(nomeAttrezzo))) {
			this.getIo().mostraMessaggio("In " + partita.getLabirinto().getStanzaCorrente().getNome() + " non c'è " + nomeAttrezzo + "!");
			return;
		}
		// Terzo caso: SUCCESSO!!
		if(partita.getGiocatore().getBorsa().getPesoRimanente(a)) {
			partita.getGiocatore().getBorsa().addAttrezzo(a);
			partita.getLabirinto().getStanzaCorrente().removeAttrezzo(a);
			this.getIo().mostraMessaggio("Hai ottenuto "+ nomeAttrezzo +"!");
		} 
		// Quarto caso: la borsa è troppo piena
		else {
			this.getIo().mostraMessaggio("Attrezzo troppo pesante per entrare nella borsa!");
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
	public String getNome() {
		return NOME;
	}
}