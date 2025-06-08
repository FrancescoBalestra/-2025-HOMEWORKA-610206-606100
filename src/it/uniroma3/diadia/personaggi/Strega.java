package it.uniroma3.diadia.personaggi;

import java.util.Iterator;

import java.util.Set;
import java.util.HashSet;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{
	private Set<Attrezzo> att;
	
	private static final String MESSAGGIO_RISATA = Proprietà.getMessaggioRisata();
	private static final String MESSAGGIO_NON_SALUTATA = Proprietà.getMessaggioNonSalutata();
	private static final String MESSAGGIO_INGANNO = Proprietà.getMessaggioInganno();
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
		this.att=new HashSet<Attrezzo>();
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		this.att.add(attrezzo);
		return MESSAGGIO_RISATA;
	}
	
	@Override 
	public String agisci(Partita partita) {
	    Set<String> direzioni = partita.getStanzaCorrente().getDirezioni();
	    if (direzioni.isEmpty())
	        return "Non ci sono stanze adiacenti!";

	    Iterator<String> iter = direzioni.iterator();
	    String primaDirezione = iter.next();
	    Stanza stanzaMigliore = partita.getStanzaCorrente().getStanzaAdiacente(primaDirezione);

	    while (iter.hasNext()) {
	        String dir = iter.next();
	        Stanza stanza = partita.getStanzaCorrente().getStanzaAdiacente(dir);

	        if (!this.haSalutato()) {
	            if (stanza.getAttrezzi().size() < stanzaMigliore.getAttrezzi().size())
	                stanzaMigliore = stanza;
	        } else {
	            if (stanza.getAttrezzi().size() > stanzaMigliore.getAttrezzi().size())
	                stanzaMigliore = stanza;
	        }
	    }

	    partita.setStanzaCorrente(stanzaMigliore);

	    if (!this.haSalutato())
	        return MESSAGGIO_NON_SALUTATA;
	    return MESSAGGIO_INGANNO;
	}


}