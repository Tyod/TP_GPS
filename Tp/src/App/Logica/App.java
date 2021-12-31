package App.Logica;

import App.Logica.Data.AppData;
import App.Logica.Data.Quarto;
import App.Logica.States.EscolheVista;
import App.Logica.States.IAppState;

import java.util.ArrayList;
import java.util.List;

public class App {
    private IAppState estado;
    private AppData dados;
    private static final String filePublicados = "ListaQuartosPublicados.txt";
    private static final String filePendentes = "ListaQuartosPendentes.txt";
    private static final String filePessoal = "ListaQuartosPessoal.txt";
    private static final String fileFavoritos = "ListaFavoritos.txt";
    private List<PropsID> eventos = new ArrayList<>();

    public App(){
        dados = new AppData();
        estado = new EscolheVista(dados);
    }

    private boolean defineEstado(IAppState prox){
        IAppState anterior = estado;
        estado = prox;
        if(anterior!=prox){
            eventos.add(new PropsID("prop_estado"));
            return true;
        }
        return false;
    }

    public AppSituation getSituacaoAtual() {
        return estado.getSituacaoAtual();
    }


    public List<PropsID> geraVistaListaQuartosPublicados() {
        eventos.clear();
        defineEstado(estado.geraVistaListaQuartosPublicados());
        return eventos;
    }

    public List<PropsID> geraVistaListaQuartosPendentes() {
        eventos.clear();
        defineEstado(estado.geraVistaListaQuartosPendentes());
        return eventos;
    }

    public List<PropsID> geraVistaListaQuartosPessoal() {
        eventos.clear();
        defineEstado(estado.geraVistaListaQuartosPessoal());
        return eventos;
    }

    public List<PropsID> geraVistaEscolheVista() {
        eventos.clear();
        defineEstado(estado.geraVistaEscolheVista());
        return eventos;
    }

    public List<PropsID> geraVistaFavoritos() {
        eventos.clear();
        defineEstado(estado.geraVistaFavoritos());
        return eventos;
    }

    public List<PropsID> geraVistaMensagensEstudante() {
        eventos.clear();
        defineEstado(estado.geraVistaMensagensEstudante());
        return eventos;
    }

    public List<PropsID> geraVistaMensagensSenhorio() {
        eventos.clear();
        defineEstado(estado.geraVistaMensagensSenhorio());
        return eventos;
    }

    public List<PropsID> geraVistaEditarAnuncio() {
        eventos.clear();
        defineEstado(estado.geraVistaEditarAnuncio());
        return eventos;
    }

    public List<PropsID> geraVistaCriarAnuncio() {
        eventos.clear();
        defineEstado(estado.geraVistaCriarAnuncio());
        return eventos;
    }

    public ArrayList<Quarto> getListaQuartosPublicados() {
        return estado.getListaQuartosPublicados();
    }
}
