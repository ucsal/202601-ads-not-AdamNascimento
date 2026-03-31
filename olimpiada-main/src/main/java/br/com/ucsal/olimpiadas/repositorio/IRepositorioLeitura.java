package br.com.ucsal.olimpiadas.repositorio;

import java.util.List;
import java.util.Optional;

public interface IRepositorioLeitura<T> {
    List<T> listarTodos();
    Optional<T> buscarPorId(long id);
}