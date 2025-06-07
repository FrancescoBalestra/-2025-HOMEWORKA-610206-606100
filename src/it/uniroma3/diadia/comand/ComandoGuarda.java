package it.uniroma3.diadia.comand;

//import it.uniroma3.diadia.IO;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {

	//private IO io; non serve più perché viene implementato nella classe astratta
	private final static String NOME = "guarda";


	@Override
	public void esegui(Partita partita) {
		this.getIo().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		this.getIo().mostraMessaggio("Hai ancora: "+partita.getGiocatore().getCfu()+ "CFU");
		this.getIo().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		
	}

	@Override
	public String getNome() {
		return NOME;
	}
}