package br.com.ucsal.olimpiadas.repositorio;

import br.com.ucsal.olimpiadas.Tentativa;
import br.com.ucsal.olimpiadas.repositorio.IRepositorioConsulta;
import br.com.ucsal.olimpiadas.repositorio.IRepositorioContador;
import br.com.ucsal.olimpiadas.repositorio.IRepositorioEscrita;
import br.com.ucsal.olimpiadas.repositorio.IRepositorioLeitura;

public interface iTentativaRepositorio extends
        IRepositorioLeitura<Tentativa>,
        IRepositorioEscrita<Tentativa>,
        IRepositorioContador,
        IRepositorioConsulta { }