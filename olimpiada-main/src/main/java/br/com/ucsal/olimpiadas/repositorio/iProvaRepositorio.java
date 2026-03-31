package br.com.ucsal.olimpiadas.repositorio;

import br.com.ucsal.olimpiadas.Prova;
import br.com.ucsal.olimpiadas.repositorio.IRepositorioLeitura;
import br.com.ucsal.olimpiadas.repositorio.IRepositorioEscrita;
import br.com.ucsal.olimpiadas.repositorio.IRepositorioContador;
import br.com.ucsal.olimpiadas.repositorio.IRepositorioConsulta;

public interface iProvaRepositorio extends IRepositorioLeitura<Prova>,
        IRepositorioEscrita<Prova>,
        IRepositorioContador,
        IRepositorioConsulta {

}