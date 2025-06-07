package it.uniroma3.diadia.comand;

//import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {

    static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda"};
    private final static String NOME = "aiuto";

    @Override
    public void esegui(Partita partita) {
        for (String comando : elencoComandi) {
            this.getIo().mostraMessaggio(comando + " ");
        }
        this.getIo().mostraMessaggio("");
    }

    @Override
    public String getNome() {
        return NOME;
    }
}
