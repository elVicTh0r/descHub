import java.util.ArrayList;

public class GerenciadorAbatimentos {

    // Lista que armazena os abatimentos cadastrados
    private ArrayList<Abatimento> abatimentos;

    // Construtor: inicializa a lista vazia
    public GerenciadorAbatimentos() {
        this.abatimentos = new ArrayList<>();
    }

    // Adiciona um novo abatimento
    public void adicionarAbatimento(Abatimento abatimento) {

        if (abatimento == null) {
            throw new IllegalArgumentException("O abatimento não pode ser nulo");
        }

        if (existeAbatimento(abatimento.getId())) {
            throw new IllegalArgumentException("Já existe um abatimento com esse ID");
        }

        abatimentos.add(abatimento);
    }

    // Busca um abatimento pelo ID
    public Abatimento buscarPorId(int id) {

        for (Abatimento abatimento : abatimentos) {
            if (abatimento.getId() == id) {
                return abatimento;
            }
        }

        return null;
    }

    // Verifica se existe um abatimento com o ID informado
    public boolean existeAbatimento(int id) {
        return buscarPorId(id) != null;
    }

    // Retorna uma cópia da lista de abatimentos
    public ArrayList<Abatimento> listarAbatimentos() {
        return new ArrayList<>(abatimentos);
    }

    // Metodo para finalizar um abatimento pelo ID
    public boolean finalizarAbatimentoPorId(int id, Vendedor vendedor) {

        Abatimento abatimento = buscarPorId(id);

        if (abatimento == null) {
            return false;
        }

        abatimento.finalizar(vendedor);

        return true;
    }
}