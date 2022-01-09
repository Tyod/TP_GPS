package App.Logica.States;

import App.Logica.Data.*;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class AppStateAdapter  implements IAppState{
    protected AppData dados;
    protected AppStateAdapter(AppData dados){ this.dados = dados; }

    public IAppState geraVistaListaQuartosPublicados(){ return null; }
    public IAppState geraVistaListaQuartosPendentes(){ return null; }
    public IAppState geraVistaListaQuartosPessoal(){ return null; }
    public IAppState geraVistaMensagensEstudante() { return null; }
    public IAppState geraVistaMensagensSenhorio(){ return null; }
    public IAppState geraVistaEscolheVista(){ return null; }
    public IAppState geraVistaFavoritos(){ return null; }
    public IAppState geraVistaCriarAnuncio(){ return null; }
    public IAppState geraVistaEditarAnuncio(){ return null; }
    public ArrayList<Quarto> getListaQuartosPublicados(){ return null; }
    public ArrayList<Quarto> getListaQuartosPessoal(){ return null; }
    public ArrayList<Quarto> getListaQuartosPendentes(){ return null; }
    public ArrayList<Quarto> getListaQuartosFavoritos(){ return null; }
    public ArrayList<Mensagem> getListaMensagens() { return null; }
    public void adicionaMensagem(TipoUtilzadores utilizador, String msg){}
    public void adicionaQuartoPessoal(DisponibilidadeQuarto disponibilidade, int preco, String localizacao, String servicos, Boolean despesas, long contacto, Image imagem){}
    public void adcionaQuartoPublico(int id){}
    public void removeQuartoPessoal(int id, boolean flag){}
    public boolean removeQuartoPendente(int id){ return false; }
    public void adicionaQuartoFavorito(int id){}
}
