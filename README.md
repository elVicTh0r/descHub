# DescHub

Sistema de controle de abatimentos comerciais, desenvolvido inicialmente em **Java com Programação Orientada a Objetos (POO)**.

## Sobre o projeto

O DescHub surgiu da necessidade de registrar e acompanhar abatimentos negociados por vendedores com clientes e indústrias. Sem um controle centralizado, a distribuidora pode tomar conhecimento de um desconto apenas quando o cliente já o aplica a um título financeiro.

A proposta é registrar a negociação, identificar o vendedor responsável e acompanhar os valores **pendentes** e **finalizados**.

**Estado atual:** aplicação Java executada pelo terminal, com dados armazenados em memória. Ainda não há banco de dados, API REST ou interface web.

## Tecnologias e conceitos

- Java
- Programação Orientada a Objetos: classes, objetos, construtores e encapsulamento
- `ArrayList` e laços `for-each`
- `LocalDate` para datas
- `enum` para representar status
- Validação com `IllegalArgumentException`
- Formatação de saída com `System.out.printf()`

## Estrutura

```text
src/
├── Main.java
├── Abatimento.java
├── GerenciadorAbatimentos.java
├── Resumo.java
├── Vendedor.java
├── Cliente.java
├── Industria.java
└── StatusAbatimento.java
```

## Classes e métodos

### 1. `Vendedor`

Representa o vendedor responsável por uma negociação.

**Atributos:** `id` e `nome`.

| Método | O que faz |
| --- | --- |
| `Vendedor(int id, String nome)` | Cria um vendedor com ID e nome. |
| `getId()` | Retorna o ID do vendedor. |
| `getNome()` | Retorna o nome do vendedor. |
| `setNome(String nome)` | Atualiza o nome do vendedor. |

### 2. `Cliente`

Representa o cliente que participa da negociação comercial.

**Atributos:** `id` e `nome`.

| Método | O que faz |
| --- | --- |
| `Cliente(int id, String nome)` | Cria um cliente com ID e nome. |
| `getId()` | Retorna o ID do cliente. |
| `getNome()` | Retorna o nome do cliente. |
| `setNome(String nome)` | Atualiza o nome do cliente, caso esteja implementado na classe. |

### 3. `Industria`

Representa a indústria associada à ação ou ao abatimento.

**Atributos:** `id` e `nome`.

| Método | O que faz |
| --- | --- |
| `Industria(int id, String nome)` | Cria uma indústria com ID e nome. |
| `getId()` | Retorna o ID da indústria. |
| `getNome()` | Retorna o nome da indústria. |
| `setNome(String nome)` | Atualiza o nome da indústria, caso esteja implementado na classe. |

### 4. `StatusAbatimento`

É um `enum` que define os estados possíveis de um abatimento:

```java
public enum StatusAbatimento {
    PENDENTE,
    FINALIZADO
}
```

O `enum` evita usar textos livres para representar o status, reduzindo erros de digitação e padronizando os valores aceitos.

### 5. `Abatimento`

É a classe central do domínio. Representa uma negociação registrada no sistema.

**Atributos:** `id`, `vendedor`, `cliente`, `industria`, `data`, `descricao`, `valor` e `status`.

**ID automático:** o atributo estático `contadorId` começa em 1 e é incrementado a cada novo objeto. O ID não precisa ser passado ao construtor. Esse contador funciona apenas durante a execução do programa e não substitui um identificador gerado por banco de dados.

