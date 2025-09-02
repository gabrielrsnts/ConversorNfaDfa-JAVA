package com.exemplo.util;

import com.exemplo.model.Nfa;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import com.exemplo.model.Dfa;

public class UtilJackson {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // Configurar o mapper para formatar o JSON de forma legível
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static Nfa lerJsonNfa(String caminhoDoArquivo) throws Exception {
        return objectMapper.readValue(new File(caminhoDoArquivo),Nfa.class);
    }

    public static void escreverJsonDfa(Dfa dfa, String caminhoDoArquivo) throws Exception {
        objectMapper.writeValue(new File(caminhoDoArquivo), dfa);
    }
}
