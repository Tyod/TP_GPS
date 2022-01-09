package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.AppData;
import App.Logica.Data.Quarto;

import java.util.ArrayList;

public class ListaQuartosPendentes extends AppStateAdapter{

    public ListaQuartosPendentes(AppData dados) {
        super(dados);
    }

    public ArrayList<Quarto> getListaQuartosPendentes() { return dados.getListaQuartosPendentes(); }

    public IAppState geraVistaEscolheVista(){
        return new EscolheVista(dados);
    }

    public void removeQuartoPessoal(int id){
        dados.removeQuartoPessoal(id);
    }

    public boolean removeQuartoPendente(int id){
        dados.guardaListaPessoal("ListaQuartosPessoal.txt");
        return dados.removeQuartoPendente(id);
    }

    @Override
    public AppSituation getSituacaoAtual() {
        return AppSituation.Lista_Quartos_Pendentes;
    }
}
