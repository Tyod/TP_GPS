package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.AppData;

public class ListaFavoritos extends AppStateAdapter{

    public ListaFavoritos(AppData dados) {
        super(dados);
    }

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
