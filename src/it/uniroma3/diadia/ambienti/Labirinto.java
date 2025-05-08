package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	public Stanza stanzaCorrente;
	public Stanza stanzaVincente;
	public Stanza stanza;
	
	
	
	/* Crea tutte le stanze e le porte di collegamento */
    public void creaStanze() {

		// crea gli attrezzi 
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo ratto = new Attrezzo("ratto", 2);
		Attrezzo pollo = new Attrezzo("pollo",2);
		Attrezzo pala = new Attrezzo("pala", 10); 		//oggetto creato per test di errore nella borsa
    	Attrezzo passpartou = new Attrezzo("uotrapssap", 1);
		
		/* crea stanze del labirinto */
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza aulaN11 = new StanzaMagica("Aula N11");
		Stanza atrio = new StanzaBloccata("Atrio", "nord", "passpartou");
		Stanza laboratorio = new StanzaBuia("Laboratorio Campus", "lanterna");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		aulaN10.addAttrezzo(ratto);
		aulaN11.addAttrezzo(osso);
		aulaN11.addAttrezzo(pollo);
		aulaN11.addAttrezzo(pala);
		laboratorio.addAttrezzo(passpartou);

		// il gioco comincia nell'atrio
        stanzaCorrente = atrio;  
		stanzaVincente = biblioteca;
    }
    
    public Stanza getStanzaVincente() {
		return stanzaVincente;				// ritorna la biblioteca
	} 

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;		
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;			// ritorna la stanza in cui si trova il giocatore
	} 
}