package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.Questao;
import br.com.ucsal.olimpiadas.Resposta;
import br.com.ucsal.olimpiadas.Tentativa;
import br.com.ucsal.olimpiadas.repositorio.iParticipanteRepositorio;
import br.com.ucsal.olimpiadas.repositorio.iProvaRepositorio;
import br.com.ucsal.olimpiadas.repositorio.iQuestaoRepositorio;
import br.com.ucsal.olimpiadas.repositorio.iTentativaRepositorio;
import br.com.ucsal.olimpiadas.util.TabuleiroUtil;
import java.util.Scanner;

public class ServiceTentativa {

    private final iParticipanteRepositorio iParticipanteRepositorio;
    private final iProvaRepositorio iProvaRepositorio;
    private final iQuestaoRepositorio iQuestaoRepositorio;
    private final iTentativaRepositorio iTentativaRepositorio;
    private final ServiceParticipante serviceParticipante;
    private final ServiceProva serviceProva;
    private final Scanner in;

    public ServiceTentativa(
            iParticipanteRepositorio iParticipanteRepositorio,
            iProvaRepositorio iProvaRepositorio,
            iQuestaoRepositorio iQuestaoRepositorio,
            iTentativaRepositorio iTentativaRepositorio,
            ServiceParticipante serviceParticipante,
            ServiceProva serviceProva,
            Scanner in) {
        this.iParticipanteRepositorio = iParticipanteRepositorio;
        this.iProvaRepositorio = iProvaRepositorio;
        this.iQuestaoRepositorio = iQuestaoRepositorio;
        this.iTentativaRepositorio = iTentativaRepositorio;
        this.serviceParticipante = serviceParticipante;
        this.serviceProva = serviceProva;
        this.in = in;
    }

    public void aplicarProva() {
        if (iParticipanteRepositorio.isEmpty()) {
            System.out.println("cadastre participantes primeiro");
            return;
        }
        if (iProvaRepositorio.isEmpty()) {
            System.out.println("cadastre provas primeiro");
            return;
        }

        var participanteId = serviceParticipante.escolher();
        if (participanteId == null)
            return;

        var provaId = serviceProva.escolher();
        if (provaId == null)
            return;

        var questoesDaProva = iQuestaoRepositorio.listarTodos()
                .stream()
                .filter(q -> q.getProvaId() == provaId)
                .toList();

        if (questoesDaProva.isEmpty()) {
            System.out.println("esta prova não possui questões cadastradas");
            return;
        }

        var tentativa = new Tentativa();
        tentativa.setId(iTentativaRepositorio.proximoId());
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

        iTentativaRepositorio.salvar(tentativa);

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
        for (var t : iTentativaRepositorio.listarTodos()) {
            System.out.printf("#%d | participante=%d | prova=%d | nota=%d/%d%n",
                    t.getId(), t.getParticipanteId(),
                    t.getProvaId(), calcularNota(t), t.getRespostas().size());
        }
    }
}