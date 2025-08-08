
# ConcessionariaManager - API Backend

Sistema backend para gerenciar concessionária de veículos, com controle de usuários, veículos, vendas, revisões, autopeças, equipamentos, entre outros.

Tecnologias usadas:
- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- Spring Security (JWT)
- MySQL
- Maven
- Swagger/OpenAPI

---

## Pré-requisitos

- Java JDK 17 ou superior instalado
- Maven instalado
- MySQL rodando e configurado
- IntelliJ IDEA (opcional, mas recomendado)
- Postman para testes de API

---

## Configuração do ambiente

1. Clone o repositório:

```bash
git clone <url-do-seu-repositorio>
cd ConcessionariaManager
```

2. Crie o banco MySQL para o projeto:

```sql
CREATE DATABASE concessionaria_manager CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

3. Configure o arquivo `src/main/resources/application.properties` (ou `application.yml`) com as credenciais do seu banco MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/concessionaria_manager?useSSL=false&serverTimezone=UTC
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

4. (Opcional) Configure o JWT e a segurança conforme sua necessidade (caso já esteja configurado).

---

## Build e execução

Para compilar e rodar a aplicação, no terminal:

```bash
mvn clean install
mvn spring-boot:run
```

A aplicação vai iniciar no endereço padrão `http://localhost:8080`.

---

## Documentação Swagger/OpenAPI

Após subir a aplicação, acesse:

```
http://localhost:8080/swagger-ui/index.html
```

Para acessar a documentação interativa de todos os endpoints.

---

## Testando a API com Postman

### 1. Autenticação (JWT)

- Faça o login com o endpoint `/api/auth/login` (se já implementado) para receber o token JWT.

### 2. Exemplo de requisição para listar veículos

- Método: GET  
- URL: `http://localhost:8080/api/veiculos`  
- Headers:  
  - Authorization: `Bearer <seu_token_jwt>`

### 3. Criar um novo vendedor

- Método: POST  
- URL: `http://localhost:8080/api/vendedores`  
- Headers:  
  - Authorization: `Bearer <token_de_gerente>`  
  - Content-Type: application/json  
- Body (raw JSON):

```json
{
  "usuarioId": "uuid-do-usuario",
  "nome": "João Silva",
  "ativo": true
}
```

### 4. Atualizar vendedor

- Método: PUT  
- URL: `http://localhost:8080/api/vendedores/{id}`  
- Headers:  
  - Authorization: `Bearer <token_de_gerente>`  
  - Content-Type: application/json  
- Body (raw JSON):

```json
{
  "usuarioId": "uuid-do-usuario",
  "nome": "João Silva Atualizado",
  "ativo": false
}
```

### 5. Deletar vendedor

- Método: DELETE  
- URL: `http://localhost:8080/api/vendedores/{id}`  
- Headers:  
  - Authorization: `Bearer <token_de_gerente>`

---

## Considerações finais

- Para mais detalhes, confira a documentação Swagger.
- Use o Postman para facilitar os testes.
- Caso queira alterar a porta, modifique `server.port` no `application.properties`.

---

## Contato

Para dúvidas, entre em contato pelo email: mauridf@gmail.com
