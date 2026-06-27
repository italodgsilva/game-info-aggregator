# Agregador de Informações de Jogos

Este projeto é um projeto pessoal para fins de aprendizado, que faz uso das seguintes tecnologias:

 - Kotlin
 - Quarkus
 - Maven
 - Compilação Nativa com GraalVM

Se quiser saber mais sobre o Quarkus, visite: <https://quarkus.io/>.

Trata-se de um agregador de informações de jogos, uma API que recebe o nome de um jogo e retorna informações vindas de 
múltiplas fontes.

> __PROJETO AINDA EM DESENVOLVIMENTO!!!__

## Executar a aplicação em modo desenvolvimento

É necessário ter o Maven instalado. Para executar, basta abrir no diretório raiz do projeto e executar o seguinte comando:

```shell script
./mvnw quarkus:dev
```

> **_OBSERVAÇÃO:_**  O Quarkus vem com uma UI de desenvolvimento, acessível somente pelo modo de desenvolvimento no 
> seguinte link:  <http://localhost:8080/q/dev/>.

## Empacotando e executando a aplicação

Para empacotar a aplicação, digite o seguinte:

```shell script
./mvnw package
```

Isso irá gerar um arquivo `quarkus-run.jar` no diretório `target/quarkus-app/`.
Esteja ciente de que não é um _über-jar_ Já que as dependências são colocadas no diretório `target/quarkus-app/lib/`.

A aplicação poderá ser então executada utilizando `java -jar target/quarkus-app/quarkus-run.jar`.

Se quiser construir um _über-jar_, execute o seguinte comando:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

A aplicação, compactada como um _über-jar_, é agora executável via `java -jar target/*-runner.jar`.

## Criando um executável nativo

Você pode criar um executável nativo. Existem duas formas de fazê-lo. Uma delas é através da GraalVM, na qual a
instalação da GraalVM no seu computador se faz necessária. A outra forma é através de um container Docker, na qual o
Docker deve estar instalado:

### Criando um executável pelo GraalVM

Digite o seguinte comando na pasta raiz do projeto:

```shell script
./mvnw package -Dnative
```

Agora é possível rodar o executável através do seguinte comando: `./target/hello-quarkus-1.0.0-SNAPSHOT-runner`

### Criando um executável pelo Docker

Digite o seguinte comando na pasta raiz do projeto:

```shell script
./mvnw package -Dnative "-Dquarkus.native.container-build=true"
```

Agora é possível rodar o executável através do seguinte comando: `./target/hello-quarkus-1.0.0-SNAPSHOT-runner`

> **_OBSERVAÇÃO:_**: Se você utiliza Windows e gerou um executável a partir do Docker, não poderá executá-lo diretamente
> pelo Powershell ou Prompt de Comando, devendo utilizar a WSL, Docker ou alguma distribuição Linux embutida no seu
> SO.

Se quiser saber mais sobre a criação de executáveis nativos, por favor, consulte <https://quarkus.io/guides/maven-tooling>.
