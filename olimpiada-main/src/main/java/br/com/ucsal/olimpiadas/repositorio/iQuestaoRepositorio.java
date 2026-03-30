package br.com.ucsal.olimpiadas.repositorio;

import br.com.ucsal.olimpiadas.Questao;
import java.util.List;
import java.util.Optional;

public interface iQuestaoRepositorio {
    long proximoId();
    void salvar(Questao q);
    List<Questao> listarTodos();
    Optional<Questao> buscarPorId(long id);
    boolean isEmpty();
}