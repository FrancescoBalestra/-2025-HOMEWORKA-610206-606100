package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * classe che si occupa di modellare una borsa 
 * La borsa ha una capienza massima oltre la quale non pu� contenere altri oggetti
 * 
 * 
 * @author utente
 *
 */

public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}
	
	//funzione che aggiunge un attrezzo all'interno della borsa 
	public boolean addAttrezzo(Attrezzo attrezzo) {
		
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())		//se l'oggetto � troppo pesante per essere contenuto nella borsa
			return false;													//la funzione ritorna false
		
		if (this.numeroAttrezzi==10)										//se ho raggiunto il numero massimo di oggetti contenuti in borsa 
			return false;													//la funzione ritorna false
		
		this.attrezzi[this.numeroAttrezzi] = attrezzo;						//aggiungo l'attrezzo raccolto nell'ultima posizione dell'array
		this.numeroAttrezzi++;					
		
		return true;
	}
	
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	//ritorna la classe attrezzo dal nome che passo come parametro
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.numeroAttrezzi;i++) {
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
				a = attrezzi[i];
			}
		}
		return a;
	}
	
	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.numeroAttrezzi; i++) {
			peso += this.attrezzi[i].getPeso();
		}
		return peso;
	}
	
	//controlla se la borsa � vuota
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	public boolean getPesoRimanente(Attrezzo a) {
		if(a != null && this.getPesoMax()-this.getPeso()>=a.getPeso())
			return true;
		return false; 
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	//funzione che rimuove un attrezzo dalla borsa
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		
		for (int i = 0; i < this.numeroAttrezzi; i++) {
	        if (this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
	            Attrezzo rimosso = this.attrezzi[i];

	            // Shiftare gli elementi rimanenti indietro di un posto
	            for (int j = i; j < this.numeroAttrezzi - 1; j++) {
	                this.attrezzi[j] = this.attrezzi[j + 1];
	            }

	            // Eliminare l'ultimo riferimento e aggiornare il contatore
	            this.attrezzi[this.numeroAttrezzi - 1] = null;
	            this.numeroAttrezzi--;

	            return rimosso;
	        }
	    }
		return a;
	}
	
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++) {
				s.append(attrezzi[i].toString()+" ");
			}
		}
		else {
			s.append("Borsa vuota");
			return s.toString();
		}
		return s.toString();
	}
}