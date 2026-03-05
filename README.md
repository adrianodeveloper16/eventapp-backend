# 📅 EventApp — Back-end Spring Boot

API REST desenvolvida como Atividade Final da disciplina de FullStack.

---

## 🛠️ Stack Utilizada

| Tecnologia | Versão |
|------------|--------|
| Java | 21 |
| Spring Boot | 4.0.3 |
| Spring Data JPA | — |
| PostgreSQL Driver | — |
| Maven | — |

---

## 🗄️ Banco de Dados

### Tabelas

| Tabela | Descrição |
|--------|-----------|
| `TB_CARGO` | Perfis de acesso (Organizador e Participante) |
| `TB_USUARIO` | Usuários cadastrados |
| `TB_EVENTO_CATEGORIA` | Categorias dos eventos |
| `TB_EVENTO` | Eventos criados pelos organizadores |
| `TB_EVENTO_INSCRICAO` | Inscrições dos participantes nos eventos |

### Categorias padrão
`ESPORTES` · `CULTURA` · `HISTÓRIA` · `TECNOLOGIA` · `EDUCAÇÃO` · `NEGÓCIOS` · `SAÚDE`

### Usuário padrão

| Campo | Valor |
|-------|-------|
| E-mail | admin@email.com |
| Senha | 123456 |
| Perfil | Organizador |

---

## 🔌 Endpoints da API

Base URL: `http://localhost:8080`

### Usuários `/api/usuarios`
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/cadastrar` | Cadastrar novo usuário |
| POST | `/login` | Autenticar usuário |
| GET | `/{id}` | Buscar usuário por ID |
| PUT | `/{id}` | Atualizar usuário |
| DELETE | `/{id}` | Inativar usuário |

### Eventos `/api/eventos`
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/` | Listar todos os eventos ativos |
| GET | `/{id}` | Buscar evento por ID |
| GET | `/organizador/{idUsuario}` | Listar eventos por organizador |
| GET | `/{id}/vagas` | Consultar vagas disponíveis |
| POST | `/` | Criar novo evento |
| PUT | `/{id}` | Atualizar evento |
| DELETE | `/{id}` | Inativar evento |

### Inscrições `/api/inscricoes`
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/usuario/{idUsuario}` | Listar inscrições do usuário |
| GET | `/evento/{idEvento}` | Listar inscritos no evento |
| POST | `/` | Realizar inscrição |
| DELETE | `/` | Cancelar inscrição |

### Categorias `/api/categorias`
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/` | Listar categorias ativas |

---

## 🗂️ Estrutura do Projeto

```
src/main/java/com/example/facens/
├── controller/
│   ├── UsuarioController.java
│   ├── EventoController.java
│   ├── EventoInscricaoController.java
│   └── EventoCategoriaController.java
├── model/
│   ├── Cargo.java
│   ├── Usuario.java
│   ├── Evento.java
│   ├── EventoCategoria.java
│   └── EventoInscricao.java
├── repository/
│   ├── CargoRepository.java
│   ├── UsuarioRepository.java
│   ├── EventoRepository.java
│   ├── EventoCategoriaRepository.java
│   └── EventoInscricaoRepository.java
└── service/
    ├── UsuarioService.java
    ├── EventoService.java
    ├── EventoInscricaoService.java
    └── EventoCategoriaService.java
```

---

## ⚙️ Como Executar

### Pré-requisitos
- Java 21
- Maven
- PostgreSQL

### 1. Configure o banco de dados

Crie o banco no PostgreSQL:
```sql
CREATE DATABASE eventapp;
```

Execute os scripts SQL para criar as tabelas e inserir os dados padrão.

### 2. Configure o `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/eventapp
spring.datasource.username=postgres
spring.datasource.password=sua_senha_aqui

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

server.port=8080
```

### 3. Execute o projeto

```bash
./mvnw spring-boot:run
```

A API ficará disponível em `http://localhost:8080`

---

## 🔗 Front-end

O front-end desta aplicação está disponível em:
[eventapp-frontend](https://github.com/adrianodeveloper16/eventapp-frontend)

---

## 👨‍💻 Autor

Desenvolvido por Adriano Batista da Silva como Atividade Final da disciplina ministrada pelo Prof. Fabrício Tonetto Londero.
