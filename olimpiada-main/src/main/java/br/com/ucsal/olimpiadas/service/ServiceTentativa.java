package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.Questao;
import br.com.ucsal.olimpiadas.Resposta;
import br.com.ucsal.olimpiadas.Tentativa;
import br.com.ucsal.olimpiadas.repositorio.ParticipanteRepositorio;
import br.com.ucsal.olimpiadas.repositorio.ProvaRepositorio;
import br.com.ucsal.olimpiadas.repositorio.QuestaoRepositorio;
import br.com.ucsal.olimpiadas.repositorio.TentativaRepositorio;
import br.com.ucsal.olimpiadas.util.TabuleiroUtil;
import java.util.Scanner;

public class ServiceTentativa {

    private final ParticipanteRepositorio participanteRepositorio;
    private final ProvaRepositorio provaRepositorio;
    private final QuestaoRepositorio questaoRepositorio;
    private final TentativaRepositorio tentativaRepositorio;
    private final ServiceParticipante serviceParticipante;
    private final ServiceProva serviceProva;
    private final Scanner in;

    public ServiceTentativa(
            ParticipanteRepositorio participanteRepositorio,
            ProvaRepositorio provaRepositorio,
            QuestaoRepositorio questaoRepositorio,
            TentativaRepositorio tentativaRepositorio,
            ServiceParticipante serviceParticipante,
            ServiceProva serviceProva,
            Scanner in) {
        this.participanteRepositorio = participanteRepositorio;
        this.provaRepositorio = provaRepositorio;
        this.questaoRepositorio = questaoRepositorio;
        this.tentativaRepositorio = tentativaRepositorio;
        this.serviceParticipante = serviceParticipante;
        this.serviceProva = serviceProva;
        this.in = in;
    }

    public void aplicarProva() {
        if (participanteRepositorio.isEmpty()) {
            System.out.println("cadastre participantes primeiro");
            return;
        }
        if (provaRepositorio.isEmpty()) {
            System.out.println("cadastre provas primeiro");
            return;
        }

        var participanteId = serviceParticipante.escolher();
        if (participanteId == null)
            return;

        var provaId = serviceProva.escolher();
        if (provaId == null)
            return;

        var questoesDaProva = questaoRepositorio.listarTodos()
                .stream()
                .filter(q -> q.getProvaId() == provaId)
                .toList();

        if (questoesDaProva.isEmpty()) {
            System.out.println("esta prova não possui questões cadastradas");
            return;
        }

        var tentativa = new Tentativa();
        tentativa.setId(tentativaRepositorio.proximoId());
        tentativa.setParticipanteId(participanteId);
        tentativa.setProvaId(provaId);

        System.out.println("\n--- Início da Prova ---");

        for (var q : questoesDaProva) {
            System.out.println("\nQuestão #" + q.getId());
            System.out.println(q.getEnunciado());

            System.out.println("Posição inicial:");
            TabuleiroUtil.imprimirTabuleiroFen(q.getFenInicial());

            for (var alt : q.getAlternativas()) {
                System.out.println(alt);
            }

            System.out.print("Sua resposta (A–E): ");
            char marcada;
            try {
                marcada = Questao.normalizar(in.nextLine().trim().charAt(0));
            } catch (Exception e) {
                System.out.println("resposta inválida (marcando como errada)");
                marcada = 'X';
            }

            var r = new Resposta();
            r.setQuestaoId(q.getId());
            r.setAlternativaMarcada(marcada);
            r.setCorreta(q.isRespostaCorreta(marcada));

            tentativa.getRespostas().add(r);
        }

        tentativaRepositorio.salvar(tentativa);

        int nota = calcularNota(tentativa);
        System.out.println("\n--- Fim da Prova ---");
        System.out.println("Nota (acertos): " + nota + " / " + tentativa.getRespostas().size());
    }

    public int calcularNota(Tentativa tentativa) {
        int acertos = 0;
        for (var r : tentativa.getRespostas()) {
            if (r.isCorreta())
                acertos++;
        }
        return acertos;
    }

    public void listar() {
        System.out.println("\n--- Tentativas ---");
        for (var t : tentativaRepositorio.listarTodos()) {
            System.out.printf("#%d | participante=%d | prova=%d | nota=%d/%d%n",
                    t.getId(), t.getParticipanteId(),
                    t.getProvaId(), calcularNota(t), t.getRespostas().size());
        }
    }
}