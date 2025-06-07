package it.uniroma3.diadia.comand;

//import it.uniroma3.diadia.IO;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {

	
	private final static String NOME = "Comando sconosciuto";
	
	@Override
	public void esegui(Partita partita) {
		this.getIo().mostraMessaggio("Comando sconosciuto");
	}

	@Override
	public String getNome() {
		return NOME;
	}

}