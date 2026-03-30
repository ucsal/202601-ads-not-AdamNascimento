package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.util.TabuleiroUtil;

public class QuestaoXadrez extends Questao {

    @Override
    public void exibirContexto() {
        if (getFenInicial() != null) {
            System.out.println("Posição inicial:");
            TabuleiroUtil.imprimirTabuleiroFen(getFenInicial());
        }
    }
}