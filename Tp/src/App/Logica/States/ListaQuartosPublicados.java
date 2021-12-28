package App.Logica.States;

import App.Logica.AppSituation;
import App.Logica.Data.AppData;

public class ListaQuartosPublicados extends AppStateAdapter{
    public ListaQuartosPublicados(AppData dados) {
        super(dados);
    }

    @Override
    public AppSituation getSituacaoAtual() {
        return AppSituation.Lista_Quartos_Publicados;
    }
}
