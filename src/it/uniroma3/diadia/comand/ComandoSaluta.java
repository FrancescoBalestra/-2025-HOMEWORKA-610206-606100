package it.uniroma3.diadia.comand;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando{
	
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().getPersonaggio() != null)
			io.mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());
		else
			io.mostraMessaggio("Non puoi salutare nessuno");
	}

	@Override
	public void setIo(IO io) {
		this.io = io;
	}
}