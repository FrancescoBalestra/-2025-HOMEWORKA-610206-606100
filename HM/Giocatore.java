package HM;

public class Giocatore {
    private static final int CFU_INIZIALI = 3; // Valore predefinito dei CFU
    public int cfu;

    /**
     * Costruttore della classe Giocatore.
     * Inizializza i CFU e crea una nuova borsa.
     */
    public Giocatore() {
        this.cfu = CFU_INIZIALI;
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