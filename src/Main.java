import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== DescHUB =====");

        // ==========================================
        // 1. CADASTRO DOS VENDEDORES
        // ==========================================

        Vendedor vendedor1 = new Vendedor(1, "Gabriel");
        Vendedor vendedor2 = new Vendedor(2, "Victor");
        Vendedor vendedor3 = new Vendedor(3, "Carlos");

        // ==========================================
        // 2. CADASTRO DOS CLIENTES
        // ==========================================

        Cliente cliente1 = new Cliente(1, "Mercadinho Central");
        Cliente cliente2 = new Cliente(2, "Supermercado Bahia");
        Cliente cliente3 = new Cliente(3, "Atacado Popular");

        // ==========================================
        // 3. CADASTRO DAS INDÚSTRIAS
        // ==========================================

        Industria industria1 = new Industria(1, "Ambev");
        Industria industria2 = new Industria(2, "Coca-Cola");
        Industria industria3 = new Industria(3, "Nestlé");

        // ==========================================
        // 4. CRIAÇÃO DOS ABATIMENTOS
        // ==========================================

        // O ID é gerado automaticamente pela classe Abatimento

        Abatimento abat1 = new Abatimento(
            vendedor1,
            cliente1,
            industria1,
            LocalDate.of(2026, 10, 5),
            "Ação de inauguração",
            15000,
            StatusAbatimento.PENDENTE
        );

        Abatimento abat2 = new Abatimento(
            vendedor2,
            cliente2,
            industria2,
            LocalDate.of(2026, 10, 6),
            "Campanha promocional",
            8500,
            StatusAbatimento.PENDENTE
        );

        Abatimento abat3 = new Abatimento(
            vendedor3,
            cliente3,
            industria3,
            LocalDate.of(2026, 10, 7),
            "Ação de exposição",
            4200,
            StatusAbatimento.PENDENTE
        );

        // ==========================================
        // 5. GERENCIADOR DE ABATIMENTOS
        // ==========================================

        GerenciadorAbatimentos gerenciador = new GerenciadorAbatimentos();

        gerenciador.adicionarAbatimento(abat1);
        gerenciador.adicionarAbatimento(abat2);
        gerenciador.adicionarAbatimento(abat3);

        System.out.println("\n===== ABATIMENTOS CADASTRADOS =====");

        for (Abatimento abatimento : gerenciador.listarAbatimentos()) {

            System.out.printf("ID: %d%n", abatimento.getId());
            System.out.printf("Descrição: %s%n", abatimento.getDescricao());
            System.out.printf("Valor: R$ %.2f%n", abatimento.getValor());
            System.out.println("------------------------------");
        }

        // ==========================================
        // 6. BUSCA POR ID
        // ==========================================

        System.out.println("\n===== BUSCA POR ID =====");

        Abatimento encontrado = gerenciador.buscarPorId(abat2.getId());

        if (encontrado != null) {
            System.out.printf("Encontrado: %s%n", encontrado.getDescricao());
        } else {
            System.out.println("Abatimento não encontrado.");
        }

        System.out.printf("Existe ID 3? %b%n", gerenciador.existeAbatimento(abat3.getId()));
        System.out.printf("Existe ID 99? %b%n", gerenciador.existeAbatimento(99));

        // ==========================================
        // 7. RESUMO FINANCEIRO
        // ==========================================

        Resumo resumo = new Resumo(gerenciador);

        System.out.println("\n===== RESUMO INICIAL =====");

        System.out.printf("Total geral: R$ %.2f%n", resumo.calcularTotal());
        System.out.printf("Total pendente: R$ %.2f%n", resumo.calcularPendente());
        System.out.printf("Total finalizado: R$ %.2f%n", resumo.calcularFinalizado());
        System.out.printf("Quantidade pendente: %d%n", resumo.contarPendentes());

        // ==========================================
        // 8. TOTAIS POR VENDEDOR E INDÚSTRIA
        // ==========================================

        System.out.println("\n===== FILTROS =====");

        System.out.printf("Total do Gabriel: R$ %.2f%n", resumo.calcularTotalPorVendedor(vendedor1));
        System.out.printf("Total da Ambev: R$ %.2f%n", resumo.calcularTotalPorIndustria(industria1));
        System.out.printf("Pendente do Victor: R$ %.2f%n", resumo.calcularPendentePorVendedor(vendedor2));

        // ==========================================
        // 9. ALTERAÇÃO DE VALOR
        // ==========================================

        System.out.println("\n===== ALTERAÇÃO DE VALOR =====");

        abat1.alterarValor(vendedor1, 18000);

        System.out.printf("Novo valor do abatimento 1: R$ %.2f%n", abat1.getValor());

        // ==========================================
        // 10. ALTERAÇÃO DE DESCRIÇÃO E DATA
        // ==========================================

        System.out.println("\n===== ALTERAÇÃO DE DADOS =====");

        abat1.alterarDescricao(vendedor1, "Ação de inauguração atualizada");
        abat1.alterarData(vendedor1, LocalDate.of(2026, 10, 10));

        System.out.printf("Nova descrição: %s%n", abat1.getDescricao());
        System.out.printf("Nova data: %s%n", abat1.getData());

        // ==========================================
        // 11. FINALIZAR ABATIMENTO
        // ==========================================

        System.out.println("\n===== FINALIZAÇÃO =====");

        gerenciador.finalizarAbatimentoPorId(abat1.getId(), vendedor1);

        System.out.printf("Abatimento 1 finalizado? %b%n", abat1.estaFinalizado());
        System.out.printf("Total pendente atualizado: R$ %.2f%n", resumo.calcularPendente());
        System.out.printf("Total finalizado atualizado: R$ %.2f%n", resumo.calcularFinalizado());

        // ==========================================
        // 12. REABRIR ABATIMENTO
        // ==========================================

        System.out.println("\n===== REABERTURA =====");

        abat1.reabrir(vendedor1);

        System.out.printf("Abatimento 1 pendente? %b%n", abat1.estaPendente());

        // ==========================================
        // 13. TESTE DE PERMISSÃO
        // ==========================================

        System.out.println("\n===== TESTE DE PERMISSÃO =====");

        try {

            // Victor tentando alterar abatimento de Gabriel
            abat1.alterarValor(vendedor2, 50000);

            System.out.println("ERRO: alteração não autorizada foi permitida.");

        } catch (IllegalArgumentException e) {

            System.out.printf("Permissão bloqueada corretamente: %s%n", e.getMessage());
        }

        // ==========================================
        // 14. TESTE DE VALOR NEGATIVO
        // ==========================================

        System.out.println("\n===== TESTE DE VALIDAÇÃO =====");

        try {

            abat2.alterarValor(vendedor2, -100);

            System.out.println("ERRO: valor negativo foi permitido.");

        } catch (IllegalArgumentException e) {

            System.out.printf("Valor negativo bloqueado: %s%n", e.getMessage());
        }

        // ==========================================
        // 15. TESTE DE ID DUPLICADO
        // ==========================================

        System.out.println("\n===== TESTE DE ID DUPLICADO =====");

        try {

            // Tenta cadastrar novamente o mesmo objeto
            gerenciador.adicionarAbatimento(abat1);

            System.out.println("ERRO: ID duplicado foi permitido.");

        } catch (IllegalArgumentException e) {

            System.out.printf("ID duplicado bloqueado: %s%n", e.getMessage());
        }

        // ==========================================
        // 16. RESUMO FINAL
        // ==========================================

        System.out.println("\n===== RESUMO FINAL =====");

        System.out.printf("Total geral: R$ %.2f%n", resumo.calcularTotal());
        System.out.printf("Total pendente: R$ %.2f%n", resumo.calcularPendente());
        System.out.printf("Total finalizado: R$ %.2f%n", resumo.calcularFinalizado());
        System.out.printf("Quantidade pendente: %d%n", resumo.contarPendentes());

        System.out.println("\n===== FIM DOS TESTES =====");
    }
}