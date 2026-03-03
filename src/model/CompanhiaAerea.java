
package model;


import observer.Observer;
import javax.swing.JTextArea;

public class CompanhiaAerea implements Observer {
    private String nome;
    private JTextArea areaNotificacoes;

    public CompanhiaAerea(String nome, JTextArea areaNotificacoes) {
        this.nome = nome;
        this.areaNotificacoes = areaNotificacoes;
    }

    @Override
    public void atualizar(String mensagem) {
        areaNotificacoes.append("[Companhia " + nome + "] " + mensagem + "\n");
    }
}