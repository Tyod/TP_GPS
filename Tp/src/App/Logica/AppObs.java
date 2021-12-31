package App.Logica;

import App.Logica.Data.Quarto;
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

    public void geraVistaListaQuartosPublicados() {
        disparaEventos(app.geraVistaListaQuartosPublicados());
    }

    public void geraVistaListaQuartosPendentes() {
        disparaEventos(app.geraVistaListaQuartosPendentes());
    }

    public void geraVistaListaQuartosPessoal() {
        disparaEventos(app.geraVistaListaQuartosPessoal());
    }

    public ArrayList<Quarto> getListaQuartosPublicados() {
        return app.getListaQuartosPublicados();
    }

    public void geraVistaEscolheVista() {
        disparaEventos(app.geraVistaEscolheVista());
    }

    public void geraVistaFavoritos() {
        disparaEventos(app.geraVistaFavoritos());
    }

    public void geraVistaMensagensEstudante() {
        disparaEventos(app.geraVistaMensagensEstudante());
    }

    public void geraVistaMensagensSenhorio() {
        disparaEventos(app.geraVistaMensagensSenhorio());
    }

    public void geraVistaEditarAnuncio() {
        disparaEventos(app.geraVistaEditarAnuncio());
    }

    public void geraVistaCriarAnuncio() {
        disparaEventos(app.geraVistaCriarAnuncio());
    }
}
