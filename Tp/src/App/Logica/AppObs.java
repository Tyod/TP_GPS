package App.Logica;

import App.Logica.Data.DisponibilidadeQuarto;
import App.Logica.Data.Mensagem;
import App.Logica.Data.Quarto;
import App.Logica.Data.TipoUtilzadores;
import App.Logica.States.IAppState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class AppObs {
    private App app = new App();
    private PropertyChangeSupport props = new PropertyChangeSupport(app);

    private void disparaEventos(List<PropsID> eventos){
        eventos.forEach((e)-> {
            props.firePropertyChange(e.toString(), null, null);
        });
    }

    public void registaPropertyChangeListener(PropsID prop, PropertyChangeListener listener){
        props.addPropertyChangeListener(prop.toString(), listener);
    }

    public AppSituation getSituacao() {
        return app.getSituacaoAtual();
    }




    public void adicionaQuartoPessoal(DisponibilidadeQuarto disponibilidade, int preco, String localizacao, String servicos, Boolean despesas, long contacto, String imagem){
        app.adicionaQuartoPessoal(disponibilidade, preco, localizacao, servicos, despesas, contacto, imagem);
    }

    public void removeQuartoPessoal(int id, boolean flag) {
        app.removeQuartoPessoal(id, flag);
    }
    public void removeQuartoPendente(int id) {
        app.removeQuartoPendente(id);

    }

    public void adcionaQuartoPublico(int id) {
        app.adcionaQuartoPublico(id);
    }

   //Get Lists
    public ArrayList<Quarto> getListaQuartosPublicados() { return app.getListaQuartosPublicados(); }
    public ArrayList<Quarto> getListaQuartosFavoritos() { return app.getListaQuartosFavoritos(); }
    public ArrayList<Quarto> getListaQuartosPessoal() {
        return app.getListaQuartosPessoal();
    }
    public ArrayList<Quarto> getListaQuartosPendentes() { return app.getListaQuartosPendentes(); }
    public ArrayList<Mensagem> getListaMensagens() { return app.getListaMensagens(); }


    //Get Mensagens
    public void adicionaMensagem(TipoUtilzadores estudante, String text) {
        app.adicionaMensagem(estudante, text);
    }


    //Gera Vistas
    public void geraVistaEscolheVista() { disparaEventos(app.geraVistaEscolheVista()); }
    public void geraVistaFavoritos() { disparaEventos(app.geraVistaFavoritos()); }
    public void geraVistaMensagensEstudante() { disparaEventos(app.geraVistaMensagensEstudante()); }
    public void geraVistaMensagensSenhorio() { disparaEventos(app.geraVistaMensagensSenhorio()); }
    public void geraVistaEditarAnuncio() { disparaEventos(app.geraVistaEditarAnuncio()); }
    public void geraVistaCriarAnuncio() { disparaEventos(app.geraVistaCriarAnuncio()); }
    public void geraVistaListaQuartosPublicados() {
        disparaEventos(app.geraVistaListaQuartosPublicados());
    }
    public void geraVistaListaQuartosPendentes() {
        disparaEventos(app.geraVistaListaQuartosPendentes());
    }
    public void geraVistaListaQuartosPessoal() {
        disparaEventos(app.geraVistaListaQuartosPessoal());
    }
}
