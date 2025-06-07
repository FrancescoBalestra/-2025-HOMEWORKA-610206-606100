package it.uniroma3.diadia.comandi;

import java.util.Scanner;



import it.uniroma3.diadia.IO;


public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi{

	private IO io;
	public FabbricaDiComandiFisarmonica(IO io) {
		this.io = io;
	}
	
	@Override
	public Comando costruisciComando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;

		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); // prima parola
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next(); // seconda parola
		
		
		if (nomeComando == null) {
			comando = new ComandoNonValido();
			comando.setIo(this.io);
			comando.setParametro(parametro);
			return comando;
		}


		String nomeClasse = "it.uniroma3.diadia.comandi.Comando";  
		nomeClasse += Character.toUpperCase(nomeComando.charAt(0));
		nomeClasse += nomeComando.substring(1);

		try {
			comando = (Comando)Class.forName(nomeClasse).newInstance();
		} catch (Exception e) {
			comando = new ComandoNonValido();
		}

		// IMPOSTA IO E PARAMETRO SEMPRE
		comando.setIo(this.io);
		comando.setParametro(parametro);

		return comando;
	}

}