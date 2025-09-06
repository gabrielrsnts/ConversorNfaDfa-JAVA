package com.exemplo;

import java.util.Scanner;

import com.exemplo.conversor.ConversorNfaDfa;
import com.exemplo.model.Dfa;

public class App {
    public static void main(String[] args) throws Exception {
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== CONVERSOR NFA PARA DFA ===");
        System.out.println("Digite o nome do arquivo JSON do NFA (ex: exemplo01.json):");
        
        String arquivoNfa = scanner.nextLine().trim();
        

        if (arquivoNfa.isEmpty()) {
            System.out.println("Nenhum arquivo foi informado!!");
        }
        
        // Verificar se o arquivo existe
        if (!arquivoNfa.endsWith(".json")) {
            arquivoNfa += ".json";
        }
        
        String arquivoDfa = arquivoNfa.replace(".json", "_dfa.json");
        
        System.out.println("\nConvertendo " + arquivoNfa + " para " + arquivoDfa);
        
        try {
            Dfa dfa = ConversorNfaDfa.converterArquivoNfaParaDfa(arquivoNfa, arquivoDfa);
            
            System.out.println("\n CONVERSÃO CONCLUÍDA COM SUCESSO!");
            System.out.println(" Arquivo DFA gerado: " + arquivoDfa);

            
        } catch (Exception e) {
            System.err.println("ERRO na conversão: " + e.getMessage());
            System.err.println("Verifique se o arquivo " + arquivoNfa + " existe e está no formato correto.");
        }
        
        scanner.close();
    }
}


