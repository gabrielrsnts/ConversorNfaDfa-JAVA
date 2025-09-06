package com.exemplo.conversor;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.exemplo.model.Dfa;
import com.exemplo.model.Nfa;
import com.exemplo.model.Transicoes;
import com.exemplo.util.UtilJackson;

public class ConversorNfaDfa {
    

    private static int stateCounter = 0;
    private static Map<Set<String>, String> stateMapping = new HashMap<>();
    
    /**
     * Converte um NFA para DFA usando o algoritmo de construção de subconjuntos
     */
    public static Dfa converterNfaDfa(Nfa nfa) {

        // 1. Inicializar o DFA com o mesmo alfabeto
        Dfa dfa = new Dfa();
        dfa.setAlphabet(new ArrayList<>(nfa.getAlphabet()));
        
        // 2. Estados do DFA (conjuntos de estados do NFA)
        List<String> dfaStates = new ArrayList<>();
        List<Transicoes> dfaTransitions = new ArrayList<>();
        
        // 3. Estado inicial do DFA é o conjunto contendo apenas o estado inicial do NFA
        Set<String> initialNfaStates = new HashSet<>();
        initialNfaStates.add(nfa.getInitial_state());
        String initialState = formatStateSet(initialNfaStates);
        dfaStates.add(initialState);
        dfa.setInitial_state(initialState);
        
        // 4. Fila para processar estados do DFA
        Queue<String> queue = new LinkedList<>();
        queue.offer(initialState);
        
        // 5. Processar cada estado do DFA
        while (!queue.isEmpty()) {
            String currentDfaState = queue.poll();
            Set<String> currentStates = parseStateSet(currentDfaState);
            
            // Para cada símbolo do alfabeto
            for (String symbol : nfa.getAlphabet()) {
                Set<String> nextStates = new HashSet<>();
                
                // Calcular transições para este símbolo
                for (String nfaState : currentStates) {
                    Set<String> transitions = getTransitions(nfa, nfaState, symbol);
                    nextStates.addAll(transitions);
                }
                
                if (!nextStates.isEmpty()) {
                    String nextDfaState = formatStateSet(nextStates);
                    
                    // Adicionar novo estado se não existir
                    if (!dfaStates.contains(nextDfaState)) {
                        dfaStates.add(nextDfaState);
                        queue.offer(nextDfaState);
                    }
                    
                    // Adicionar transição (DFA tem apenas um estado de destino)
                    Transicoes transition = new Transicoes();
                    transition.setInitial(currentDfaState);
                    transition.setSymbol(symbol);
                    transition.setEnd(new ArrayList<>(Arrays.asList(nextDfaState)));
                    dfaTransitions.add(transition);
                }
            }
        }
        
        // 6. Definir estados finais do DFA
        List<String> finalStates = new ArrayList<>();
        for (String dfaState : dfaStates) {
            Set<String> states = parseStateSet(dfaState);
            // Se o conjunto contém algum estado final do NFA, é final no DFA
            for (String nfaFinalState : nfa.getEnd_state()) {
                if (states.contains(nfaFinalState)) {
                    finalStates.add(dfaState);
                    break;
                }
            }
        }
        
        // 7. Configurar o DFA
        dfa.setStates(dfaStates);
        dfa.setTransiction(dfaTransitions);
        dfa.setEnd_state(finalStates);
        
        return dfa;
    }
    

    public static Dfa converterESalvarNfaDfa(Nfa nfa, String caminhoArquivo) throws Exception {
  
        // Converter NFA para DFA
        Dfa dfa = converterNfaDfa(nfa);
        
        // Salvar DFA em arquivo JSON
        UtilJackson.escreverJsonDfa(dfa, caminhoArquivo);
        
        return dfa;
    }
    

    public static Dfa converterArquivoNfaParaDfa(String caminhoNfa, String caminhoDfa) throws Exception {
        System.out.println("Carregando NFA de: " + caminhoNfa);
        
        // Carregar NFA do arquivo JSON
        Nfa nfa = UtilJackson.lerJsonNfa(caminhoNfa);
        
        // Converter e salvar DFA
        return converterESalvarNfaDfa(nfa, caminhoDfa);
    }
    
    /**
     * Obtém os estados de destino para uma transição específica
     */
    private static Set<String> getTransitions(Nfa nfa, String fromState, String symbol) {
        Set<String> destinations = new HashSet<>();
        
        for (Transicoes transition : nfa.getTransiction()) {
            if (fromState.equals(transition.getInitial()) && 
                symbol.equals(transition.getSymbol()) && 
                transition.getEnd() != null) {
                destinations.addAll(transition.getEnd());
            }
        }
        
        return destinations;
    }
    
    /**
     * Converte string de estado do DFA para conjunto de estados
     */
    private static Set<String> parseStateSet(String stateString) {
        // Busca o conjunto de estados no mapeamento
        for (Map.Entry<Set<String>, String> entry : stateMapping.entrySet()) {
            if (entry.getValue().equals(stateString)) {
                return new HashSet<>(entry.getKey());
            }
        }
        return new HashSet<>();
    }
    
    /**
     * Converte conjunto de estados para string de estado do DFA
     */
    private static String formatStateSet(Set<String> states) {
        if (stateMapping.containsKey(states)) {
            return stateMapping.get(states);
        }
        
        String newState = "S" + stateCounter++;
        stateMapping.put(new HashSet<>(states), newState);
        return newState;
    }
}

