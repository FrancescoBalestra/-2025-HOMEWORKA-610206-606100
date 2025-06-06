package it.uniroma3.diadia.comand;

public interface FabbricaDiComandi {
	public Comando costruisciComando(String istruzione)
			throws Exception; // (>> eccezioni)
}