| Método | O que faz |
| --- | --- |
| `Abatimento(Vendedor, Cliente, Industria, LocalDate, String, double, StatusAbatimento)` | Constrói o abatimento, valida valor não negativo e gera um ID. |
| `getId()` | Retorna o ID gerado. |
| `getVendedor()` | Retorna o vendedor responsável. |
| `getCliente()` | Retorna o cliente. |
| `getIndustria()` | Retorna a indústria. |
| `getData()` | Retorna a data da negociação. |
| `getDescricao()` | Retorna a descrição. |
| `getValor()` | Retorna o valor. |
| `getStatus()` | Retorna o status atual. |
| `setData(LocalDate)` **privado** | Atualiza internamente a data. |
| `setDescricao(String)` **privado** | Atualiza internamente a descrição. |
| `setValor(double)` **privado** | Valida que o valor não seja negativo antes de atualizar. |
| `setStatus(StatusAbatimento)` **privado** | Atualiza internamente o status. |
| `estaPendente()` | Retorna `true` quando o status é `PENDENTE`. |
| `estaFinalizado()` | Retorna `true` quando o status é `FINALIZADO`. |
| `pertenceAoVendedor(Vendedor)` | Compara os IDs para verificar se o vendedor informado é o responsável; retorna `false` para vendedor nulo. |
| `validarPermissao(Vendedor)` **privado** | Lança uma exceção quando o vendedor não é o responsável. |
| `alterarData(Vendedor, LocalDate)` | Verifica a permissão e altera a data. |
| `alterarDescricao(Vendedor, String)` | Verifica a permissão e altera a descrição. |
| `alterarValor(Vendedor, double)` | Verifica a permissão e valida o novo valor. |
| `finalizar(Vendedor)` | Verifica a permissão e muda o status para `FINALIZADO`. |
| `reabrir(Vendedor)` | Verifica a permissão e muda o status para `PENDENTE`. |

**Exemplo de uso:**

```java
Abatimento abatimento = new Abatimento(
    vendedor1,
    cliente1,
    industria1,
    LocalDate.of(2026, 10, 5),
    "Ação de inauguração",
    15000,
    StatusAbatimento.PENDENTE
);

abatimento.alterarValor(vendedor1, 18000);
abatimento.finalizar(vendedor1);
```

**Regra de negócio:** um vendedor não pode alterar ou finalizar o abatimento de outro vendedor. As alterações passam por métodos públicos que validam a permissão, em vez de expor os setters diretamente.

### 6. `GerenciadorAbatimentos`

Centraliza a coleção de abatimentos cadastrados. Utiliza `ArrayList<Abatimento>` como armazenamento em memória.

| Método | O que faz |
| --- | --- |
| `GerenciadorAbatimentos()` | Inicializa a lista vazia. |
| `adicionarAbatimento(Abatimento)` | Adiciona um abatimento, rejeitando `null` e IDs já cadastrados. |
| `buscarPorId(int)` | Procura um abatimento pelo ID; retorna o objeto ou `null` se não existir. |
| `existeAbatimento(int)` | Retorna `true` se um ID estiver cadastrado e `false` caso contrário. |
| `listarAbatimentos()` | Retorna uma nova lista com referências aos abatimentos cadastrados. |
| `finalizarAbatimentoPorId(int, Vendedor)` | Localiza pelo ID e solicita a finalização com validação do vendedor; retorna `false` se o ID não existir. |

**Por que `listarAbatimentos()` cria uma lista nova?**

```java
public ArrayList<Abatimento> listarAbatimentos() {
    return new ArrayList<>(abatimentos);
}
```

A cópia protege a **estrutura da lista interna**: quem recebe a lista não consegue remover ou adicionar registros diretamente no gerenciador. Porém, os elementos são referências aos **mesmos objetos `Abatimento`**. Portanto, uma alteração autorizada em um objeto também aparece nas próximas consultas.

**Exemplo:**

```java
GerenciadorAbatimentos gerenciador = new GerenciadorAbatimentos();

gerenciador.adicionarAbatimento(abatimento);
Abatimento encontrado = gerenciador.buscarPorId(abatimento.getId());

gerenciador.finalizarAbatimentoPorId(abatimento.getId(), vendedor1);
```

### 7. `Resumo`

É responsável **somente por consultas e cálculos**. Recebe o `GerenciadorAbatimentos` no construtor e consulta a lista atualizada sempre que um cálculo é solicitado.

| Método | O que faz |
| --- | --- |
| `Resumo(GerenciadorAbatimentos)` | Recebe o gerenciador e rejeita referência nula. |
| `calcularTotal()` | Soma os valores de todos os abatimentos. |
| `calcularPendente()` | Soma os valores com status `PENDENTE`. |
| `calcularFinalizado()` | Soma os valores com status `FINALIZADO`. |
| `calcularTotalPorIndustria(Industria)` | Soma os abatimentos associados à indústria informada. |
| `calcularTotalPorVendedor(Vendedor)` | Soma os abatimentos pertencentes ao vendedor informado. |
| `calcularPendentePorVendedor(Vendedor)` | Soma apenas os abatimentos pendentes daquele vendedor. |
| `contarPendentes()` | Conta quantos abatimentos estão pendentes. |

