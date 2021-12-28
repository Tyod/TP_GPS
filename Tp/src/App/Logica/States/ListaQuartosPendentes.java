package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.AppData;

public class ListaQuartosPendentes extends AppStateAdapter{

    public ListaQuartosPendentes(AppData dados) {
        super(dados);
    }

    @Override
    public AppSituation getSituacaoAtual() {
        return AppSituation.Lista_Quartos_Pendentes;
    }
}
