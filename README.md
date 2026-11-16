# Top Car OS Service API 

microserviço/API de gestão de ordens de serviço, clientes e peças da oficina top-car-service, com foco em gestão de ordens de serviço.

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.5-brightgreen)
![Maven](https://img.shields.io/badge/Maven-3.8.5-blue)
![Docker](https://img.shields.io/badge/Docker-Enabled-blue)
![Kubernetes](https://img.shields.io/badge/Kubernetes-Enabled-blue)
![Terraform](https://img.shields.io/badge/Terraform-Enabled-purple)

## Como Executar localmente

### Pré-requisitos / Ferramentas

- **Java 21** ou superior
- **Git**
- **Docker** e **Docker Compose**
- **Kubernetes**


### 1.1. Preparação do Ambiente

* Clone e build do repositório:
```bash
git clone https://github.com/arthurjg/top-car-os-service.git

cd top-car-os-service
cd app
mvnw package
docker build -t top-car-os-service:1.4.0 .
```

### 1.2.1 Subir do Ambiente Via Docker/Docker-compose

* Execute o Docker Compose
```bash
cd ..
docker-compose up -d
```

### 1.3. Utilizar aplicação

### 1.3.1 Acessar Swagger

http://localhost:8082/swagger-ui/index.html#/

### 1.3.2 Autenticar

* aceesar o endpoint de autenticação - "autenticação controller - post"

* usuario: fgarage

* senha: 123456

* copiar o conteúdo do campo access_key

* utilizar no botão campo "authorize"

### 1.3.2 Utilizar funcionalidades

* criar: Cliente, Veículo, Servico, PeçasInsumos

* criar: Ordem Servico

* adicionar Servicos e peçasInsumos à Ordem de Servico e visualizar Orçamento

* aprovar Ordem de Serviço

## Notas da versão

### Nota versão 1.4.0 (Fase 4)

#### Objetivos/Entregáveis dessa Fase

- Refatoração/Separação de microserviços em: gestão OS, Billing e Execução OS;
- Refatoração/implementação de comunicação síncrona/assincrona e padrão SAGA;
- Implementação de testes unitários, BDD, cobertura de 80% e validação de qualidade com SonarQube;
- Pipelines/CI/CD com build, testes, qualidade com Sonar e deploy em Kubernetes  

## Detalhes/Entregáveis dessa Fase/Versão

### Documentação da API - Swagger

#### URL (Online)

http://localhost:8082/swagger-ui/index.html#/

#### Print

![arquitetura](/docs/api/top-car-os-service-swagger.png)

### Cobertura de Testes - SonarQube

#### URL

https://sonarcloud.io/summary/overall?id=arthurjg_top-car-os-service&branch=main

#### Print

![arquitetura](/docs/sonar/top-car-os-service-sonar.png)

### Diagrama da Arquitetura Microserviço

![arquitetura](/docs/arquitetura/top-car-os-service-os-arq.png)

### Diagrama da Arquitetura Geral

![arquitetura](/docs/arquitetura/diagrama-top-car-service-geral-aws.drawio.png)

### Detalhamento Estratégia SAGA

A estratégia SAGA escolhida foi: **Coreografada** pois facilita a independência entre os microserviços e contribuiu para manter os fluxos assíncronos.






