# Agendamento API — Java 21 + Spring Boot + MySQL 5.7

API REST em camadas para CRUD de `cliente`, `funcionario`, `servico`, `horario_disponivel` e `agendamento`.

## Decisão importante sobre status

O SQL original possui colunas `*_status` em formato `ENUM`. Como o requisito pediu apagado lógico usando:

- `-1` = registro apagado logicamente;
- `0` = registro inativo;
- `1` = registro ativo;

este projeto usa `TINYINT` para as colunas `*_status`.

Para não perder os estados de negócio:

- `agendamento_status` original virou `agendamento_situacao` (`PENDENTE`, `CONFIRMADO`, `CANCELADO`, `CONCLUIDO`);
- `horario_disponivel_status` original virou `horario_disponivel_situacao` (`DISPONIVEL`, `OCUPADO`, `BLOQUEADO`).

## Estrutura

```text
src/main/java/br/com/senac/agendamentoapi
├── config
├── controller
├── dto
├── exception
├── mapper
├── model
├── repository
├── service
└── util
```

## Tecnologias

- Java 21
- Spring Boot 3.5.15
- Spring Web
- Spring Data JPA
- Bean Validation
- MySQL Connector/J 8.0.33
- Springdoc OpenAPI / Swagger UI
- Actuator

## Como rodar localmente com Docker

```bash
docker compose up -d
mvn spring-boot:run
```

Acesse:

```text
http://localhost:8080/swagger-ui.html
```

## Como conectar no banco real

Configure as variáveis de ambiente:

```bash
export DB_URL="jdbc:mysql://edumysql.acesso.rj.senac.br:3306/20261_prjint3_manha_ruimarsilvano?useSSL=false&serverTimezone=America/Sao_Paulo&characterEncoding=latin1"
export DB_USER="seu_usuario"
export DB_PASSWORD="sua_senha"
mvn spring-boot:run
```

No Windows PowerShell:

```powershell
$env:DB_URL="jdbc:mysql://edumysql.acesso.rj.senac.br:3306/20261_prjint3_manha_ruimarsilvano?useSSL=false&serverTimezone=America/Sao_Paulo&characterEncoding=latin1"
$env:DB_USER="seu_usuario"
$env:DB_PASSWORD="sua_senha"
mvn spring-boot:run
```

## Endpoints principais

| Recurso | Endpoint |
|---|---|
| Clientes | `/api/clientes` |
| Funcionários | `/api/funcionarios` |
| Serviços | `/api/servicos` |
| Horários disponíveis | `/api/horarios-disponiveis` |
| Agendamentos | `/api/agendamentos` |

Cada recurso possui:

```text
GET    /api/recurso
GET    /api/recurso/{id}
POST   /api/recurso
PUT    /api/recurso/{id}
DELETE /api/recurso/{id}
```

O `DELETE` não remove fisicamente o registro: apenas altera o campo `*_status` para `-1`.

## Scripts de banco

- `database/schema.sql`: cria o banco no modelo já ajustado para exclusão lógica.
- `database/migration-from-original.sql`: roteiro para adaptar o SQL original com `ENUM` para o modelo com status numérico.

## Exemplos de JSON

### Cliente

```json
{
  "nome": "Maria Silva",
  "telefone": "21999990000",
  "email": "maria@email.com",
  "dataCadastro": "2026-06-11",
  "status": 1
}
```

### Funcionário

```json
{
  "nome": "João Santos",
  "email": "joao@email.com",
  "senha": "123456",
  "telefone": "21988887777",
  "status": 1
}
```

### Serviço

```json
{
  "nome": "Corte de cabelo",
  "descricao": "Corte feminino",
  "valor": 90.00,
  "duracaoMinutos": 60,
  "funcionarioId": 1,
  "status": 1
}
```

### Horário disponível

```json
{
  "data": "2026-06-11",
  "inicio": "09:00:00",
  "fim": "10:00:00",
  "situacao": "DISPONIVEL",
  "funcionarioId": 1,
  "status": 1
}
```

### Agendamento

```json
{
  "data": "2026-06-11",
  "horarioInicio": "09:00:00",
  "horarioFim": "10:00:00",
  "observacoes": "Cliente pediu confirmação por WhatsApp",
  "situacao": "PENDENTE",
  "servicoId": 1,
  "horarioDisponivelId": 1,
  "clienteId": 1,
  "status": 1
}
```
