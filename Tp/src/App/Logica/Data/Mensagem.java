package App.Logica.Data;

import java.io.Serializable;

public class Mensagem implements Serializable {
    private TipoUtilzadores autor;
    private String mensagem;

    public Mensagem(TipoUtilzadores autor, String mensagem){
        this.autor = autor;
        this.mensagem = mensagem;
    }

    public TipoUtilzadores getAutor() {
        return autor;
    }

    public String getMensagem() {
        return mensagem;
    }
}
