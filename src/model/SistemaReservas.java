package model;

import observer.Subject;
import observer.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class SistemaReservas implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private Map<String, String> voosCancelados = new HashMap<>();  
    private Map<String, String> alteracoesHorario = new HashMap<>(); 

    @Override
    public void registrarObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removerObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notificarObservers(String mensagem) {
        for (Observer observer : observers) {
            observer.atualizar(mensagem);
        }
    }

    public void cancelarVoo(String numeroVoo, String horario) {
       
        String novoHorario = alteracoesHorario.get(numeroVoo + "|" + horario);
        
        if (novoHorario != null) {
           
            voosCancelados.put(numeroVoo + "|" + novoHorario, "CANCELADO");
            notificarObservers("CANCEL|" + numeroVoo + "|" + novoHorario + "|" + horario);
        } else {
           
            voosCancelados.put(numeroVoo + "|" + horario, "CANCELADO");
            notificarObservers("CANCEL|" + numeroVoo + "|" + horario + "|" + horario);
        }
    }

    public void alterarHorario(String numeroVoo, String horarioAntigo, String novoHorario) {
      
        alteracoesHorario.put(numeroVoo + "|" + horarioAntigo, novoHorario);
        notificarObservers("ALTER|" + numeroVoo + "|" + horarioAntigo + "|" + novoHorario);
    }

    public boolean isVooCancelado(String numeroVoo, String horario) {
       
        return voosCancelados.containsKey(numeroVoo + "|" + horario);
    }

    public String getNovoHorario(String numeroVoo, String horarioAntigo) {
      
        return alteracoesHorario.get(numeroVoo + "|" + horarioAntigo);
    }
}