package br.com.ucsal.olimpiadas.repositorio;

public interface IRepositorioEscrita<T> {
    void salvar(T entidade);
}