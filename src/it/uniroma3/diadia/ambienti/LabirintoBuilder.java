package it.uniroma3.diadia.ambienti;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class LabirintoBuilder {

	private Labirinto labirinto;
	private Stanza ultimaStanzaAggiunta;
	private Map<String, Stanza> nome2stanza;

	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.nome2stanza = new HashMap<String, Stanza>();
	}

	public Map<String, Stanza> getNome2stanza() {
		return nome2stanza;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
		Stanza i = new Stanza(stanzaIniziale);
		this.labirinto.setStanzaCorrente(i);
		this.labirinto.setStanzaIniziale(i);
		this.UltimaStanzaAggiuntaEAggiorna(i);
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
		Stanza s = new Stanza(stanzaVincente);
		this.labirinto.setStanzaVincente(s);
		this.UltimaStanzaAggiuntaEAggiorna(s);
		return this;
	}

	public LabirintoBuilder addStanza(String stanza) {
		Stanza s = new Stanza(stanza);
		this.UltimaStanzaAggiuntaEAggiorna(s);
		return this;
	}

	public LabirintoBuilder addAttrezzo(String attrezzo, int peso) {
		Attrezzo a = new Attrezzo(attrezzo, peso);
		if (this.ultimaStanzaAggiunta == null)
			return this;

		if (!this.ultimaStanzaAggiunta.hasAttrezzo(attrezzo)) {
			this.ultimaStanzaAggiunta.addAttrezzo(a);
		}
		return this;
	}

	public LabirintoBuilder addAdiacenza(String stanzaCorrente, String stanzaAdiecente, String direzione) {
		Stanza c = this.nome2stanza.get(stanzaCorrente);
		Stanza a = this.nome2stanza.get(stanzaAdiecente);
		c.impostaStanzaAdiacente(direzione, a);
		return this;
	}

	public LabirintoBuilder addStanzaMagica(String nome) {
		Stanza stanza = new StanzaMagica(nome);
		this.UltimaStanzaAggiuntaEAggiorna(stanza);
		return this;
	}

	public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
		Stanza stanza = new StanzaMagica(nome, soglia);
		this.UltimaStanzaAggiuntaEAggiorna(stanza);
		return this;
	}

	public LabirintoBuilder addStanzaBuia(String nome, String attrezzoPerVedere) {
		Stanza stanza = new StanzaBuia(nome, attrezzoPerVedere);
		this.UltimaStanzaAggiuntaEAggiorna(stanza);
		return this;
	}

	public LabirintoBuilder addStanzaBloccata(String nome, String attrezzoSbloccante, String direzioneBloccata) {
		Stanza stanza = new StanzaBloccata(nome, attrezzoSbloccante, direzioneBloccata);
		this.UltimaStanzaAggiuntaEAggiorna(stanza);
		return this;
	}

	public LabirintoBuilder addStanzaBloccataIniziale(String nome, String direzione, String attrezzo) {
		Stanza stanza = new StanzaBloccata(nome, attrezzo, direzione);
		this.labirinto.setStanzaCorrente(stanza);
		this.labirinto.setStanzaIniziale(stanza);
		this.UltimaStanzaAggiuntaEAggiorna(stanza);
		return this;
	}

	private void UltimaStanzaAggiuntaEAggiorna(Stanza stanza) {
		this.ultimaStanzaAggiunta = stanza;
		this.nome2stanza.put(stanza.getNome(), stanza);
	}

	public Collection<Stanza> getListaStanze() {
		return this.nome2stanza.values();
	}

	/* --- AGGIUNTA PERSONAGGI IN ULTIMA STANZA --- */

	public LabirintoBuilder addCane(String nome, String presentazione) {
		if (this.ultimaStanzaAggiunta != null) {
			Cane cane = new Cane(nome, presentazione);
			this.ultimaStanzaAggiunta.setPersonaggio(cane);
		}
		return this;
	}

	public LabirintoBuilder addMago(String nome, String presentazione, Attrezzo attrezzo) {
		if (this.ultimaStanzaAggiunta != null) {
			Mago mago = new Mago(nome, presentazione, attrezzo);
			this.ultimaStanzaAggiunta.setPersonaggio(mago);
		}
		return this;
	}

	public LabirintoBuilder addStrega(String nome, String presentazione) {
		if (this.ultimaStanzaAggiunta != null) {
			Strega strega = new Strega(nome, presentazione);
			this.ultimaStanzaAggiunta.setPersonaggio(strega);
		}
		return this;
	}

	/* --- AGGIUNTA PERSONAGGI IN STANZA SPECIFICA --- */

	public LabirintoBuilder addCane(String nomeStanza, String nome, String presentazione) {
		Stanza stanza = this.nome2stanza.get(nomeStanza);
		if (stanza != null) {
			Cane cane = new Cane(nome, presentazione);
			stanza.setPersonaggio(cane);
		}
		return this;
	}

	public LabirintoBuilder addMago(String nomeStanza, String nome, String presentazione, Attrezzo attrezzo) {
		Stanza stanza = this.nome2stanza.get(nomeStanza);
		if (stanza != null) {
			Mago mago = new Mago(nome, presentazione, attrezzo);
			stanza.setPersonaggio(mago);
		}
		return this;
	}

	public LabirintoBuilder addStrega(String nomeStanza, String nome, String presentazione) {
		Stanza stanza = this.nome2stanza.get(nomeStanza);
		if (stanza != null) {
			Strega strega = new Strega(nome, presentazione);
			stanza.setPersonaggio(strega);
		}
		return this;
	}
}
