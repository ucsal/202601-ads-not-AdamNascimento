package br.com.ucsal.olimpiadas.repositorio;

import br.com.ucsal.olimpiadas.Tentativa;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TentativaRepositorio implements iTentativaRepositorio {

    private final List<Tentativa> tentativas = new ArrayList<>();
    private long proximoId = 1;

    public long proximoId() {
        return proximoId++;
    }

    public void salvar(Tentativa t) {
        tentativas.add(t);
    }

    public List<Tentativa> listarTodos() {
        return tentativas;
    }

    public Optional<Tentativa> buscarPorId(long id) {
        return tentativas.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    public boolean isEmpty() {
        return tentativas.isEmpty();
    }
}