package App.Logica.Data;

import javafx.scene.Node;
import javafx.scene.image.Image;

import java.io.Serializable;

public class Quarto implements Serializable {

    private static int Contador = 0;
    private int id;
    private DisponibilidadeQuarto disponiblidade;
    private int preco;
    private String localizacao;
    private String servicos;
    private Boolean despesas;
    private long contacto;
    private String imagem;
    private Boolean aprovacao;
    private Boolean publicado;

    public Quarto(DisponibilidadeQuarto disponiblidade, int preco, String localizacao, String servicos, Boolean despesas, long contacto, String imagem) {
        this.id = Quarto.Contador;
        this.disponiblidade = disponiblidade;
        this.preco = preco;
        this.localizacao = localizacao;
        this.servicos = servicos;
        this.despesas = despesas;
        this.contacto = contacto;
        this.imagem = imagem;
        this.aprovacao = false;
        this.publicado = false;


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
    public void setImagem(String imagem) { this.imagem = imagem; }
    public void setAprovacao(Boolean aprovacao) { this.aprovacao = aprovacao; }
    public void setPublicado(Boolean publicado) { this.publicado = publicado; }

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
    public String getImagem() { return imagem; }
    public Boolean getAprovacao() { return aprovacao; }
    public Boolean getPublicado() { return publicado; }

    @Override
    public String toString() {
        return id + " // " + disponiblidade + " // " + preco + " // " + localizacao + " // " + servicos + " // " + despesas + " // " + contacto;
    }
}
