<h1>Pessoas-API</h1>

<h2> Funcionalidades: :ok_hand:</h2>
<ul>
    <li>Criar uma pessoa :ballot_box_with_check:</li> 
    <li>Editar uma pessoa :ballot_box_with_check:</li>
    <li>Consultar uma pessoa :ballot_box_with_check:</li>
    <li>Listar pessoas :ballot_box_with_check:</li>
    <li>Criar endereço para pessoa :ballot_box_with_check:</li>
    <li>Listar endereços da pessoa :ballot_box_with_check:</li>
    <li>Poder informar qual endereço é o principal da pessoa :ballot_box_with_check:</li>
</ul>

<h2>Endpoints: 	:v:</h2>
<ul>
    <li>GET  /pessoas        -  Listar pessoas</li>
    <li>GET  /pessoas/{id}   -  Consultar uma pessoa e mostra seus endereços cadastrados</li>
    <li>POST /pessoas        -  Cria uma pessoa</li>
    <li>PUT  /pessoas/{id}   -  Edita uma pessoa</li>
    <li>POST /enderecos      -  Cria endereço para pessoa</li>
    <li>GET  /enderecos/{id} -  Informa qual é o endereço principal da pessoa cujo id foi informado</li>
</ul>

<h2>Cobertura de testes: :star_struck:</h2>

![](https://raw.githubusercontent.com/julianomachadoo/pessoas-api/main/img/cobertura-de-testes.png)

<h2>Como utilizar o programa? :thinking:</h2>
<h3>Opção 1: Acessar via AWS Elastik Beanstalk :relaxed:</h3>

```
http://pessoasapi-env.eba-jndu8qtf.us-east-1.elasticbeanstalk.com/

```

<h3>Opção 2: Clone do projeto e build na sua própria maquina :relieved:</h3>
<p>No terminal, clone o projeto:</p>

```
git clone https://github.com/julianomachadoo/pessoas-api.git
```

<p>Com o projeto baixado, execute o seguinte comando para criar o jar: </p>

```
mvn clean package
```

<p>E execute o comando abaixo para executar o projeto :warning: Atenção, o projeto utiliza a porta 8080</p>

```
java -jar ./target/pessoas-api.jar 
```


<h2>Fique à vontade para conhecer meus outros projetos :smile: 	:wave:</h2>
