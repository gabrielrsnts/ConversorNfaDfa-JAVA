package com.exemplo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Transicoes {

    private String initial;

    private String symbol;

    private List<String> end; // Lista de estados de destino

    public Transicoes() {
    }

    public Transicoes(String initial, String symbol, List<String> end) {
        this.initial = initial;
        this.symbol = symbol;
        this.end = end;
    }

    @JsonSetter("end")
    @SuppressWarnings("unchecked")
    public void setEstadosDestino(Object end) {
        if (end == null || "null".equals(end.toString())) {
            this.end = new ArrayList<>();
        } else if (end instanceof List) {
            this.end = (List<String>) end;
        } else {
            this.end = new ArrayList<>();
        }
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

    public List<String> getEnd() {
        return end;
    }

    public void setEnd(List<String> end) {
        this.end = end;
    }

}
