package model;

import observer.Observer;
import javax.swing.JOptionPane;

public class Passageiro implements Observer {
    private String nome;
    private String numeroVoo;
    private String horarioVoo;

    public Passageiro(String nome, String numeroVoo, String horarioVoo) {
        this.nome = nome;
        this.numeroVoo = numeroVoo;
        this.horarioVoo = horarioVoo;
    }

@Override
public void atualizar(String mensagem) {
    String[] partes = mensagem.split("\\|");
    
    if (partes.length >= 3 && partes[1].equals(numeroVoo)) {
        String tipo = partes[0];
        String mensagemFinal;
        
        if (tipo.equals("CANCEL")) {
           
            String horarioAfetado = partes[2];
            String horarioOriginal = partes.length > 3 ? partes[3] : horarioAfetado;
            
            if (horarioVoo.equals(horarioAfetado) || horarioVoo.equals(horarioOriginal)) {
                mensagemFinal = "Seu voo foi CANCELADO\n"
                    + "Número: " + numeroVoo + "\n"
                    + (horarioAfetado.equals(horarioOriginal) 
                        ? "Horário: " + horarioAfetado 
                        : "Horário original: " + horarioOriginal + "\n"
                        + "Horário alterado: " + horarioAfetado);
            } else {
                return;
            }
        } 
        else if (tipo.equals("ALTER") && partes.length >= 4) {
            if (horarioVoo.equals(partes[2])) { 
                this.horarioVoo = partes[3];
                mensagemFinal = "Seu voo foi REAGENDADO\n"
                    + "Número: " + numeroVoo + "\n"
                    + "De: " + partes[2] + "\n"
                    + "Para: " + partes[3];
            } else {
                return;
            }
        }
        else {
            return;
        }

        JOptionPane.showMessageDialog(null,
            "⚠️ ATENÇÃO " + nome.toUpperCase() + " ⚠️\n\n" 
            + mensagemFinal,
            "Atualização do Voo " + numeroVoo,
            JOptionPane.WARNING_MESSAGE);
    }
}
}
