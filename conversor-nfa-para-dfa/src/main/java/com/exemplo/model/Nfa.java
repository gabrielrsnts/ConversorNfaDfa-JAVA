package com.exemplo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Nfa {

    private List<String> alphabet;
    private List<String> states;
    private String initial_state;
    private List<String> end_state;
    private List<Transicoes> transiction;

    public Nfa() {
    }

    public Nfa(List<String> alphabet, List<String> end_state, String initial_state, List<String> states, List<Transicoes> transiction) {
        this.alphabet = alphabet;
        this.end_state = end_state;
        this.initial_state = initial_state;
        this.states = states;
        this.transiction = transiction;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(List<String> alphabet) {
        this.alphabet = alphabet;
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public String getInitial_state() {
        return initial_state;
    }

    public void setInitial_state(String initial_state) {
        this.initial_state = initial_state;
    }

    public List<String> getEnd_state() {
        return end_state;
    }

    public void setEnd_state(List<String> end_state) {
        this.end_state = end_state;
    }

    public List<Transicoes> getTransiction() {
        return transiction;
    }

    public void setTransiction(List<Transicoes> transiction) {
        this.transiction = transiction;
    }

    public boolean ehEstadoFinal(String estado) {
        return end_state.contains(estado);
    }

}
