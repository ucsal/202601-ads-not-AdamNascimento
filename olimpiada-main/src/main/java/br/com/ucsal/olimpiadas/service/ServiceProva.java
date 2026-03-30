package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.Prova;
import br.com.ucsal.olimpiadas.Questao;
import br.com.ucsal.olimpiadas.repositorio.ProvaRepositorio;
import br.com.ucsal.olimpiadas.repositorio.QuestaoRepositorio;
import java.util.Scanner;

public class ServiceProva {

    private final ProvaRepositorio provaRepositorio;
    private final QuestaoRepositorio questaoRepositorio;
    private final Scanner in;

    public ServiceProva(ProvaRepositorio provaRepositorio, QuestaoRepositorio questaoRepositorio, Scanner in) {
        this.provaRepositorio = provaRepositorio;
        this.questaoRepositorio = questaoRepositorio;
        this.in = in;
    }

    public void cadastrar() {
        System.out.print("Título da prova: ");
        var titulo = in.nextLine();

        if (titulo == null || titulo.isBlank()) {
            System.out.println("título inválido");
            return;
        }

        var prova = new Prova();
        prova.setId(provaRepositorio.proximoId());
        prova.setTitulo(titulo);

        provaRepositorio.salvar(prova);
        System.out.println("Prova criada: " + prova.getId());
    }

    public void cadastrarQuestao() {
        if (provaRepositorio.isEmpty()) {
            System.out.println("não há provas cadastradas");
            return;
        }

        var provaId = escolher();
        if (provaId == null)
            return;

        System.out.println("Enunciado:");
        var enunciado = in.nextLine();

        var alternativas = new String[5];
        for (int i = 0; i < 5; i++) {
            char letra = (char) ('A' + i);
            System.out.print("Alternativa " + letra + ": ");
            alternativas[i] = letra + ") " + in.nextLine();
        }

        System.out.print("Alternativa correta (A–E): ");
        char correta;
        try {
            correta = br.com.ucsal.olimpiadas.Questao.normalizar(in.nextLine().trim().charAt(0));
        } catch (Exception e) {
            System.out.println("alternativa inválida");
            return;
        }

        var q = new Questao();
        q.setId(questaoRepositorio.proximoId());
        q.setProvaId(provaId);
        q.setEnunciado(enunciado);
        q.setAlternativas(alternativas);
        q.setAlternativaCorreta(correta);

        questaoRepositorio.salvar(q);
        System.out.println("Questão cadastrada: " + q.getId() + " (na prova " + provaId + ")");
    }

    public Long escolher() {
        System.out.println("\nProvas:");
        for (var p : provaRepositorio.listarTodos()) {
            System.out.printf("  %d) %s%n", p.getId(), p.getTitulo());
        }
        System.out.print("Escolha o id da prova: ");

        try {
            long id = Long.parseLong(in.nextLine());
            if (provaRepositorio.buscarPorId(id).isEmpty()) {
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