package br.com.ucsal.olimpiadas.repositorio;

import br.com.ucsal.olimpiadas.Participante;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParticipanteRepositorio implements iParticipanteRepositorio {

    private final List<Participante> participantes = new ArrayList<>();
    private long proximoId = 1;

    public long proximoId(){
        return proximoId++;
    }

    public void salvar(Participante p){
        participantes.add(p);

    }

    public List<Participante> listarTodos(){
        return participantes;
    }

    public Optional<Participante> buscarPorId(long id){
        return participantes.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }
    public boolean isEmpty() {
        return participantes.isEmpty();
    }
}
