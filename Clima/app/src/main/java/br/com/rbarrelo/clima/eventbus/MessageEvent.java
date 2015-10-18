package br.com.rbarrelo.clima.eventbus;

import br.com.rbarrelo.clima.modelos.simplificado.Cidade;

/**
 * Created by rafaelbarrelo on 10/17/15.
 */
public class MessageEvent {

    private Cidade cidade;

    public MessageEvent(Cidade cidade) {
        this.cidade = cidade;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
