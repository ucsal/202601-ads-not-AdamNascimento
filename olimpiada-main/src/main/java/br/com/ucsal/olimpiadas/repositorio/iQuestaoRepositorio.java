package br.com.ucsal.olimpiadas.repositorio;

import br.com.ucsal.olimpiadas.Questao;
import br.com.ucsal.olimpiadas.repositorio.IRepositorioLeitura;
import br.com.ucsal.olimpiadas.repositorio.IRepositorioEscrita;
import br.com.ucsal.olimpiadas.repositorio.IRepositorioContador;
import br.com.ucsal.olimpiadas.repositorio.IRepositorioConsulta;

public interface iQuestaoRepositorio extends
        IRepositorioLeitura<Questao>,
        IRepositorioEscrita<Questao>,
        IRepositorioContador,
        IRepositorioConsulta {
}