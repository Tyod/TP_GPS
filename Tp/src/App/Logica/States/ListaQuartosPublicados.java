package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.AppData;
import App.Logica.Data.Quarto;

import java.util.ArrayList;
import java.util.List;

public class ListaQuartosPublicados extends AppStateAdapter{
    public ListaQuartosPublicados(AppData dados) {
        super(dados);
    }

    public ArrayList<Quarto> getListaQuartosPublicados(){
         return dados.getListaQuartoPublicados();
    }

    public IAppState geraVistaMensagensEstudante(){
        return new MensagensNotificacoes_Estudante(dados);
    }

    public IAppState geraVistaEscolheVista(){
        return new EscolheVista(dados);
    }

    public IAppState geraVistaFavoritos() {
        return new ListaFavoritos(dados);
    }

    @Override
    public AppSituation getSituacaoAtual() {
        return AppSituation.Lista_Quartos_Publicados;
    }
}
