public class Resumo {

    private final GerenciadorAbatimentos gerenciador;

    // Recebe o gerenciador que contém os abatimentos
    public Resumo(GerenciadorAbatimentos gerenciador) {

        if (gerenciador == null) {
            throw new IllegalArgumentException(
                "O gerenciador não pode ser nulo"
            );
        }

        this.gerenciador = gerenciador;
    }

    // Calcula o valor total de todos os abatimentos
    public double calcularTotal() {

        double total = 0;

        for (Abatimento abatimento : gerenciador.listarAbatimentos()) {
            total += abatimento.getValor();
        }

        return total;
    }

    // Calcula o valor total dos abatimentos pendentes
    public double calcularPendente() {

        double total = 0;

        for (Abatimento abatimento : gerenciador.listarAbatimentos()) {

            if (abatimento.estaPendente()) {
                total += abatimento.getValor();
            }
        }

        return total;
    }

    // Calcula o valor total dos abatimentos finalizados
    public double calcularFinalizado() {

        double total = 0;

        for (Abatimento abatimento : gerenciador.listarAbatimentos()) {

            if (abatimento.estaFinalizado()) {
                total += abatimento.getValor();
            }
        }

        return total;
    }

    // Calcula o total de abatimentos de uma indústria
    public double calcularTotalPorIndustria(Industria industria) {

        double total = 0;

        for (Abatimento abatimento : gerenciador.listarAbatimentos()) {

            if (abatimento.getIndustria().getId() == industria.getId()) {
                total += abatimento.getValor();
            }
        }

        return total;
    }

    // Calcula o total de abatimentos de um vendedor
    public double calcularTotalPorVendedor(Vendedor vendedor) {

        double total = 0;

        for (Abatimento abatimento : gerenciador.listarAbatimentos()) {

            if (abatimento.pertenceAoVendedor(vendedor)) {
                total += abatimento.getValor();
            }
        }

        return total;
    }

    // Calcula os abatimentos pendentes de um vendedor
    public double calcularPendentePorVendedor(Vendedor vendedor) {

        double total = 0;

        for (Abatimento abatimento : gerenciador.listarAbatimentos()) {

            if (abatimento.pertenceAoVendedor(vendedor) && abatimento.estaPendente()) {

                total += abatimento.getValor();
            }
        }

        return total;
    }

    // Conta a quantidade de abatimentos pendentes
    public int contarPendentes() {

        int contador = 0;

        for (Abatimento abatimento : gerenciador.listarAbatimentos()) {

            if (abatimento.estaPendente()) {
                contador++;
            }
        }

        return contador;
    }
}