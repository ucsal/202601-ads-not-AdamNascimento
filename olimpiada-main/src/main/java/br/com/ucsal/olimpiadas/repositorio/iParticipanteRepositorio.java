package br.com.ucsal.olimpiadas.repositorio;

import br.com.ucsal.olimpiadas.Participante;

public interface iParticipanteRepositorio extends
        IRepositorioLeitura<Participante>,
        IRepositorioEscrita<Participante>,
        IRepositorioContador,
        IRepositorioConsulta {

}