package it.uniroma3.diadia.comand;

import java.util.Scanner;



import it.uniroma3.diadia.IO;


public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi{

	private IO io;
	public FabbricaDiComandiFisarmonica(IO io) {
		this.io = io;
	}
	
	public Comando costruisciComando(String istruzione) throws Exception {
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); // prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();
		// seconda parola: eventuale parametro
		
		
		StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comand.Comando");
		nomeClasse.append( Character.toUpperCase(nomeComando.charAt(0)) );
		
		nomeClasse.append( nomeComando.substring(1) ) ;
	
		// POSSIBILE ALTERNATIVA basata sul rendere il tipo Class<Comando> esplicito:
		comando = ((Class<Comando>)Class.forName(nomeClasse.toString())).newInstance();
		
		comando.setIo(this.io);
		comando.setParametro(parametro);
		
		return comando;
		//scannerDiParole.close();
	}
}