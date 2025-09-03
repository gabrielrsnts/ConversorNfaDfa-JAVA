package com.exemplo;

import com.exemplo.model.Nfa;
import com.exemplo.util.UtilJackson;


public class App {
    public static void main(String[] args) throws Exception {
        
        String caminhoDoArquivoNfa = "exemplo01.json";
        Nfa nfa = UtilJackson.lerJsonNfa(caminhoDoArquivoNfa);
        System.out.println(nfa.getAlphabet());
        System.out.println(nfa.getStates());
        System.out.println(nfa.getTransiction());
        System.out.println(nfa.getInitial_state());
        System.out.println(nfa.getEnd_state());


    }
}


