package br.com.ucsal.olimpiadas.repositorio;

import br.com.ucsal.olimpiadas.Questao;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuestaoRepositorio {

    private final List<Questao> questoes = new ArrayList<>();
    private long proximoId = 1;

    public long proximoId() {
        return proximoId++;
    }

    public void salvar(Questao q) {
        questoes.add(q);
    }

    public List<Questao> listarTodos() {
        return questoes;
    }

    public Optional<Questao> buscarPorId(long id) {
        return questoes.stream()
                .filter(q -> q.getId() == id)
                .findFirst();
    }

    public boolean isEmpty() {
        return questoes.isEmpty();
    }
}