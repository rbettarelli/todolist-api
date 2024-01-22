# Projeto de API Spring para Lista de Tarefas

Este projeto é uma API Spring que gerencia uma lista de tarefas, utilizando tecnologias como Amazon ECS2, GitHub Secrets e GitHub Actions. A aplicação é desenvolvida em Java e o banco de dados PostgreSQL é utilizado, sendo executado em um contêiner Docker em uma instância Amazon ECS2.

## Configuração do Banco de Dados

O banco de dados PostgreSQL está configurado em um contêiner Docker na instância ECS2. As configurações de conexão podem ser encontradas no arquivo `application.properties` da aplicação Spring.

## GitHub Secrets

O GitHub Secrets é utilizado para armazenar informações sensíveis, como credenciais do Docker Hub e variáveis de ambiente necessárias para a execução e implantação do projeto.

### Secrets Configurados

- `DOCKER_USERNAME`: Nome de usuário para autenticação no Docker Hub.
- `DOCKER_PASSWORD`: Senha para autenticação no Docker Hub.
- `IP_ECS2`: Endereço IP da instância ECS2.
- `USERNAME_POSTGRES`: Seu usuário do PostgreSQL no contêiner Docker.
- `PASSWORD_POSTGRES`: Sua senha do PostgreSQL no contêiner Docker.

## Execução do Projeto

O projeto é configurado para ser executado na porta 8080. Certifique-se de ajustar suas configurações de firewall, se necessário.

## Deploy Automático com GitHub Actions

O GitHub Actions é configurado para realizar o build do projeto, criar uma imagem Docker e fazer o deploy automaticamente ao detectar alterações na branch principal (main). As configurações do GitHub Actions podem ser encontradas no arquivo `.github/workflows/main.yml`.

---
**Nota:** Não esqueça de ajustar as configurações do `application.properties` antes de implantar o projeto.
