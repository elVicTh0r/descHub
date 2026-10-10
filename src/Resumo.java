import java.util.ArrayList;

// Classe para uma lista dinâmica de abatimentos (Ela junta todos os abatimentos)
public class Resumo {
    private ArrayList<Abatimento> abatimentos;

    public Resumo(ArrayList<Abatimento> abatimentos) {
        this.abatimentos = abatimentos;
    }

    // Metodo para Somar todos os valores de abatimentos
    public double calcularTotal(){

        double total = 0;

        for (Abatimento abatimento : abatimentos) {
            total += abatimento.getValor();
        }   
            
        return total;
    }   

    // Metodo para somar todos os valores de abatimentos pendentes
    public double calcularPendente(){

        double totalPendente = 0;

        for (Abatimento abatimento : abatimentos) {
            if(abatimento.estaPendente()){
                totalPendente += abatimento.getValor();
            }
        }
        
        return totalPendente;
    }

    // Metodo para somar todos os valores de abatimentos finalizados
    public double calcularFinalizado(){

        double totalFinalizado = 0;

        for (Abatimento abatimento : abatimentos) {
            if(abatimento.estaFinalizado()){
                totalFinalizado += abatimento.getValor();
            }
        }

        return totalFinalizado;
    }

    // Metodo para somar todos os valores de abatimentos por industria
    public double calcularTotalPorIndustria(Industria industria) {

        double total = 0;

        for (Abatimento abatimento : abatimentos) {
            if(abatimento.getIndustria().getId() == (industria.getId())){
                total += abatimento.getValor();
            }
        }

        return total;

    }

    // Metodo para somar todos os valores de abatimentos por vendedor
    public double calcularTotalPorVendedor(Vendedor vendedor) {

        double total = 0;

        for (Abatimento abatimento : abatimentos) {
            if(abatimento.getVendedor().getId() == (vendedor.getId())){
                total += abatimento.getValor();
            }
        }

        return total;

    }

    // Metodo para finalizar um abtimento
    public boolean finalizarAbatimento(int id) {

        Abatimento abatimento = exibirAbatimentoPorId(id);

        if(abatimento == null){
            return false;
        }
        abatimento.finalizar();
        return true;     
    }  

    // Metodos para adicionar um abatimento
    public void adicionarAbatimento(Abatimento abatimento){
        abatimentos.add(abatimento);
    }

    // Metodo para busca e exibir um abatimento pelo ID
    public Abatimento exibirAbatimentoPorId(int id){
        for (Abatimento abatimento : abatimentos) {
            if(abatimento.getId() == id){
                return abatimento;
            }
        }
        return null;
    }
}