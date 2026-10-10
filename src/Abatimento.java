import java.time.LocalDate;

public class Abatimento {


    
    private static int contadorId = 1;
    private int id;


    private Vendedor vendedor;
    private Cliente cliente;
    private Industria industria;
    private LocalDate data;
    private String descricao;
    private double valor;
    private StatusAbatimento status;

    public Abatimento(Vendedor vendedor, Cliente cliente, Industria industria, LocalDate data, String descricao, double valor, StatusAbatimento status){

        // -------- Validação para valor negativo---------------
        if (valor < 0){
            throw new IllegalArgumentException("Valor do abatimento não pode ser negativo");
        }
        this.valor = valor;
        // -----------------------------------------------------

        this.id = contadorId;
        contadorId ++;
        
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.industria = industria;
        this.data = data;
        this.descricao = descricao;
        this.status = status;
    }

    // Metodos Getters
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

    // Metodos Setters
    public void setData(LocalDate data){
        this.data = data;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public void setValor(double valor){
        if (valor < 0){
            throw new IllegalArgumentException("Valor do abatimento não pode ser negativo");
        }
        this.valor = valor;
    }

    public void setStatus(StatusAbatimento status){
        this.status = status;
    }

    // Outros Metodos

    // Metodo para testar se está finalizado ou pendente
    public boolean estaPendente(){
        return status == StatusAbatimento.PENDENTE;
    }

    public boolean estaFinalizado(){
        return status == StatusAbatimento.FINALIZADO;
    }

    // Metodo para finalizar
    public void finalizar(){
        this.status = StatusAbatimento.FINALIZADO;
    }

}