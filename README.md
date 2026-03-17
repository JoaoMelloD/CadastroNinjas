# 🥷 Cadastro de Ninjas

API REST para cadastro e gerenciamento de ninjas e missões, desenvolvida com Java e Spring Boot como projeto de aprendizado.

---

## 1. Introdução e Motivação

O projeto surgiu como forma de praticar a construção de uma API REST do zero com Java e Spring Boot. O objetivo foi consolidar conceitos como mapeamento objeto-relacional, relacionamentos entre entidades, migrations e documentação de APIs.

- **Objetivo principal:** Construir uma API REST completa com operações de CRUD para ninjas e missões.
- **O que foi praticado:** Sintaxe do Java 17, Spring Boot, JPA/Hibernate, Flyway, DTOs, Mappers e documentação com OpenAPI (Swagger).

---

## 2. Requisitos do Projeto

### Funcionais

- Cadastrar, listar, atualizar e deletar ninjas.
- Cadastrar, listar, atualizar e deletar missões.
- Associar um ninja a uma missão.
- Cada ninja possui nome, e-mail (único), idade, rank e imagem de perfil.
- Cada missão possui nome e nível de dificuldade (`RANK_S`, `RANK_A`, `RANK_B`, `RANK_C`).

### Não-Funcionais

- **Linguagem:** Java 17
- **Framework:** Spring Boot 4.0.3
- **Banco de Dados:** H2 (em memória, com persistência configurável via variáveis de ambiente)
- **Migrations:** Flyway para versionamento do esquema do banco
- **Documentação:** Springdoc OpenAPI (Swagger UI)

---

## 3. Arquitetura do Sistema

O projeto segue o padrão **MVC em camadas**:

```
Controller  →  Service  →  Repository  →  Banco de Dados (H2)
     ↕              ↕
    DTO          Mapper
```

- **Controller:** Recebe as requisições HTTP e retorna as respostas.
- **Service:** Contém a lógica de negócio.
- **Repository:** Interface com o banco de dados via Spring Data JPA.
- **Model:** Entidade mapeada para a tabela no banco.
- **DTO:** Objeto de transferência de dados exposto na API.
- **Mapper:** Converte entre `Model` e `DTO`.

### Relacionamento entre entidades

```
NinjaModel (tb_cadastro)  N ──── 1  MissoesModel (tb_missao)
```

- Um ninja pertence a **uma** missão (`@ManyToOne`).
- Uma missão pode ter **vários** ninjas (`@OneToMany`).

---

## 4. API Endpoints

A documentação interativa completa está disponível via Swagger UI em:
**`http://localhost:8080/swagger-ui/index.html`**

### 🥷 Ninjas

| Método   | Rota            | Descrição                        | Status de Retorno      |
|----------|-----------------|----------------------------------|------------------------|
| `GET`    | `/ninjas`       | Lista todos os ninjas            | `200 OK`               |
| `GET`    | `/ninjas/{id}`  | Busca um ninja pelo ID           | `200 OK` / `404`       |
| `POST`   | `/ninjas`       | Cria um novo ninja               | `201 Created` / `400`  |
| `PUT`    | `/ninjas/{id}`  | Atualiza os dados de um ninja    | `200 OK` / `404`       |
| `DELETE` | `/ninjas/{id}`  | Remove um ninja                  | `200 OK` / `404`       |

**Exemplo de corpo para `POST /ninjas`:**
```json
{
  "name": "Naruto Uzumaki",
  "email": "naruto@konoha.com",
  "idade": 17,
  "rank": "Hokage",
  "imgUrl": "https://example.com/naruto.png",
  "missoes": null
}
```

### 📜 Missões

| Método   | Rota             | Descrição                         | Status de Retorno      |
|----------|------------------|-----------------------------------|------------------------|
| `GET`    | `/missoes`       | Lista todas as missões            | `200 OK`               |
| `GET`    | `/missoes/{id}`  | Busca uma missão pelo ID          | `200 OK` / `404`       |
| `POST`   | `/missoes`       | Cria uma nova missão              | `201 Created` / `400`  |
| `PUT`    | `/missoes/{id}`  | Atualiza os dados de uma missão   | `200 OK` / `404`       |
| `DELETE` | `/missoes/{id}`  | Remove uma missão                 | `200 OK` / `404`       |

**Exemplo de corpo para `POST /missoes`:**
```json
{
  "nome": "Recuperar o Pergaminho Proibido",
  "dificuldade": "RANK_A"
}
```

