# DescHub - Documentação do Projeto até o momento

## 1. O que é o DescHub?

O DescHub é um sistema interno pensado para registrar e controlar
abatimentos, descontos e ações comerciais negociadas pelos vendedores.

O problema que o sistema pretende resolver é simples:

-   vendedores negociam ações e descontos com clientes;
-   essas negociações geram valores que poderão ser abatidos
    posteriormente;

-   sem um controle centralizado, a empresa pode descobrir o abatimento
    somente quando o cliente já descontou o valor de um título;
-   o sistema deve permitir registrar esses valores antes que isso
    aconteça e acompanhar o status de cada abatimento.

A primeira versão possui duas ideias principais:

1.  Cadastro de um novo abatimento.
2.  Resumo dos valores registrados.

------------------------------------------------------------------------

# 2. Tecnologias e etapas do projeto

O projeto foi pensado para evoluir até uma aplicação web:

``` text
Angular + TypeScript
        ↓
      HTTP
        ↓
Spring Boot + Java
        ↓
       JPA
        ↓
      MySQL
```

Neste momento estamos trabalhando somente com Java puro.

O objetivo é aprender a lógica e a orientação a objetos antes de
introduzir Spring Boot, banco de dados e Angular.

------------------------------------------------------------------------

# 3. Estrutura atual

Neste momento, as classes podem ficar diretamente dentro de `src`:

``` text
src/
├── Main.java
├── Vendedor.java
├── Cliente.java
├── Industria.java
├── Abatimento.java
├── StatusAbatimento.java
└── Resumo.java
```

Essa organização é proposital.

Mais tarde, quando o projeto virar uma aplicação Spring Boot, a
organização será separada em camadas como:

``` text
controller/
service/
repository/
model/
```

------------------------------------------------------------------------

# 4. Classe Vendedor

A classe `Vendedor` representa um vendedor da empresa.

``` java
public class Vendedor {
    private int id;
    private String nome;

    public Vendedor(int id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public int getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
}
```

## Papel da classe

Representar um vendedor dentro do sistema.

Um objeto `Vendedor` possui:

-   `id`
-   `nome`

Exemplo:

``` java
Vendedor vendedor = new Vendedor(1, "Gabriel");
```

------------------------------------------------------------------------

## 4.1 Atributos

``` java
private int id;
private String nome;
```

Os atributos representam os dados que pertencem ao vendedor.

### `id`

Identifica o vendedor.

``` java
private int id;
```

### `nome`

Armazena o nome do vendedor.

``` java
private String nome;
```

Os atributos são `private` para aplicar encapsulamento.

------------------------------------------------------------------------

## 4.2 Construtor

``` java
public Vendedor(int id, String nome){
    this.id = id;
    this.nome = nome;
}
```

O construtor é usado quando criamos um objeto.

Exemplo:

``` java
Vendedor vendedor = new Vendedor(1, "Gabriel");
```

O `this` representa o objeto atual.

Então:

``` java
this.id = id;
```

significa:

``` text
atributo id do objeto = valor recebido pelo parâmetro id
```

------------------------------------------------------------------------

## 4.3 Getters

``` java
public int getId(){
    return id;
}

public String getNome(){
    return nome;
}
```

Os getters permitem acessar informações que estão protegidas por
`private`.

Exemplo:

``` java
System.out.println(vendedor.getNome());
```

------------------------------------------------------------------------

## 4.4 Setter

``` java
public void setNome(String nome){
    this.nome = nome;
}
```

O setter permite alterar o nome.

Não criamos `setId()` neste momento porque o ID não precisa ser alterado
depois que o objeto foi criado.

------------------------------------------------------------------------

# 5. Classe Cliente

A classe `Cliente` representa um cliente da empresa.

``` java
public class Cliente {
    private int id;
    private String nome;

    public Cliente(int id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public int getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
}
```

Ela segue praticamente a mesma estrutura de `Vendedor`.

Possui:

-   `id`
-   `nome`
-   construtor
-   `getId()`
-   `getNome()`
-   `setNome()`

Exemplo:

``` java
Cliente cliente = new Cliente(1, "Mercadinho Central");
```

------------------------------------------------------------------------

# 6. Classe Industria

A classe `Industria` representa a indústria relacionada ao abatimento.

``` java
public class Industria {
    private int id;
    private String nome;

    public Industria(int id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public int getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
}
```

Também possui:

-   `id`
-   `nome`
-   construtor
-   getters
-   setter para o nome

Exemplo:

``` java
Industria industria = new Industria(1, "Ambev");
```

------------------------------------------------------------------------

# 7. Enum StatusAbatimento

