package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
	
class ComandoVaiTest {
	
	private ComandoVai comandoVai;
	private Partita partita;
	private Stanza stanzaIniziale;
	private Stanza stanzaAdiacente;
	
	@BeforeEach
	public void setUp() {
		
		this.comandoVai = new ComandoVai();
		this.partita = new Partita();
		this.stanzaIniziale = new Stanza("atrio");
		this.stanzaAdiacente = new Stanza("segreteria");
		
		this.stanzaIniziale.impostaStanzaAdiacente("nord", stanzaAdiacente);
		this.partita.setStanzaCorrente(stanzaIniziale);
		this.comandoVai.setIo(new IOConsole());
	}
	
	@Test
	public void testVaiDirezioneNull() {
		this.comandoVai.setParametro(null);
		this.comandoVai.esegui(partita);
		assertEquals("atrio", this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testVaiDirezioneValida() {
		this.comandoVai.setParametro("nord");
		this.comandoVai.esegui(partita);
		assertEquals("segreteria", this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testVaiDirezioneSbagliata() {
		this.comandoVai.setParametro("sud");
		this.comandoVai.esegui(partita);
		assertEquals("atrio", this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testContatoreCfu() {
		int cfu = this.partita.getGiocatore().getCfu();
		this.comandoVai.setParametro("nord");
		this.comandoVai.esegui(partita);
		assertEquals(cfu-1, this.partita.getGiocatore().getCfu());
	}
	
}
