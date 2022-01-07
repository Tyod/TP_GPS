package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.AppData;
import App.Logica.Data.Quarto;

import java.util.ArrayList;

public class ListaFavoritos extends AppStateAdapter{

    public ListaFavoritos(AppData dados) {
        super(dados);
    }

    public ArrayList<Quarto> getListaQuartosFavoritos() { return dados.getListaFavoritos(); }

    public IAppState geraVistaListaQuartosPublicados(){ return new ListaQuartosPublicados(dados); }

    public IAppState geraVistaMensagensEstudante(){ return new MensagensNotificacoes_Estudante(dados); }

    public IAppState geraVistaEscolheVista(){
        return new EscolheVista(dados);
    }

    @Override
    public AppSituation getSituacaoAtual() {
        return AppSituation.Lista_Favoritos;
    }
}