Criamos um `enum` para representar os possíveis estados de um
abatimento.

``` java
public enum StatusAbatimento {
    PENDENTE,
    FINALIZADO
}
```

## Por que usar enum?

Porque o status não deve ser qualquer texto.

Seria ruim permitir:

``` java
status = "pendente";
status = "Pendente";
status = "PENDENTE";
status = "esperando";
```

Com `enum`, os valores possíveis são definidos previamente.

Neste momento temos somente:

``` text
PENDENTE
FINALIZADO
```

------------------------------------------------------------------------

## 7.1 Status inicial

Um abatimento novo deve começar como:

``` text
PENDENTE
```

O vendedor não deve escolher livremente se o abatimento está finalizado.

A ideia é evitar que alguém registre um abatimento já como finalizado
sem que a dedução tenha realmente acontecido.

------------------------------------------------------------------------

# 8. Classe Abatimento

Esta é a classe principal.

Ela representa um abatimento comercial.

``` java
import java.time.LocalDate;

public class Abatimento {
    private int id;
    private Vendedor vendedor;
    private Cliente cliente;
    private Industria industria;
    private LocalDate data;
    private String descricao;
    private double valor;
    private StatusAbatimento status;

    public Abatimento(
        int id,
        Vendedor vendedor,
        Cliente cliente,
        Industria industria,
        LocalDate data,
        String descricao,
        double valor,
        StatusAbatimento status
    ){
        this.id = id;
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.industria = industria;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.status = status;
    }

    public int getId(){
        return id;
    }

    public Vendedor getVendedor(){
        return vendedor;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public Industria getIndustria(){
        return industria;
    }

    public LocalDate getData(){
        return data;
    }

    public String getDescricao(){
        return descricao;
    }

    public double getValor(){
        return valor;
    }

    public StatusAbatimento getStatus(){
        return status;
    }

    public void setData(LocalDate data){
        this.data = data;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public void setValor(double valor){
        this.valor = valor;
    }

    public void setStatus(StatusAbatimento status){
        this.status = status;
    }

    public void finalizar(){
        this.status = StatusAbatimento.FINALIZADO;
    }

    public boolean estaPendente(){
        return status == StatusAbatimento.PENDENTE;
    }

    public boolean estaFinalizado(){
        return status == StatusAbatimento.FINALIZADO;
    }
}
```

------------------------------------------------------------------------

# 9. Os atributos de Abatimento

## ID

``` java
private int id;
```

Identifica o abatimento.

------------------------------------------------------------------------

## Vendedor

``` java
private Vendedor vendedor;
```

Aqui acontece algo importante.

O atributo não é:

``` java
private String vendedor;
```

Ele é um objeto:

``` java
private Vendedor vendedor;
```

Isso significa que um `Abatimento` possui uma relação com um objeto
`Vendedor`.

Exemplo:

``` java
Vendedor vendedor = new Vendedor(1, "Gabriel");
```

Depois:

``` java
Abatimento abatimento = new Abatimento(
    ...,
    vendedor,
    ...
);
```

O abatimento passa a saber qual vendedor criou ou está relacionado à
ação.

------------------------------------------------------------------------

## Cliente

``` java
private Cliente cliente;
```

Também é um objeto.

O abatimento possui um cliente relacionado.

------------------------------------------------------------------------

## Industria

``` java
private Industria industria;
```

O abatimento também possui uma indústria relacionada.

------------------------------------------------------------------------

## Data

``` java
private LocalDate data;
```

Usamos `LocalDate` para representar uma data sem horário.

Exemplo:

``` java
LocalDate.of(2026, 10, 5)
```

Representa:

``` text
05/10/2026
```

------------------------------------------------------------------------

## Descrição

``` java
private String descricao;
```

Guarda uma descrição da ação.

Exemplo:

``` text
"Ação Inauguração"
```

------------------------------------------------------------------------

## Valor

``` java
private double valor;
```

Representa o valor financeiro do abatimento.

Exemplo:

``` text
15000
```

------------------------------------------------------------------------

## Status

``` java
private StatusAbatimento status;
```

O status utiliza o `enum` criado anteriormente.

Exemplo:

``` java
StatusAbatimento.PENDENTE
```

------------------------------------------------------------------------

# 10. Métodos de Abatimento

## Getters

Os getters permitem consultar os dados:

``` java
getId()
getVendedor()
getCliente()
getIndustria()
getData()
getDescricao()
getValor()
getStatus()
```

Exemplo:

``` java
abatimento.getValor();
```

------------------------------------------------------------------------

## Setters

Os setters permitem alterar alguns dados:

