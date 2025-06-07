package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
//import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoRegala extends AbstractComando{
	private static final String MESSAGGIO_CON_CHI = "Non c'è nessuno nella stanza a cui puoi regalare ";//Proprietà.getMessaggioConChi();
	final static private String MESSAGGIO_ATTREZZO_INESISTENTE = "Non possiedi ";//Proprietà.getMessaggioAttrezzoInesistente();
	final static private String MESSAGGIO_PARAMETRO_NULL = "Che cosa vorresti regalare? ";
	
	public ComandoRegala() {
		super.setNome(this.getClass().getSimpleName());
	}
	
	@Override 
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Borsa borsa = partita.getGiocatore().getBorsa();
		
		//Caso in cui il parametro è null
		if(getParametro()==null) {
			super.getIo().mostraMessaggio(MESSAGGIO_PARAMETRO_NULL);
			return;
		}
		
		//Caso in cui non ci sia nessuno all'interno della stanza 
		if(stanzaCorrente.getPersonaggio()==null) {
			super.getIo().mostraMessaggio(MESSAGGIO_CON_CHI + getParametro());
			return;
		}
		//Caso in cui non ci sia l'attrezzo mandato come parametro nella borsa
		if(!borsa.hasAttrezzo(getParametro())) {
			super.getIo().mostraMessaggio(MESSAGGIO_ATTREZZO_INESISTENTE + getParametro());
			return;
		}
		
		//Caso in cui ho l'attrezzo in borsa e c'è il personaggio
		if(stanzaCorrente.getPersonaggio()!=null) {
			super.getIo().mostraMessaggio(stanzaCorrente.getPersonaggio().riceviRegalo(borsa.getAttrezzo(getParametro()), partita));
			borsa.removeAttrezzo(getParametro());
		}
	}

}