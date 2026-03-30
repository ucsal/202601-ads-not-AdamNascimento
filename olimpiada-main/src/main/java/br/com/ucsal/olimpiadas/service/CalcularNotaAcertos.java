package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.Tentativa;

public class CalcularNotaAcertos implements iCalcularNota {

    @Override
    public int calcular(Tentativa tentativa) {
        int acertos = 0;
        for (var r : tentativa.getRespostas()) {
            if (r.isCorreta())
                acertos++;
        }
        return acertos;
    }
}