``` java
setData()
setDescricao()
setValor()
setStatus()
```

Não criamos setter para o ID.

------------------------------------------------------------------------

# 11. Método finalizar()

Criamos um método específico para finalizar o abatimento:

``` java
public void finalizar(){
    this.status = StatusAbatimento.FINALIZADO;
}
```

Assim, em vez de fazer:

``` java
abatimento.setStatus(StatusAbatimento.FINALIZADO);
```

podemos fazer:

``` java
abatimento.finalizar();
```

Isso deixa o código mais próximo da regra de negócio.

A ideia é:

> "Finalize este abatimento."

------------------------------------------------------------------------

# 12. Métodos estaPendente() e estaFinalizado()

Criamos:

``` java
public boolean estaPendente(){
    return status == StatusAbatimento.PENDENTE;
}
```

E:

``` java
public boolean estaFinalizado(){
    return status == StatusAbatimento.FINALIZADO;
}
```

Esses métodos retornam `true` ou `false`.

Exemplo:

``` java
if (abatimento.estaPendente()) {
    System.out.println("Abatimento pendente");
}
```

Isso é melhor do que espalhar comparações do enum pelo código:

``` java
if (abatimento.getStatus() == StatusAbatimento.PENDENTE)
```

A comparação ainda existe internamente, mas a regra fica encapsulada na
própria classe.

------------------------------------------------------------------------

# 13. ArrayList de Abatimento

No `Main`, criamos:

``` java
ArrayList<Abatimento> abatimentos = new ArrayList<>();
```

Isso representa uma lista de abatimentos.

Podemos adicionar objetos:

``` java
abatimentos.add(abat1);
abatimentos.add(abat2);
```

Consultar um objeto pelo índice:

``` java
abatimentos.get(0);
```

Saber quantos existem:

``` java
abatimentos.size();
```

Percorrer todos:

``` java
for (Abatimento abatimento : abatimentos) {
    // código
}
```

------------------------------------------------------------------------

# 14. A classe Resumo

Criamos a classe `Resumo` para retirar do `Main` a responsabilidade de
fazer cálculos.

``` java
import java.util.ArrayList;

public class Resumo {
    private ArrayList<Abatimento> abatimentos;

    public Resumo(ArrayList<Abatimento> abatimentos) {
        this.abatimentos = abatimentos;
    }

    public double calcularTotal() {

        double total = 0;

        for (Abatimento abatimento : abatimentos) {
            total += abatimento.getValor();
        }

        return total;
    }
}
```

------------------------------------------------------------------------

# 15. Por que Resumo recebe um ArrayList?

O construtor recebe:

``` java
ArrayList<Abatimento> abatimentos
```

Assim podemos criar:

``` java
Resumo resumo = new Resumo(abatimentos);
```

O objeto `Resumo` passa a ter acesso à lista.

O atributo:

``` java
private ArrayList<Abatimento> abatimentos;
```

guarda essa referência.

E:

``` java
this.abatimentos = abatimentos;
```

faz a associação entre o parâmetro recebido e o atributo da classe.

------------------------------------------------------------------------

# 16. Método calcularTotal()

Criamos:

``` java
public double calcularTotal() {

    double total = 0;

    for (Abatimento abatimento : abatimentos) {
        total += abatimento.getValor();
    }

    return total;
}
```

A lógica é:

``` text
total = 0

pega abatimento 1
    ↓
soma o valor

pega abatimento 2
    ↓
soma o valor

terminou a lista
    ↓
retorna o total
```

O `return` fica depois do `for`.

Se ele estivesse dentro do `for`, o método retornaria na primeira
repetição.

------------------------------------------------------------------------

# 17. Main

O `Main` é usado atualmente para testar nosso sistema.

Exemplo atual:

``` java
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Vendedor vendedor = new Vendedor(1, "Gabriel");
        Vendedor vendedor2 = new Vendedor(2, "Andre");

        Cliente cliente = new Cliente(1, "Mercadinho Central");
        Cliente cliente2 = new Cliente(2, "Mercadinho Nordeste");

        Industria industria = new Industria(1, "Ambev");
        Industria industria2 = new Industria(2, "Ambev");

        Abatimento abat1 = new Abatimento(
            1,
            vendedor,
            cliente,
            industria,
            LocalDate.of(2026, 10, 5),
            "Ação Inauguração",
            15000,
            StatusAbatimento.PENDENTE
        );

        Abatimento abat2 = new Abatimento(
            2,
            vendedor2,
            cliente2,
            industria2,
            LocalDate.of(2026, 12, 15),
            "Rebaixa",
            1300,
            StatusAbatimento.FINALIZADO
        );

        ArrayList<Abatimento> abatimentos = new ArrayList<Abatimento>();

        abatimentos.add(abat1);
        abatimentos.add(abat2);

        Resumo resumo = new Resumo(abatimentos);

        System.out.printf(
            "Valor total: R$ %.2f",
            resumo.calcularTotal()
        );
    }
}
```

