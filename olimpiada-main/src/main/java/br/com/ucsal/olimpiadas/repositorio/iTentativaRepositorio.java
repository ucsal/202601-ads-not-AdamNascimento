package br.com.ucsal.olimpiadas.repositorio;

import br.com.ucsal.olimpiadas.Tentativa;
import java.util.List;
import java.util.Optional;

public interface iTentativaRepositorio {
    long proximoId();
    void salvar(Tentativa t);
    List<Tentativa> listarTodos();
    Optional<Tentativa> buscarPorId(long id);
    boolean isEmpty();
}