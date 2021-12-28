package App.Logica.Data;

public class Quarto {

    private static int Contador = 0;
    private int id;
    private DisponibilidadeQuarto disponiblidade;
    private int preco;
    private String localizacao;
    private String servicos;
    private Boolean despesas;
    private long contacto;

    public Quarto(DisponibilidadeQuarto disponiblidade, int preco, String localizacao, String servicos, Boolean despesas, long contacto) {
        this.id = Quarto.Contador;
        this.disponiblidade = disponiblidade;
        this.preco = preco;
        this.localizacao = localizacao;
        this.servicos = servicos;
        this.despesas = despesas;
        this.contacto = contacto;

        Quarto.Contador++;
    }



    //SETTERS
    public void setDisponiblidade(DisponibilidadeQuarto disponiblidade) {
        this.disponiblidade = disponiblidade;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }

    public void setId(int id) { this.id = id; }

    public void setServicos(String servicos) {
        this.servicos = servicos;
    }

    public void setDespesas(Boolean despesas) {
        this.despesas = despesas;
    }

    public void setContacto(long contacto) {
        this.contacto = contacto;
    }



    //GETTERS
    public DisponibilidadeQuarto getDisponiblidade() {
        return disponiblidade;
    }

    public int getPreco() {
        return preco;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getServicos() {
        return servicos;
    }

    public int getId() { return id; }

    public Boolean getDespesas() {
        return despesas;
    }

    public long getContacto() {
        return contacto;
    }


    @Override
    public String toString() {
        return id + " // " + disponiblidade + " // " + preco + " // " + localizacao + " // " + servicos + " // " + despesas + " // " + contacto;
    }
}
