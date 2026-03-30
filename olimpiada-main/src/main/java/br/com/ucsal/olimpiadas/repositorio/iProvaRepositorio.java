package br.com.ucsal.olimpiadas.repositorio;

import br.com.ucsal.olimpiadas.Prova;
import java.util.List;
import java.util.Optional;

public interface iProvaRepositorio {
    long proximoId();
    void salvar(Prova p);
    List<Prova> listarTodos();
    Optional<Prova> buscarPorId(long id);
    boolean isEmpty();
}