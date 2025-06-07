package it.uniroma3.diadia.comand;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando {

    private IO io;
    private String parametro;
    private final static String NOME = "AbstractComando";

    @Override
    public void esegui(Partita partita) {
        // Metodo astratto, da implementare nelle sottoclassi
    }

    @Override
    public String getParametro() {
        return this.parametro;
    }

    @Override
    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    @Override
    public void setIo(IO io) {
        this.io = io;
    }

    public IO getIo() {
        return this.io;
    }

    @Override
    public String getNome() {
        return NOME;
    }
}