------------------------------------------------------------------------

# 18. O papel do Main

O `Main` está funcionando como nosso ambiente de teste.

Ele:

1.  cria vendedores;
2.  cria clientes;
3.  cria indústrias;
4.  cria abatimentos;
5.  coloca os abatimentos em uma lista;
6.  cria o objeto `Resumo`;
7.  pede para o `Resumo` calcular o total;
8.  imprime o resultado.

O `Main` não deveria concentrar todas as regras do sistema.

Por isso estamos começando a transferir responsabilidades para as
classes.

------------------------------------------------------------------------

# 19. Antes e depois

## Antes

O `Main` fazia o cálculo:

``` java
double totalPendente = 0;

for (Abatimento abatimento : abatimentos) {
    if (abatimento.estaPendente()) {
        totalPendente += abatimento.getValor();
    }
}
```

Isso fazia o `Main` cuidar tanto da criação dos dados quanto das regras
de cálculo.

## Depois

A ideia é:

``` java
Resumo resumo = new Resumo(abatimentos);

double total = resumo.calcularTotal();
```

O `Main` apenas solicita o resultado.

Quem sabe como calcular é a classe `Resumo`.

------------------------------------------------------------------------

# 20. Separação de responsabilidades

Essa é uma das ideias mais importantes que estamos aprendendo.

Cada classe possui uma responsabilidade.

``` text
Vendedor
└── representa um vendedor

Cliente
└── representa um cliente

Industria
└── representa uma indústria

Abatimento
└── representa um abatimento

StatusAbatimento
└── define os status possíveis

Resumo
└── realiza cálculos sobre os abatimentos

Main
└── cria objetos e testa o sistema
```

Não estamos tentando colocar tudo dentro de uma única classe.

------------------------------------------------------------------------

# 21. Relação entre os objetos

Um abatimento utiliza outros objetos:

``` text
Abatimento
│
├── Vendedor
├── Cliente
├── Industria
├── LocalDate
├── String
├── double
└── StatusAbatimento
```

Por exemplo:

``` java
abatimento.getVendedor().getNome();
```

Primeiro:

``` java
abatimento.getVendedor()
```

retorna um objeto `Vendedor`.

Depois:

``` java
.getNome()
```

pega o nome desse vendedor.

Isso mostra como os objetos podem se relacionar.

------------------------------------------------------------------------

# 22. Estado atual do projeto

Até agora conseguimos construir:

-   classes básicas do domínio;
-   atributos privados;
-   construtores;
-   getters;
-   setters;
-   uso de `this`;
-   relacionamento entre objetos;
-   `enum`;
-   `LocalDate`;
-   `ArrayList`;
-   `for-each`;
-   métodos que retornam valores;
-   métodos booleanos;
-   encapsulamento;
-   regras simples de negócio;
-   separação de responsabilidades.

Também criamos a primeira camada de lógica de resumo.

------------------------------------------------------------------------

# 23. Próximos passos

A evolução planejada é:

## Etapa atual

Continuar o Java puro.

Próximos métodos da classe `Resumo`:

``` java
calcularTotalPendente()
calcularTotalFinalizado()
```

Depois:

``` java
calcularTotalPorIndustria()
calcularTotalPorVendedor()
```

Sem complicar a primeira versão.

------------------------------------------------------------------------

## Depois

Quando a lógica estiver funcionando:

``` text
Java puro
    ↓
Spring Boot
    ↓
API REST
    ↓
MySQL
    ↓
Angular + TypeScript
```

O mesmo projeto será transformado em uma aplicação web.

------------------------------------------------------------------------

# 24. Conceito principal aprendido até aqui

O objetivo não é apenas fazer o código funcionar.

Estamos aprendendo a perguntar:

> "Quem deve ser responsável por fazer isso?"

Por exemplo:

``` java
resumo.calcularTotal();
```

Em vez de colocar todo o cálculo dentro do `Main`.

Essa forma de pensar será muito importante no Spring Boot,
principalmente quando começarmos a trabalhar com:

-   Controllers;
-   Services;
-   Repositories;
-   Entities;
-   DTOs;
-   banco de dados;
-   APIs.

O DescHub está sendo usado para aprender esses conceitos na prática,
começando do Java básico e evoluindo gradualmente.
#