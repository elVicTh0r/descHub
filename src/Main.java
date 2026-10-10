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

        // Crio um ArrayList para guardar os objetos Abatimento
        ArrayList<Abatimento> abatimentos = new ArrayList<Abatimento>();
        
        // Crio a classe Resumo para conseguir calcular somas e adicionar os objetos a lista
        Resumo resumo = new Resumo(abatimentos);

        // Adiciono o objeto abat1 a lista
        resumo.adicionarAbatimento(abat1);

        // Uso metodo para finalizar o abatimento
        resumo.finalizarAbatimento(1);

        // Exibo os calculos de todos os abatimentos através dos metodos existentes na classe Resumo
        System.out.printf("Total Pendente: R$ %.2f%n", resumo.calcularPendente());
        System.out.printf("Total Finalizado: R$ %.2f%n", resumo.calcularFinalizado());
        System.out.printf("Valor total: R$ %.2f%n", resumo.calcularTotal());
        System.out.printf("Total Industria: R$ %.2f%n", resumo.calcularTotalPorIndustria(industria));
        System.out.printf("Total Vendedor: R$ %.2f%n", resumo.calcularTotalPorVendedor(vendedor));

        // Exibo o Status do abatimento pós alteração pelo metodo
        System.out.println(abat1.getStatus());
  
        // Crio um variável do tipo Abatimento para pode exibir todos os dados de um abatimento especifico. 
        Abatimento resultado = resumo.exibirAbatimentoPorId(2);

        if (resultado != null) {
            System.out.printf("ID: %s%n", resultado.getId());
            System.out.printf("Data: %s%n", resultado.getData());
            System.out.printf("Vendedor: %s%n", resultado.getVendedor().getNome());
            System.out.printf("Cliente: %s%n", resultado.getCliente().getNome());
            System.out.printf("Descrição: %s%n", resultado.getDescricao());
            System.out.printf("Valor: %.2f%n", resultado.getValor());
            System.out.printf("Status: %s", resultado.getStatus());
     
        } else {
            System.out.println("Abatimento não encontrado.");
        }
    }
}