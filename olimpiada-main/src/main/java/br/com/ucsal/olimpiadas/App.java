package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.repositorio.ParticipanteRepositorio;
import br.com.ucsal.olimpiadas.repositorio.ProvaRepositorio;
import br.com.ucsal.olimpiadas.repositorio.QuestaoRepositorio;
import br.com.ucsal.olimpiadas.repositorio.TentativaRepositorio;
import br.com.ucsal.olimpiadas.service.*;

import java.util.Scanner;

public class App {
	private static final Scanner in = new Scanner(System.in);
	static final ParticipanteRepositorio participanteRepositorio = new ParticipanteRepositorio();
	static final ProvaRepositorio provaRepositorio = new ProvaRepositorio();
	static final QuestaoRepositorio questaoRepositorio = new QuestaoRepositorio();
	static final TentativaRepositorio tentativaRepositorio = new TentativaRepositorio();
	static final ServiceParticipante serviceParticipante = new ServiceParticipante(participanteRepositorio, in);
	static final ServiceProva serviceProva = new ServiceProva(provaRepositorio, questaoRepositorio, in);
	static final iCalcularNota calculadora = new CalcularNotaAcertos ();
	static final ServiceTentativa serviceTentativa = new ServiceTentativa(participanteRepositorio, provaRepositorio, questaoRepositorio,
			tentativaRepositorio, serviceParticipante, serviceProva,calculadora, in);


	public static void main(String[] args) {
		seed();

		while (true) {
			System.out.println("\n=== OLIMPÍADA DE QUESTÕES (V1) ===");
			System.out.println("1) Cadastrar participante");
			System.out.println("2) Cadastrar prova");
			System.out.println("3) Cadastrar questão (A–E) em uma prova");
			System.out.println("4) Aplicar prova (selecionar participante + prova)");
			System.out.println("5) Listar tentativas (resumo)");
			System.out.println("0) Sair");
			System.out.print("> ");

			switch (in.nextLine()) {
				case "1" -> serviceParticipante.cadastrar();
				case "2" -> serviceProva.cadastrar();
				case "3" -> serviceProva.cadastrarQuestao();
				case "4" -> serviceTentativa.aplicarProva();
				case "5" -> serviceTentativa.listar();
				case "0" -> {
					System.out.println("tchau");
					return;
				}
				default -> System.out.println("opção inválida");
			}
		}
	}
	static void seed() {
		var prova = new Prova();
		prova.setId(provaRepositorio.proximoId()); // ← repositório gera o ID
		prova.setTitulo("Olimpíada 2026 • Nível 1 • Prova A");
		provaRepositorio.salvar(prova); // ← repositório guarda

		var q1 = new QuestaoXadrez();
		q1.setId(questaoRepositorio.proximoId()); // ← repositório gera o ID
		q1.setProvaId(prova.getId());

		q1.setEnunciado("""
				Questão 1 — Mate em 1.
				É a vez das brancas.
				Encontre o lance que dá mate imediatamente.
				""");

		q1.setFenInicial("6k1/5ppp/8/8/8/7Q/6PP/6K1 w - - 0 1");
		q1.setAlternativas(new String[] { "A) Qh7#", "B) Qf5#", "C) Qc8#", "D) Qh8#", "E) Qe6#" });
		q1.setAlternativaCorreta('C');

		questaoRepositorio.salvar(q1); // ← repositório guarda
	}
}