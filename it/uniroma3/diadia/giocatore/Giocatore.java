package it.uniroma3.diadia.giocatore;

public class Giocatore {
    private static final int CFU_INIZIALI = 10; // Valore predefinito dei CFU abbassato per l'immediatezza dei test
    public int cfu;
    public Borsa borsa;

    /**
     * Costruttore della classe Giocatore.
     * Inizializza i CFU e crea una nuova borsa.
     */
    public Giocatore() {
        this.cfu = CFU_INIZIALI;		// inizializza i cfu che si hanno in partenza
        borsa = new Borsa();			// inizializza la borsa
    }

    /**
     * Restituisce il numero di CFU attuali del giocatore.
     * @return CFU attuali.
     */
    public int getCfu() {
        return this.cfu;
    }

    /**
     * Imposta i CFU del giocatore.
     * @param cfu Nuovo valore dei CFU.
     */
    public void setCfu(int cfu) {
        this.cfu = cfu;
    }

    /**
     * Decrementa i CFU del giocatore di 1.
     */
    public void decrementaCfu() {
        this.cfu -= 1;
    }
}