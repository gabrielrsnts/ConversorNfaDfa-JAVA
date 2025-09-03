package com.exemplo.model;

import java.util.List;

public class Transicoes {
    
    private String initial;
    
    private String symbol;
    
    private Object end; // Pode ser String "null" ou List<String>
    
    public Transicoes() {
    }
    
    public Transicoes(String initial, String symbol, Object end) {
        this.initial = initial;
        this.symbol = symbol;
        this.end = end;
    }
    
    public String getInitial() {
        return initial;
    }
    
    public void setInitial(String initial) {
        this.initial = initial;
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    public Object getEnd() {
        return end;
    }
    
    public void setEnd(Object end) {
        this.end = end;
    }

    public boolean temDestino() {
        return end != null && !end.equals("null") && !((List<String>) end).isEmpty();
    }

    public boolean levaPara(String estado) {
        return end != null && ((List<String>) end).contains(estado);
    }
    
    @Override
    public String toString() {
        return "Transicoes{" +
                "initial='" + initial + '\'' +
                ", symbol='" + symbol + '\'' +
                ", end=" + end +
                '}';
    }
}
