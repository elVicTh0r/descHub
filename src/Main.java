import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Crio os objetos (Vendedor -> Cliente -> Industria => Abatimento)
        Vendedor vendedor = new Vendedor(1, "Paulo Portugal");
        Cliente cliente = new Cliente(1, "Atacadão");
        Industria industria = new Industria(1, "Nissin");
        Abatimento abat1 = new Abatimento(
            vendedor,
            cliente,
            industria,
            LocalDate.of(2026, 10, 5),
            "Rebaixa Cup Noodles",
            15000,
            StatusAbatimento.PENDENTE
        );

        Vendedor vendedor2 = new Vendedor(2, "Leones Carilo");
        Cliente cliente2 = new Cliente(2, "Hiperideal");
        Industria industria2 = new Industria(2, "Condor");
        Abatimento abat2 = new Abatimento(
            vendedor2,
            cliente2,
            industria2,
            LocalDate.of(2026, 10, 5),
            "Inauguração Gbarbosa",
            35000,
            StatusAbatimento.FINALIZADO
        );

        // Crio um ArrayList para guardar os objetos Abatimento
        ArrayList<Abatimento> abatimentos = new ArrayList<Abatimento>();
        
        // Crio a classe Resumo para conseguir calcular somas e adicionar os objetos a lista
        Resumo resumo = new Resumo(abatimentos);
        
        // Adiciono o objeto abat1 a lista
        resumo.adicionarAbatimento(abat1);
        resumo.adicionarAbatimento(abat2);
        
        // Crio um variável do tipo Abatimento para pode exibir todos os dados de um abatimento especifico. 
        Abatimento resultado = resumo.exibirAbatimentoPorId(1);
        Abatimento resultado2 = resumo.exibirAbatimentoPorId(2);

        if (resultado != null) {
            System.out.printf("%n%n==== Listagem Detalhada dos Abatimentos ====%n%n");
            System.out.printf("ID: %s%n", resultado.getId());
            System.out.printf("Data: %s%n", resultado.getData());
            System.out.printf("Vendedor: %s%n", resultado.getVendedor().getNome());
            System.out.printf("Cliente: %s%n", resultado.getCliente().getNome());
            System.out.printf("Descrição: %s%n", resultado.getDescricao());
            System.out.printf("Valor: %.2f%n", resultado.getValor());
            System.out.printf("Status: %s%n%n", resultado.getStatus());
     
        } else {
            System.out.println("Abatimento não encontrado.");
        }

        if (resultado2 != null) {
            System.out.printf("ID: %s%n", resultado2.getId());
            System.out.printf("Data: %s%n", resultado2.getData());
            System.out.printf("Vendedor: %s%n", resultado2.getVendedor().getNome());
            System.out.printf("Cliente: %s%n", resultado2.getCliente().getNome());
            System.out.printf("Descrição: %s%n", resultado2.getDescricao());
            System.out.printf("Valor: %.2f%n", resultado2.getValor());
            System.out.printf("Status: %s", resultado2.getStatus());
     
        } else {
            System.out.println("Abatimento não encontrado.");
        }

        // Exibo os calculos de todos os abatimentos através dos metodos existentes na classe Resumo
        System.out.printf("%n%n==== Valores totais dos Abatimentos ====%n%n");
        System.out.printf("Abatimentos Pendentes: R$ %.2f%n", resumo.calcularPendente());
        System.out.printf("Abatimentos Finalizados: R$ %.2f%n", resumo.calcularFinalizado());
        System.out.printf("Valor Total: R$ %.2f%n", resumo.calcularTotal());

        // Exibo os valores por vendedor
        
        // System.out.printf("Total Vendedor: R$ %.2f", resumo.calcularTotalPorVendedor(vendedor));

        // Exibo os valores por Industria
        // System.out.printf("Total Industria: R$ %.2f%n", resumo.calcularTotalPorIndustria(industria));
  
    }
}