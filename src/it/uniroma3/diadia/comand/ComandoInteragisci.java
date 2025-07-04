package it.uniroma3.diadia.comand;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.*;

public class ComandoInteragisci extends AbstractComando{
	
	private static final String MESSAGGIO_CON_CHI = "Con chi vorresti interagire? ";
	private String messaggio;
	private IO io;
	
	@Override 
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio != null) {
			this.messaggio = personaggio.agisci(partita);
			io.mostraMessaggio(this.messaggio);
		}
		else
			io.mostraMessaggio(MESSAGGIO_CON_CHI);
	}
	
	@Override
	public void setIo(IO io) {
		this.io = io;
	}
}