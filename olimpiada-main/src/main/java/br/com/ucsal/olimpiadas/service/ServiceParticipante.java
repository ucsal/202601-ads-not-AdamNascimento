package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.Participante;
import br.com.ucsal.olimpiadas.repositorio.ParticipanteRepositorio;
import java.util.Scanner;

public class ServiceParticipante {

    private final ParticipanteRepositorio repositorio;
    private final Scanner in;

    public ServiceParticipante(ParticipanteRepositorio repositorio, Scanner in) {
        this.repositorio = repositorio;
        this.in = in;
    }

    public void cadastrar() {
        System.out.print("Nome: ");
        var nome = in.nextLine();

        System.out.print("Email (opcional): ");
        var email = in.nextLine();

        if (nome == null || nome.isBlank()) {
            System.out.println("nome inválido");
            return;
        }

        var p = new Participante();
        p.setId(repositorio.proximoId());
        p.setNome(nome);
        p.setEmail(email);
        repositorio.salvar(p);

        System.out.println("Participante cadastrado: " + p.getId());
    }

    public Long escolher() {
        System.out.println("\nParticipantes:");
        for (var p : repositorio.listarTodos()) {
            System.out.printf("  %d) %s%n", p.getId(), p.getNome());
        }
        System.out.print("Escolha o id do participante: ");

        try {
            long id = Long.parseLong(in.nextLine());
            if (repositorio.buscarPorId(id).isEmpty()) {
                System.out.println("id inválido");
                return null;
            }
            return id;
        } catch (Exception e) {
            System.out.println("entrada inválida");
            return null;
        }
    }
}