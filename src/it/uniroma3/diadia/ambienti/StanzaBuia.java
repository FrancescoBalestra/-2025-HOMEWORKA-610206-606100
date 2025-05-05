package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuia extends Stanza {

	private  String attrezzoLucente;
	
	public StanzaBuia(String nome , String attrezzoLucente) {
		super(nome);
		this.attrezzoLucente = attrezzoLucente;
	}

	@Override
	public String getDescrizione() {
	    if (!this.hasAttrezzo(attrezzoLucente)) {
	        return "qui c'è buio pesto";
	    }
	    return super.getDescrizione();
	}
}