**Valores válidos para `dificuldade`:** `RANK_S`, `RANK_A`, `RANK_B`, `RANK_C`

---

## 5. Tecnologias e Ferramentas

| Ferramenta | Finalidade |
|---|---|
| **Spring Boot 4.0.3** | Framework principal da aplicação |
| **Spring Data JPA** | Abstração do banco de dados com Hibernate |
| **H2 Database** | Banco de dados em memória para desenvolvimento |
| **Flyway** | Versionamento e migrations do esquema do banco |
| **Lombok** | Redução de código repetitivo (getters, setters, construtores) |
| **Springdoc OpenAPI 2.5.0** | Geração automática da documentação Swagger UI |
| **Maven** | Gerenciamento de dependências e build |

### Migrations (Flyway)

| Versão | Arquivo | Descrição |
|--------|---------|-----------|
| V2 | `V2__Add_rank_tb_cadastro.sql` | Adiciona a coluna `rank` na tabela `tb_cadastro` |
| V3 | `V3__Add_imageURL_tb_cadastro.sql` | Adiciona a coluna `imgUrl` na tabela `tb_cadastro` |

---

## 6. Como Rodar Localmente

### Pré-requisitos

- Java 17+
- Maven 3.8+

### Variáveis de ambiente

Configure as seguintes variáveis antes de executar:

```bash
export DATABASE_URL=jdbc:h2:mem:testdb
export DATABASE_USERNAME=sa
export DATABASE_PASSWORD=
```

### Executando

```bash
# Clonar o repositório
git clone <url-do-repositorio>
cd CadastroDeNinjas

# Compilar e executar
./mvnw spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

### Painéis disponíveis

| Painel | URL |
|--------|-----|
| Swagger UI | `http://localhost:8080/swagger-ui/index.html` |
| H2 Console | `http://localhost:8080/h2-console` |

> No H2 Console, use `jdbc:h2:mem:testdb` como JDBC URL, usuário `sa` e senha em branco.

---

## 7. Estrutura do Projeto

```
src/main/java/dev/java10x/CadastroDeNinjas/
├── CadastroDeNinjasApplication.java   # Ponto de entrada da aplicação
├── DificuldadeMissao.java             # Enum com os ranks de missão
├── Config/
│   └── WebConfig.java                 # Configurações Web (CORS, etc.)
├── Ninjas/
│   ├── NinjaController.java           # Endpoints REST de ninjas
│   ├── NinjaService.java              # Regras de negócio
│   ├── NinjaRepository.java           # Acesso ao banco
│   ├── NinjaModel.java                # Entidade JPA (tb_cadastro)
│   ├── NinjaDTO.java                  # DTO de transferência
│   └── NinjaMapper.java               # Conversão Model ↔ DTO
└── Missoes/
    ├── MissoesController.java         # Endpoints REST de missões
    ├── MissoesService.java            # Regras de negócio
    ├── MissoesRepository.java         # Acesso ao banco
    ├── MissoesModel.java              # Entidade JPA (tb_missao)
    ├── MissoesDTO.java                # DTO de transferência
    └── MissoesMapper.java             # Conversão Model ↔ DTO
```

---

## 8. Conceitos Aprendidos

### Padrão DTO + Mapper
Em vez de expor a entidade do banco diretamente na API, usamos um DTO (Data Transfer Object) que contém apenas os campos necessários. O Mapper converte entre os dois objetos, desacoplando a camada de API do banco de dados.

### Relacionamentos JPA
- `@ManyToOne` no `NinjaModel`: cada ninja referencia uma missão via chave estrangeira `missoes_id`.
- `@OneToMany` no `MissoesModel`: uma missão pode ter vários ninjas. O `@JsonIgnore` evita recursão infinita na serialização.

### Migrations com Flyway
O Flyway executa arquivos `.sql` numerados em ordem (`V2__`, `V3__...`) toda vez que a aplicação sobe, garantindo que o esquema do banco esteja sempre na versão correta.

### Documentação com OpenAPI (Swagger)
Usando a dependência `springdoc-openapi-starter-webmvc-ui`, a API gera automaticamente uma interface interativa onde é possível visualizar e testar todos os endpoints sem precisar de ferramentas externas como o Postman.

---

## 9. Status e Próximos Passos

- ✅ CRUD completo de Ninjas
- ✅ CRUD completo de Missões
- ✅ Relacionamento Many-to-One entre Ninja e Missão
- ✅ Documentação com Swagger UI
- ✅ Migrations com Flyway