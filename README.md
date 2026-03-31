# Sistema de Olimpíadas — Refatoração SOLID

## Descrição
Refatoração do sistema legado de olimpíadas de questões de xadrez,
aplicando os princípios SOLID sem alterar a lógica de negócio original.

## Estrutura de pacotes
```
src/main/java/br/com/ucsal/olimpiadas/
├── App.java
├── Participante.java
├── Prova.java
├── Questao.java
├── QuestaoXadrez.java
├── Resposta.java
├── Tentativa.java
├── repositorio/
│   ├── IRepositorioLeitura.java
│   ├── IRepositorioEscrita.java
│   ├── IRepositorioContador.java
│   ├── IRepositorioConsulta.java
│   ├── iParticipanteRepositorio.java
│   ├── iProvaRepositorio.java
│   ├── iQuestaoRepositorio.java
│   ├── iTentativaRepositorio.java
│   ├── ParticipanteRepositorio.java
│   ├── ProvaRepositorio.java
│   ├── QuestaoRepositorio.java
│   └── TentativaRepositorio.java
├── service/
│   ├── iCalcularNota.java
│   ├── CalculadoraPorAcertos.java
│   ├── ServiceParticipante.java
│   ├── ServiceProva.java
│   └── ServiceTentativa.java
└── util/
    └── TabuleiroUtil.java
```

## Onde cada princípio foi aplicado

### S — Single Responsibility Principle (SRP)
O `App.java` original concentrava todas as responsabilidades.
Foi refatorado separando em classes especializadas:
- `TabuleiroUtil` → responsável por renderizar tabuleiros FEN
- `*Repositorio` → responsável por armazenar e buscar cada entidade
- `Service*` → responsável pela lógica de negócio de cada entidade

### O — Open/Closed Principle (OCP)
O cálculo de nota estava fixo no `ServiceTentativa`. Foi criada a
interface `iCalcularNota` e a implementação `CalculadoraPorAcertos`.
Novos critérios de pontuação podem ser adicionados criando novas
implementações sem modificar o código existente.

### L — Liskov Substitution Principle (LSP)
Foi criada a classe `QuestaoXadrez` que estende `Questao` e sobrescreve
o método `exibirContexto()`. O `ServiceTentativa` chama `q.exibirContexto()`
sem saber o tipo concreto — qualquer subtipo de `Questao` pode ser usado
no lugar sem quebrar o comportamento.

### I — Interface Segregation Principle (ISP)
As interfaces de repositório foram segregadas em contratos menores:
- `IRepositorioLeitura` → listarTodos(), buscarPorId()
- `IRepositorioEscrita` → salvar()
- `IRepositorioContador` → proximoId()
- `IRepositorioConsulta` → isEmpty()
Cada interface específica estende apenas o que precisa.

### D — Dependency Inversion Principle (DIP)
Foram criadas interfaces para todos os repositórios. Os serviços
passaram a depender das abstrações e não das implementações concretas.
As dependências são injetadas via construtor.

## Restrições respeitadas
- Nenhuma lógica de negócio foi alterada
- Nenhuma funcionalidade foi removida
- Nenhum framework externo foi adicionado
