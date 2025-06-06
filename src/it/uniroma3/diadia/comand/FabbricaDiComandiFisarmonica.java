package it.uniroma3.diadia.comand;

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
			nomeComando = scannerDiParole.next(); // prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();
		// seconda parola: eventuale parametro
		
		String nomeClasse = "it.uniroma3.diadia.comand.Comando";
		nomeClasse += Character.toUpperCase(nomeComando.charAt(0));
		nomeClasse += nomeComando.substring(1);
		
		try {
			comando = (Comando)Class.forName(nomeClasse).newInstance();
			comando.setIo(this.io);
			comando.setParametro(parametro);
		} catch (Exception e) {
			comando = new ComandoNonValido();
			this.io.mostraMessaggio("Comando inesistente");
		}

		
		return comando;
		//scannerDiParole.close();
	}
}