**Exemplo:**

```java
Resumo resumo = new Resumo(gerenciador);

System.out.printf("Total geral: R$ %.2f%n", resumo.calcularTotal());
System.out.printf("Total pendente: R$ %.2f%n", resumo.calcularPendente());
System.out.printf("Quantidade pendente: %d%n", resumo.contarPendentes());
```

**Integração:** como o `Resumo` guarda uma referência ao gerenciador, um novo abatimento adicionado ao gerenciador entra nos cálculos seguintes, sem recriar o objeto `Resumo`.

### 8. `Main`

É o ponto de entrada do programa. O método `public static void main(String[] args)` cria os objetos e executa testes manuais das regras de negócio.

O fluxo atual é:

1. Criar três vendedores, três clientes e três indústrias.
2. Criar três abatimentos, com IDs automáticos.
3. Cadastrar os abatimentos no gerenciador.
4. Listar os registros e buscar abatimentos por ID.
5. Gerar o resumo financeiro inicial.
6. Consultar totais por vendedor e indústria.
7. Alterar valor, descrição e data de um abatimento.
8. Finalizar e reabrir um abatimento.
9. Testar bloqueio de alteração por vendedor não autorizado.
10. Testar rejeição de valor negativo e de cadastro duplicado.
11. Exibir o resumo financeiro final.

**Importante:** esses testes são executados manualmente no `Main`; ainda não são testes automatizados com JUnit.

## Como as classes trabalham juntas

```text
Main
 ├── cria Vendedor, Cliente e Industria
 ├── cria Abatimento
 │    └── aplica validações e regras de permissão
 ├── usa GerenciadorAbatimentos
 │    ├── cadastra
 │    ├── busca
 │    ├── lista
 │    └── solicita finalização
 └── usa Resumo
      └── consulta GerenciadorAbatimentos para calcular valores
```

A separação evita que `Resumo` cadastre ou finalize registros e evita que `GerenciadorAbatimentos` repita as validações já existentes em `Abatimento`.

## Como executar

**Pré-requisito:** JDK instalado e disponível no terminal.

Na raiz do projeto, compile os arquivos Java:

```bash
javac -d bin src/*.java
```

Execute o programa:

```bash
java -cp bin Main
```

Os comandos acima consideram que os arquivos estão diretamente em `src/`, sem declaração de `package`.

## Resultado esperado dos testes

Com os três abatimentos de exemplo:

| Momento | Total geral | Pendente | Finalizado | Quantidade pendente |
| --- | ---: | ---: | ---: | ---: |
| Após o cadastro | R$ 27.700,00 | R$ 27.700,00 | R$ 0,00 | 3 |
| Após alterar o primeiro para R$ 18.000 e finalizá-lo | R$ 30.700,00 | R$ 12.700,00 | R$ 18.000,00 | 2 |
| Após reabrir o primeiro | R$ 30.700,00 | R$ 30.700,00 | R$ 0,00 | 3 |

Os testes de permissão, valor negativo e ID duplicado devem gerar as exceções esperadas, tratadas pelo `Main`.

## Limitações atuais

- Os dados ficam apenas em memória e são perdidos ao encerrar a aplicação.
- O contador de IDs é reiniciado quando o programa inicia novamente.
- Não existe autenticação de usuários: a permissão é validada com base no objeto `Vendedor` informado pelo código.
- Valores financeiros ainda utilizam `double`; para uma versão de produção, é preferível avaliar `BigDecimal`.
- Os testes ainda não são automatizados.

## Próximas etapas

1. Criar testes unitários com JUnit.
2. Introduzir Spring Boot e expor uma API REST.
3. Adicionar persistência com banco de dados.
4. Desenvolver a interface em Angular e TypeScript.
5. Integrar frontend e backend para cadastrar e consultar abatimentos.

---

**DescHub** — projeto de aprendizado e evolução prática em Java, POO e desenvolvimento full stack.
