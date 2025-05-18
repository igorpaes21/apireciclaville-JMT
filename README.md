<h2 align="left">API ReciclaVille - JMT</h2>

###

<p align="left">A API ReciclaVille - JMT é uma aplicação backend desenvolvida para gerenciar e facilitar o processo de coleta seletiva de resíduos sólidos urbanos. Ela serve como base para sistemas que conectam cidadãos, cooperativas de reciclagem e órgãos públicos, promovendo a sustentabilidade e a logística eficiente de materiais recicláveis.</p>

###

<h2 align="left">Tecnologias Utilizadas</h2>

###

<div align="left">
  <img src="https://skillicons.dev/icons?i=java" height="40" alt="java logo"  />
  <img width="12" />
  <img src="https://skillicons.dev/icons?i=spring" height="40" alt="spring logo"  />
  <img width="12" />
  <img src="https://skillicons.dev/icons?i=maven" height="40" alt="apachemaven logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original.svg" height="40" alt="postgresql logo"  />
</div>

###

<p align="left">Java: Linguagem principal do projeto.<br><br>Spring Boot: Framework para criação de aplicações Java.<br><br>Maven: Gerenciador de dependências e build.<br><br>PostgreSQL: Banco de dados relacional.</p>

###

<h2 align="left">Estrutura do Projeto</h2>

###

<p align="left">ReciclaVille/<br>├── .mvn/<br>├── src/<br>│   └── main/<br>│       └── java/<br>│           └── com/<br>│               └── reciclaville/<br>│                   └── configurations/<br>│                       ├── controller/<br>│                       ├── model/<br>│                       └── service/<br>├── .gitignore<br>├── mvnw<br>├── mvnw.cmd<br>└── pom.xml</p>

###

<p align="left">controller/: Contém os controladores REST que definem os endpoints da API.<br><br>model/: Define as classes de modelo que representam os dados.<br><br>service/: Implementa a lógica de negócios e integrações com APIs externas.</p>

###

<h2 align="left">Como Executar</h2>

###

<p align="left">1. Pré-requisitos:<br><br>Java 11 ou superior instalado.<br><br>Maven instalado.<br><br>2.  Clonar o repositório:<br>git clone https://github.com/igorpaes21/apireciclaville-JMT<br><br>3. Compilar o projeto:<br>./mvnw clean install<br><br>4. Executar a aplicação:<br>./mvnw spring-boot:run<br><br>A aplicação estará disponível em http://localhost:8080.</p>

###

<h2 align="left">Endpoints Disponíveis</h2>

###

<p align="left">Endpoint: /clientes<br><br>GET /clientes/{id}: Retorna informações detalhadas sobre o cliente especificado pelo nome.<br><br>GET /clientes: Lista todos os clientes disponíveis na base de dados.<br><br>POST /clientes: Cria o cliente com todas as informações no banco de dados.<br><br>PUT /clientes/(id:): Atualiza as informações do cliente no banco de dados via id.<br><br>DELETE /clientes/(id): Deleta o cliente do banco de dados.</p>

###

<p align="left">Endpoint: /materiais<br><br>GET /materiais/{id}: Retorna informações detalhadas sobre o material especificado pelo nome.<br><br>GET /materiais: Lista todos os materiais disponíveis na base de dados.<br><br>POST /materiais: Cria o material com todas as informações no banco de dados.<br><br>PUT /materiais/(id:): Atualiza as informações do material no banco de dados via id.<br><br>DELETE /materiais/(id): Deleta o material do banco de dados.</p>

###

<p align="left">Endpoint: /declaracoes<br><br>GET /declaracoes/{id}: Retorna informações detalhadas sobre a declaração especificado pelo nome.<br><br>GET /declaracoes: Lista todos as declarações disponíveis na base de dados.<br><br>POST /declaracoes: Cria a declaração com todas as informações no banco de dados.<br><br>PUT /declaracoes/(id:): Atualiza as informações da declaração no banco de dados via id.<br><br>DELETE /materiais/(id): Deleta o material do banco de dados.</p>

###

<h2 align="left">Contribuição</h2>

###

<p align="left">Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.</p>

###

<h2 align="left">Licença</h2>

###

<p align="left">Este projeto está licenciado sob a MIT License.</p>

###
