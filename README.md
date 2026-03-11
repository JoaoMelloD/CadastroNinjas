# ☕ Cadastro de Ninjas

## 1. Introdução e Motivação

Escreva um breve resumo sobre o que é o projeto. Siga o estilo do artigo: conte o **porquê** de você estar construindo isso. Foi para sair da zona de conforto? Foi para automatizar algo do seu dia a dia?

- **Objetivo principal:** Criar uma API REST para gerenciamento e cadastro de ninjas.
- **O que eu queria praticar:** Aprender a sintaxe do Java 21 e o funcionamento básico do Spring Boot.

---

## 2. Requisitos do Projeto

O que o seu código precisa fazer e quais as restrições técnicas dele.

### Funcionais (O que o sistema faz)

- O usuário pode criar, editar e deletar tarefas.
- O sistema deve converter um relatório de tarefas para PDF.

### Não-Funcionais (Como o sistema faz)

- **Linguagem:** Java 17+ (sem frameworks, ou usando Spring Boot).
- **Banco de Dados:** PostgreSQL ou H2 (para testes em memória).
- **Autenticação:** JWT simples.

---

## 3. Design e Arquitetura do Sistema

Como as peças se encaixam? Se você estiver seguindo uma arquitetura específica, explique aqui.

> **Exemplo:** "O projeto segue o padrão MVC (Model-View-Controller). As requisições chegam no Controller, a lógica de negócios fica na camada de Service, e o acesso ao banco é feito via Repository."

---

## 4. API Endpoints (Ou Funcionalidades Principais)

Se for uma API, liste as rotas. Se for um app de console, liste os comandos.

| Método | Rota | Descrição |
|--------|------|-----------|
| `POST` | `/api/tarefas` | Recebe os dados e cria uma nova tarefa. |
| `GET` | `/api/tarefas/{id}` | Retorna os detalhes de uma tarefa específica. |

---

## 5. Implementação e Ferramentas Usadas

Quais bibliotecas você usou e por quê?

- **Maven / Gradle:** Para gerenciamento de dependências.
- **Lombok:** Para reduzir o código repetitivo (getters, setters, construtores).
- **Estrutura de pastas:** Onde cada coisa está no seu projeto (ex: `src/main/java/com/seuprojeto/...`).

---

## 6. Fluxo de Desenvolvimento (Como rodar localmente)

Como você faz para rodar esse projeto na sua máquina? Se outra pessoa (ou você daqui a 6 meses) for rodar, quais comandos deve usar?

```bash
# Exemplo de comandos
mvn clean install
mvn spring-boot:run
```

> **Dica:** Escreva aqui também se você teve dificuldades para rodar algo (ex: configurar variáveis de ambiente) e como resolveu.

---

## 7. 📚 Conceitos Aprendidos (Sua seção especial)

Aqui é onde você transforma seu projeto em um material de estudo ativo. Liste e detalhe os conceitos de Java ou de arquitetura que você precisou aprender para fazer o projeto funcionar.

### Injeção de Dependência (Dependency Injection)

- **O que é:** Em vez de instanciar um objeto com `new` dentro de uma classe, nós passamos esse objeto pronto para ela.
- **Como apliquei:** Usei a anotação `@Autowired` do Spring para injetar meu `TarefaRepository` dentro do `TarefaService`. Isso facilitou muito os testes.

### Interfaces em Java

- **O que é:** Contratos que definem o que uma classe deve fazer, mas não como.
- **Como apliquei:** Criei uma interface `RelatorioGenerator` e implementei em uma classe específica de PDF.

### `Optional<T>`

- **O que é:** Um container do Java para evitar o famoso erro `NullPointerException`.
- **Como apliquei:** Ao buscar uma tarefa no banco de dados que pode não existir.

---

## 8. Conclusão e Próximos Passos

O que você achou de construir isso? O que planeja para o futuro (Parte 2)?

- **Status atual:** Consegui fazer o CRUD completo funcionar e conectar com o banco.
- **Próximos passos:** Escrever testes unitários usando JUnit e Mockito (ainda não sei como fazer, vou ter que pesquisar).

---

## Por que esse padrão é excelente para quem está aprendendo?

1. **Documenta o "Porquê":** Assim como o Lucas Faria explicou no artigo o motivo de estar saindo do Serverless para o Kubernetes, você explica suas decisões no Java.
2. **Ajuda o seu "Eu" do futuro:** A seção de Fluxo de Desenvolvimento garante que você não vai esquecer como compilar o próprio código.
3. **Consolida o conhecimento:** A seção Conceitos Aprendidos força você a explicar o que acabou de estudar. Como o próprio autor do artigo cita: *"Acho que ensinar é a melhor ferramenta de aprendizado para engenheiros de software."*