package br.com.ucsal.olimpiadas.repositorio;

import br.com.ucsal.olimpiadas.Prova;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProvaRepositorio implements iProvaRepositorio {

    private final List<Prova> provas = new ArrayList<>();
    private long proximoId = 1;

    public long proximoId() {
        return proximoId++;
    }

    public void salvar(Prova p) {
        provas.add(p);
    }

    public List<Prova> listarTodos() {
        return provas;
    }

    public Optional<Prova> buscarPorId(long id) {
        return provas.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    public boolean isEmpty() {
        return provas.isEmpty();
    }
}
