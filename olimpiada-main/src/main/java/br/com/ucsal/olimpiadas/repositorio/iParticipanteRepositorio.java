package br.com.ucsal.olimpiadas.repositorio;

import br.com.ucsal.olimpiadas.Participante;
import java.util.List;
import java.util.Optional;

public interface iParticipanteRepositorio {

    long proximoId();
    void salvar(Participante p);
    List<Participante> listarTodos();
    Optional<Participante> buscarPorId(long id);
    boolean isEmpty();
}