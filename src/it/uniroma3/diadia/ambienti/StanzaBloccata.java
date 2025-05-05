package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	private boolean haPasspartou;
	
	public StanzaBloccata(String nome) {
		super(nome);
		this.haPasspartou =  false;
	}
	
	public void setPasspartou(boolean haPasspartou) {
		this.haPasspartou = haPasspartou;
	}
	
	
}
